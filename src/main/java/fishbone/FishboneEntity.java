package fishbone;

import javax.persistence.*;

@Entity
@Table(name = "fishbone", schema = "login", catalog = "")
@IdClass(FishboneEntityPK.class)
public class FishboneEntity {
   private int projectId;
   private String userName;
   private String projectTime;
   private String projectName;
   private String projectRemark;

   public FishboneEntity () {
   }

   private String result;

   public FishboneEntity (int projectId, String userName, String projectTime, String projectName, String projectRemark, String result) {
      this.projectId = projectId;
      this.userName = userName;
      this.projectTime = projectTime;
      this.projectName = projectName;
      this.projectRemark = projectRemark;
      this.result = result;
   }

   @Id
   @Column(name = "projectId")
   public int getProjectId () {
      return projectId;
   }

   public void setProjectId (int projectId) {
      this.projectId = projectId;
   }

   @Id
   @Column(name = "userName")
   public String getUserName () {
      return userName;
   }

   public void setUserName (String userName) {
      this.userName = userName;
   }

   @Basic
   @Column(name = "projectTime")
   public String getProjectTime () {
      return projectTime;
   }

   public void setProjectTime (String projectTime) {
      this.projectTime = projectTime;
   }

   @Basic
   @Column(name = "projectName")
   public String getProjectName () {
      return projectName;
   }

   public void setProjectName (String projectName) {
      this.projectName = projectName;
   }

   @Basic
   @Column(name = "projectRemark")
   public String getProjectRemark () {
      return projectRemark;
   }

   public void setProjectRemark (String projectRemark) {
      this.projectRemark = projectRemark;
   }

   @Basic
   @Column(name = "result")
   public String getResult () {
      return result;
   }

   public void setResult (String result) {
      this.result = result;
   }

   @Override
   public boolean equals (Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      FishboneEntity that = (FishboneEntity) o;

      if (projectId != that.projectId) return false;
      if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
      if (projectTime != null ? !projectTime.equals(that.projectTime) : that.projectTime != null) return false;
      if (projectName != null ? !projectName.equals(that.projectName) : that.projectName != null) return false;
      if (projectRemark != null ? !projectRemark.equals(that.projectRemark) : that.projectRemark != null) return false;
      if (result != null ? !result.equals(that.result) : that.result != null) return false;

      return true;
   }

   @Override
   public int hashCode () {
      int result1 = projectId;
      result1 = 31 * result1 + (userName != null ? userName.hashCode() : 0);
      result1 = 31 * result1 + (projectTime != null ? projectTime.hashCode() : 0);
      result1 = 31 * result1 + (projectName != null ? projectName.hashCode() : 0);
      result1 = 31 * result1 + (projectRemark != null ? projectRemark.hashCode() : 0);
      result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
      return result1;
   }
}
