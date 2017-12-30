package fishbone;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class FishboneEntityPK implements Serializable {
   private int projectId;
   private String userName;

   @Column(name = "projectId")
   @Id
   public int getProjectId () {
      return projectId;
   }

   public void setProjectId (int projectId) {
      this.projectId = projectId;
   }

   @Column(name = "userName")
   @Id
   public String getUserName () {
      return userName;
   }

   public void setUserName (String userName) {
      this.userName = userName;
   }

   @Override
   public boolean equals (Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      FishboneEntityPK that = (FishboneEntityPK) o;

      if (projectId != that.projectId) return false;
      if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;

      return true;
   }

   @Override
   public int hashCode () {
      int result = projectId;
      result = 31 * result + (userName != null ? userName.hashCode() : 0);
      return result;
   }
}
