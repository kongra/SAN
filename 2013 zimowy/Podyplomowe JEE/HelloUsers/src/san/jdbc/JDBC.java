package san.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.sql.DataSource;

import org.postgresql.ds.PGPoolingDataSource;

import san.util.Body;
import san.util.Doclean;
import san.util.DynVar;
import san.util.refs.IntRef;

public class JDBC {

  public static void withConnection(Connection connection, Runnable body) {
    withConnection(Doclean.assertDoclean(), connection, body);
  }

  public static void withConnection(String url, String user, String pass,
      Runnable body) {
    try {
      withConnection(Doclean.assertDoclean(),
        DriverManager.getConnection(url, user, pass), body);
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static void withConnection(DataSource dataSource, Runnable body) {
    try {
      withConnection(Doclean.assertDoclean(), dataSource.getConnection(), body);
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static Statement createStatement() throws RuntimeException {
    try {
      Doclean doclean = Doclean.assertDoclean();
      final Statement stmt = connection().createStatement();
      doclean.register(new Runnable() {
        @Override
        public void run() {
          try {
            if (DEBUG) {
              System.out.println("Closing statement " + stmt);
            }
            stmt.close();
          }
          catch (SQLException e) {
            e.printStackTrace();
          }
        }
      });
      return stmt;
    }
    catch (SQLException e) {
      throw new RuntimeException();
    }
  }

  public static int executeUpdate(String query) {
    try {
      return createStatement().executeUpdate(query);
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
    catch (RuntimeException e) {
      throw e;
    }
  }

  public static int withQueryResults(String query, final Body step) {
    try {
      Doclean doclean = Doclean.assertDoclean();
      final ResultSet rs = createStatement().executeQuery(query);
      doclean.register(new Runnable() {
        @Override
        public void run() {
          try {
            if (DEBUG) {
              System.out.println("Closing resultSet " + rs);
            }
            rs.close();
          }
          catch (SQLException e) {
            e.printStackTrace();
          }
        }
      });

      final IntRef stepsCount = IntRef.initially(0);

      resultSet.binding(rs, new Runnable() {
        @Override
        public void run() {
          try {
            while (rs.next()) {
              try {
                stepsCount.value += 1;
                step.run();
              }
              catch (Body.Break br) {
                break;
              }
              catch (Body.Continue cont) {
                ;
              }
            }
          }
          catch (SQLException e) {
            throw new RuntimeException(e);
          }
        }
      });

      return stepsCount.value;
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static Iterator<ResultSet> resultSetIterator(String query) {
    Doclean doclean = Doclean.assertDoclean();
    try {
      final ResultSet rs = createStatement().executeQuery(query);

      doclean.register(new Runnable() {
        @Override
        public void run() {
          try {
            rs.close();
          }
          catch (SQLException e) {
            throw new RuntimeException(e);
          }
        }
      });

      return new Iterator<ResultSet>() {

        private boolean prefetched;

        private boolean ended;

        @Override
        public boolean hasNext() {
          if (prefetched) {
            return !ended;
          }
          ended = nextStep();
          prefetched = true;
          return !ended;
        }

        private boolean nextStep() {
          try {
            return !rs.next();
          }
          catch (SQLException e) {
            throw new RuntimeException(e);
          }
        }

        @Override
        public ResultSet next() {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          prefetched = false;
          return rs;
        }

        @Override
        public void remove() {
          throw new UnsupportedOperationException();
        }

      };
    }
    catch (SQLException | RuntimeException e) {
      throw new RuntimeException(e);
    }
  }

  public static void withTransaction(int isolationLevel, Runnable body) {
    Connection conn = JDBC.connection();
    try {
      boolean originalAutoCommit = conn.getAutoCommit();
      int originalIsolationLevel = conn.getTransactionIsolation();

      try {
        conn.setAutoCommit(false);
        conn.setTransactionIsolation(isolationLevel);

        body.run();
        conn.commit();
      }
      catch (Exception e) {
        conn.rollback();
        throw new RuntimeException(e);
      }
      finally {
        conn.setAutoCommit(originalAutoCommit);
        conn.setTransactionIsolation(originalIsolationLevel);
      }
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static void withTransaction(Runnable body) {
    withTransaction(Connection.TRANSACTION_READ_COMMITTED, body);
  }

  public static void commit() {
    try {
      JDBC.connection().commit();
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static void rollback() {
    try {
      JDBC.connection().rollback();
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static void rollback(Savepoint savepoint) {
    try {
      JDBC.connection().rollback(savepoint);
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static Savepoint setSavepoint() {
    try {
      return JDBC.connection().setSavepoint();
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static Savepoint setSavepoint(String name) {
    try {
      return JDBC.connection().setSavepoint(name);
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> T get(String columnLabel) {
    try {
      return (T) resultSet().getObject(columnLabel);
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static void inDefaultENV(final Runnable body) {
    Doclean.run(new Runnable() {
      @Override
      public void run() {
        JDBC.withConnection(JDBC.pool(), new Runnable() {
          @Override
          public void run() {
            JDBC.withTransaction(body);
          }
        });
      }
    });
  }

  public static Connection connection() {
    return connection.value();
  }

  public static ResultSet resultSet() {
    return resultSet.value();
  }

  public static DataSource pool() {
    return pool;
  }

  private static void withConnection(Doclean doclean,
      final Connection connection, Runnable body) {
    // WITHIN THE CLEANUP CONTEXT REGISTER A CLEANER ...
    doclean.register(new Runnable() {
      @Override
      public void run() {
        try {
          if (DEBUG) {
            System.out.println("Closing connection " + connection);
          }
          connection.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
    });

    // ... AND RUN THE BODY
    JDBC.connection.binding(connection, body);
  }

  private static final DynVar<Connection> connection = DynVar.initially(null);

  private static final DynVar<ResultSet> resultSet = DynVar.initially(null);

  private static final PGPoolingDataSource pool;

  static {
    try {
      Class.forName("org.postgresql.Driver");
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    pool = new PGPoolingDataSource();
    pool.setDataSourceName("A Data Source");
    pool.setServerName("localhost");
    pool.setDatabaseName("JEE2");
    pool.setUser("jee2");
    pool.setPassword("jee2");
    pool.setMaxConnections(50);

    Runtime.getRuntime().addShutdownHook(new Thread() {
      @SuppressWarnings("synthetic-access")
      @Override
      public void run() {
        if (DEBUG) {
          System.out.println("Shutting down JDBC.pool " + pool);
        }
        pool.close();
      }
    });
  }

  private static final boolean DEBUG = false;
}
