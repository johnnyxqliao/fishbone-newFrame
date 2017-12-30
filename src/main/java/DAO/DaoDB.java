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

   private static void beginTransaction () {
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

   public static void remove (Integer Id, String username) {//删除数据
      System.out.println("开始删除");
      beginTransaction();
//      FishboneEntity fishboneEntity = new FishboneEntity();
//      fishboneEntity.setProjectId(id);
      String hql = "from FishboneEntity where projectId=? and userName=?";
      Query query = session.createQuery(hql);
      query.setString(0, String.valueOf(Id));
      query.setString(1, username);
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

   public static void modify (String username, Integer Id, String result) {
      beginTransaction();
      System.out.println("开始修改");
//      条件查询
      String hql = "from FishboneEntity where projectId=? and userName=?";
      Query query = session.createQuery(hql);
      query.setString(0, String.valueOf(Id));
      query.setString(1, username);
      List<FishboneEntity> fishboneEntity = query.list();
//      修改并更新数据
      fishboneEntity.get(0).setResult(result);
      session.update(fishboneEntity.get(0));
      transaction.commit();
      session.close();
      System.out.println("modify successful");
   }

   public static String check (String username, Integer Id) {
      beginTransaction();
      System.out.println("开始查询");
//      条件查询
      String hql = "from FishboneEntity where projectId=? and userName=?";
      Query query = session.createQuery(hql);
      query.setString(0, String.valueOf(Id));
      query.setString(1, username);
      List<FishboneEntity> fishboneEntity = query.list();
//      修改并更新数据
      String checkData = fishboneEntity.get(0).getResult();
      transaction.commit();
      session.close();
      System.out.println("check successful");
      return checkData;
   }

   public static void main (String[] args) {
//   System.out.println("liaoxiaoqiang");
//      FishboneEntity fishboneEntity = new FishboneEntity(1, "asd", "asdf", "asdf");
//      insert(fishboneEntity);
//      remove (4);
//      query("from FishboneEntity fishboneEntity where userName="+"123");
//      System.out.println(check ("123", 2));
//      ;
//      System.out.println("successful");
      modify("123", 1, "asdfasd");
   }
}
