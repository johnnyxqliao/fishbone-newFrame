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

   public static void insert (FishboneEntity fishboneEntity) {//插入数据
      beginTransaction();
      System.out.println("执行了一次插入操作");
      session.save(fishboneEntity);
      transaction.commit();
      System.out.println("事务提交");
      session.close();
      sessionFactory.close();
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
      return gson.toJson(TemplateFishboneEntity.get(0).getFishboneEntity());
   }

   public static void updateTempTable (Integer tempProjectId, Integer Id) {//更新鱼骨图关联表
      beginTransaction();
      gson = new Gson();
      String hql = "from TemplateFishboneEntity where tempProjectId=?";
      Query query = session.createQuery(hql);
      query.setString(0, String.valueOf(tempProjectId));
      if (query.list() == null) {//关联表中没有该字段
         session.save(new TemplateFishboneEntity(0, tempProjectId, session.get(FishboneEntity.class, Id)));
      } else {//存在该字段，需要对关联表模板id绑定项目进行更新
         Query queryId = session.createQuery("from TemplateFishboneEntity where tempProjectId=?");
         queryId.setString(0, String.valueOf(tempProjectId));
         session.delete(queryId.list().get(0));//将当前的记录删除，并根据当前条件添加新的记录
         session.save(new TemplateFishboneEntity(0, tempProjectId, session.get(FishboneEntity.class, Id)));
      }
      transaction.commit();
      session.close();
   }

   public static void main (String[] args) {
//      FishboneEntity fishboneEntity = new FishboneEntity(1, "123", "asdf", "asd1f", "as2df", "asd3f", "a3sdf");
      String aa = checkTempId(1);
      System.out.println(aa);
   }
}
