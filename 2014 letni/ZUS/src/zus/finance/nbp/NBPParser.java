package zus.finance.nbp;

import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

public class NBPParser {

  private final EntityManager em;

  private final SessionContext ctx;
  
  public NBPParser(EntityManager em, SessionContext ctx) {
    this.em = em;
    this.ctx = ctx;
  }

  public boolean parse() {
    return false;
  }
  
}
