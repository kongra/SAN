package san.profile;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import org.apache.commons.codec.digest.DigestUtils;

import san.jdbc.JDBC;
import san.util.Body;
import san.util.Doclean;
import san.util.StringUtils;
import san.util.refs.Ref;

@Stateless
public class ProfileManagerImpl implements ProfileManager {

  @Resource(name = "jdbc/MAAS")
  DataSource dataSource;

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  @Override
  public Long validateProfile(final String login, final String passwd) {
    if (StringUtils.isBlank(login) || StringUtils.isBlank(passwd)) {
      return null;
    }

    final Ref<Long> id = Ref.initially(null);
    final Ref<String> p = Ref.initially(null);

    Doclean.run(new Runnable() {
      @Override
      public void run() {
        JDBC.withConnection(dataSource, new Runnable() {
          @Override
          public void run() {
            JDBC.withQueryResults("select id, passwd from users where login='"
                + login + "'", new Body() {
              @Override
              public void run() throws Break, Continue {
                id.value = JDBC.get("id");
                p.value = JDBC.get("passwd");
              }
            });
          }
        });
      }
    });

    if (id.value == null) {
      return null;
    }
    if (DigestUtils.md5Hex(passwd).equals(p.value)) {
      return id.value;
    }
    return null;
  }

}
