package zus.doc;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.ForeignKey;

import zus.profile.Client;

@Entity
@Table(name = "docs")
public class Doc implements Serializable {

  @Id
  private long id;

  @Lob
  @Basic(fetch = FetchType.LAZY)
  private String content;

  private Timestamp times;

  @ManyToMany(fetch = FetchType.LAZY)
  private List<Client> clients;

  @Override
  public final int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    return result;
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Doc)) {
      return false;
    }
    Doc other = (Doc) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

  private static final long serialVersionUID = 1L;

  public Doc() {
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

//  public Timestamp getTime() {
//    return time;
//  }
//
//  public void setTime(Timestamp time) {
//    this.time = time;
//  }

//  public Client getClient() {
//    return client;
//  }
//
//  public void setClient(Client client) {
//    this.client = client;
//  }

  public long getId() {
    return id;
  }

}
