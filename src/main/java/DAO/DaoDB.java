package DAO;

import com.google.gson.Gson;
import fishbone.FishboneEntity;
import fishbone.TemplateFishboneEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class DaoDB {
   private static Configuration config = new Configuration().configure("/hibernate.cfg.xml");
   private static SessionFactory sessionFactory = config.buildSessionFactory();
   private static Session session;
   private static Transaction transaction;
   private static Gson gson = null;

   private static void beginTransaction () {//可以修改
      sessionFactory = config.buildSessionFactory();
      session = sessionFactory.openSession();
      transaction = session.beginTransaction();
   }

   public static Integer insert (FishboneEntity fishboneEntity) {//插入数据
      beginTransaction();
      System.out.println("执行了一次插入操作");
      session.save(fishboneEntity);
      //获取当前添加id
      List<FishboneEntity> FishboneEntitylist = session.createQuery("from FishboneEntity as o order by o.projectNumber").list();
      Integer projectNumber = FishboneEntitylist.get(FishboneEntitylist.size()-1).getProjectNumber();
      transaction.commit();
      session.close();
      sessionFactory.close();
      return projectNumber;
   }

   public static void remove (Integer Id) {//删除数据
      System.out.println("开始删除");
      beginTransaction();
      String hql = "from FishboneEntity where projectNumber=?";
      Query query = session.createQuery(hql);
      query.setString(0, String.valueOf(Id));
      List<FishboneEntity> fishboneEntity = query.list();
      session.delete(fishboneEntity.get(0));
      transaction.commit();
      session.close();
      sessionFactory.close();
      System.out.println("删除成功");
   }

   public static String query (String username) {//查询数据
      beginTransaction();
      gson = new Gson();
      System.out.println("开始查询");
      String hql = "from FishboneEntity where userName=?";
      Query query = session.createQuery(hql);
      query.setString(0, username);
      List<FishboneEntity> fishboneEntity = query.list();
      transaction.commit();
      session.close();
      sessionFactory.close();
      System.out.println("查询结果是：" + gson.toJson(fishboneEntity));
      return gson.toJson(fishboneEntity);
   }

   public static void modify (Integer Id, String result, String tempId, String saveType) {//修改数据
      beginTransaction();
      String hql = "from FishboneEntity where projectNumber=?";
      Query query = session.createQuery(hql);
      query.setString(0, String.valueOf(Id));
      List<FishboneEntity> fishboneEntity = query.list();
      if (saveType.equals("appData")) {//修改画图数据
         fishboneEntity.get(0).setResult(result);
      } else {//修改word数据
         fishboneEntity.get(0).setWordResult(result);
      }
      session.update(fishboneEntity.get(0));
      transaction.commit();
      session.close();
      sessionFactory.close();
      System.out.println("modify successful");
   }

   public static String check (Integer Id) {//查询数据
      beginTransaction();
      System.out.println("开始查询");
      String hql = "from FishboneEntity where projectNumber=?";
      Query query = session.createQuery(hql);
      query.setString(0, String.valueOf(Id));
      List<FishboneEntity> fishboneEntity = query.list();
      String checkData = fishboneEntity.get(0).getResult();
      transaction.commit();
      session.close();
      sessionFactory.close();
//      System.out.println(checkData);
      return checkData;
   }

   public static String checkTempId (Integer tempProjectId) {//查询模板Id
      beginTransaction();
      gson = new Gson();
      String hql = "from TemplateFishboneEntity where tempProjectId=?";
      Query query = session.createQuery(hql);
      query.setString(0, String.valueOf(tempProjectId));
      List<TemplateFishboneEntity> TemplateFishboneEntity = query.list();
      transaction.commit();
      session.close();
      sessionFactory.close();
      System.out.println("模板层id查询成功");
      if (TemplateFishboneEntity.size() == 0) {
         return null;
      } else {
         return gson.toJson(TemplateFishboneEntity.get(0).getFishboneEntity());
      }
   }

   public static void updateTempTable (Integer tempProjectId, Integer Id) {//更新鱼骨图关联表
      beginTransaction();
      gson = new Gson();
      // 根据模板层Id查询是否有相关记录
      String hqlTemp = "from TemplateFishboneEntity where tempProjectId=?";
      Query queryTemp = session.createQuery(hqlTemp);
      queryTemp.setString(0, String.valueOf(tempProjectId));
      queryTemp.list();
      if (queryTemp.list().size() != 0) {//模板层id不为空。删除相关记录
         session.delete(queryTemp.list().get(0));//将当前的记录删除，并根据当前条件添加新的记录
      }

      // 根据AppId查询是否有相关记录
      String hqlId = "from TemplateFishboneEntity where fishboneEntity.projectNumber=?";
      Query queryAPP = session.createQuery(hqlId);
      queryAPP.setString(0, String.valueOf(Id));
      if (queryAPP.list().size() != 0) {//模板层id为空，AppId不为空
         session.delete(queryAPP.list().get(0));//将当前的记录删除，并根据当前条件添加新的记录
      }

      // 新建一条绑定记录
      session.save(new TemplateFishboneEntity(0, tempProjectId, session.get(FishboneEntity.class, Id)));
      System.out.println("完成模板Id与app绑定");
      transaction.commit();
      session.close();
   }

   public static void main (String[] args) {
      FishboneEntity fishboneEntity = new FishboneEntity(1, "123", "asd1", "asd1f", "as2df", "asd3f", "a3sdf");
//      updateTempTable(4, 24);
//      check(45);
      System.out.println(insert(fishboneEntity));
//      String hql = "select max(route.rouId) from RouteEntity as route";
//      Query query = (int)query("select max(FishboneEntity.projectNumber) from FishboneEntity").uniqueResult();
//      int maxid = (int)query.uniqueResult();
//      return maxid;
//      query("123");
   }
}
