package DAO;

import com.google.gson.Gson;
import fishbone.FishboneEntity;
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

   public static void insert (FishboneEntity fishboneEntity) {//插入数据库
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

   //修改数据
   public static void modify (Integer Id, String result, String saveType) {
      beginTransaction();
      String hql = "from FishboneEntity where projectNumber=?";
      Query query = session.createQuery(hql);
      query.setString(0, String.valueOf(Id));
      List<FishboneEntity> fishboneEntity = query.list();
//      修改并更新数据
      if (saveType.equals("appData")) {
         fishboneEntity.get(0).setResult(result);
      } else {
         fishboneEntity.get(0).setWordResult(result);
      }

      session.update(fishboneEntity.get(0));
      transaction.commit();
      session.close();
      System.out.println("modify successful");
   }

   public static String check (Integer Id) {
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

   public static void main (String[] args) {
//      FishboneEntity fishboneEntity = new FishboneEntity(1, "123", "asdf", "asd1f", "as2df", "asd3f", "a3sdf");
   }
}
