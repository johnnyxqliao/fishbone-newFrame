package fishbone;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "template_fishbone", schema = "fishbone")
public class TemplateFishboneEntity {
   private int id;
   private Integer tempProjectId;
   //   private Integer projectNumber;
   private FishboneEntity fishboneEntity;

   public TemplateFishboneEntity () {
   }

   public TemplateFishboneEntity (int id, Integer tempProjectId, FishboneEntity fishboneEntity) {
      this.id = id;
      this.tempProjectId = tempProjectId;
      this.fishboneEntity = fishboneEntity;
   }

   //定义一对一关系
   @OneToOne
   //定义外键
   @JoinColumn(name = "projectNumber")
   //外键没有发现，忽略错误返回null值（否则会报错）
   @NotFound(action = NotFoundAction.IGNORE)
   public FishboneEntity getFishboneEntity () {
      return fishboneEntity;
   }

   public void setFishboneEntity (FishboneEntity fishboneEntity) {
      this.fishboneEntity = fishboneEntity;
   }

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   public int getId () {
      return id;
   }

   public void setId (int id) {
      this.id = id;
   }

   @Basic
   @Column(name = "tempProjectId")
   public Integer getTempProjectId () {
      return tempProjectId;
   }

   public void setTempProjectId (Integer tempProjectId) {
      this.tempProjectId = tempProjectId;
   }

//   @Basic
//
//   @Column(name = "projectNumber")
//   public Integer getProjectNumber () {
//      return projectNumber;
//   }
//
//   public void setProjectNumber (Integer projectNumber) {
//      this.projectNumber = projectNumber;
//   }

   @Override
   public boolean equals (Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      TemplateFishboneEntity that = (TemplateFishboneEntity) o;

      if (id != that.id) return false;
      if (tempProjectId != null ? !tempProjectId.equals(that.tempProjectId) : that.tempProjectId != null) return false;
//      if (projectNumber != null ? !projectNumber.equals(that.projectNumber) : that.projectNumber != null) return false;

      return true;
   }

   @Override
   public int hashCode () {
      int result = id;
      result = 31 * result + (tempProjectId != null ? tempProjectId.hashCode() : 0);
//      result = 31 * result + (projectNumber != null ? projectNumber.hashCode() : 0);
      return result;
   }
}
