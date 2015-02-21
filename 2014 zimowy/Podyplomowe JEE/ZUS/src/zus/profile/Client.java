package zus.profile;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import zus.doc.Doc;
import zus.profile.Profile;

@Entity
public class Client extends Profile implements Serializable {

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "clients")
  @OrderBy("time DESC")
  private List<Doc> docs;

  private static final long serialVersionUID = 1L;

  public Client() {
    super();
  }

//  public Set<Doc> getDocs() {
//    return docs;
//  }
//
//  public void setDocs(Set<Doc> docs) {
//    this.docs = docs;
//  }

}
