package fishbone;

import javax.persistence.*;

@Entity
@Table(name = "fishbone", schema = "fishbone", catalog = "")
public class FishboneEntity {
   private int projectNumber;
   private String userName;
   private String projectTime;
   private String projectName;
   private String projectRemark;
   private String result;
   private String wordResult;

   public FishboneEntity () {
   }

   public FishboneEntity (int projectNumber, String userName, String projectTime, String projectName, String projectRemark, String result, String wordResult) {
      this.projectNumber = projectNumber;
      this.userName = userName;
      this.projectTime = projectTime;
      this.projectName = projectName;
      this.projectRemark = projectRemark;
      this.result = result;
      this.wordResult = wordResult;
   }

   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   @Column(name = "projectNumber")
   public int getProjectNumber () {
      return projectNumber;
   }

   public void setProjectNumber (int projectNumber) {
      this.projectNumber = projectNumber;
   }

   @Basic
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

   @Basic
   @Column(name = "wordResult")
   public String getWordResult () {
      return wordResult;
   }

   public void setWordResult (String wordResult) {
      this.wordResult = wordResult;
   }

   @Override
   public boolean equals (Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      FishboneEntity that = (FishboneEntity) o;

      if (projectNumber != that.projectNumber) return false;
      if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
      if (projectTime != null ? !projectTime.equals(that.projectTime) : that.projectTime != null) return false;
      if (projectName != null ? !projectName.equals(that.projectName) : that.projectName != null) return false;
      if (projectRemark != null ? !projectRemark.equals(that.projectRemark) : that.projectRemark != null) return false;
      if (result != null ? !result.equals(that.result) : that.result != null) return false;
      if (wordResult != null ? !wordResult.equals(that.wordResult) : that.wordResult != null) return false;

      return true;
   }

   @Override
   public int hashCode () {
      int result1 = projectNumber;
      result1 = 31 * result1 + (userName != null ? userName.hashCode() : 0);
      result1 = 31 * result1 + (projectTime != null ? projectTime.hashCode() : 0);
      result1 = 31 * result1 + (projectName != null ? projectName.hashCode() : 0);
      result1 = 31 * result1 + (projectRemark != null ? projectRemark.hashCode() : 0);
      result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
      result1 = 31 * result1 + (wordResult != null ? wordResult.hashCode() : 0);
      return result1;
   }
}
