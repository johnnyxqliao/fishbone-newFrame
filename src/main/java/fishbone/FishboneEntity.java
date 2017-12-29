package fishbone;

import javax.persistence.*;

@Entity
@Table(name = "fishbone", schema = "fishbone", catalog = "")
public class FishboneEntity {
   private Integer projectNumber;
   private String projectTime;
   private String projectName;
   private String projectRemark;

   public FishboneEntity () {
   }

   public FishboneEntity (Integer projectNumber, String projectTime, String projectName, String projectRemark) {
      this.projectNumber = projectNumber;
      this.projectTime = projectTime;
      this.projectName = projectName;
      this.projectRemark = projectRemark;
   }

   @Id
   @Column(name = "projectNumber")
   public Integer getProjectNumber () {
      return projectNumber;
   }

   public void setProjectNumber (Integer projectNumber) {
      this.projectNumber = projectNumber;
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

   @Override
   public boolean equals (Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      FishboneEntity that = (FishboneEntity) o;

      if (projectNumber != null ? !projectNumber.equals(that.projectNumber) : that.projectNumber != null) return false;
      if (projectTime != null ? !projectTime.equals(that.projectTime) : that.projectTime != null) return false;
      if (projectName != null ? !projectName.equals(that.projectName) : that.projectName != null) return false;
      if (projectRemark != null ? !projectRemark.equals(that.projectRemark) : that.projectRemark != null) return false;

      return true;
   }

   @Override
   public int hashCode () {
      int result = projectNumber != null ? projectNumber.hashCode() : 0;
      result = 31 * result + (projectTime != null ? projectTime.hashCode() : 0);
      result = 31 * result + (projectName != null ? projectName.hashCode() : 0);
      result = 31 * result + (projectRemark != null ? projectRemark.hashCode() : 0);
      return result;
   }
}
