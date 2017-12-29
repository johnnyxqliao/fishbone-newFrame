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
   private static Configuration config = null;
   private static SessionFactory sessionFactory = null;
   private static Session session = null;
   private static Transaction transaction = null;
   private static Gson gson = null;

   public static void init () {
      config = new Configuration().configure("/hibernate.cfg.xml");
      sessionFactory = config.buildSessionFactory();
      session = sessionFactory.openSession();
      transaction = session.beginTransaction();
   }

   public static void insert (FishboneEntity fishboneEntity) {//插入数据库
      init();
      System.out.println("执行了一次插入操作");
      session.save(fishboneEntity);
      transaction.commit();
      System.out.println("事务提交");
      session.close();
      sessionFactory.close();
   }

   public static void remove (Integer id) {//删除数据
      System.out.println("开始删除");
      init();
      FishboneEntity fishboneEntity = new FishboneEntity();
      fishboneEntity.setProjectNumber(id);
      session.delete(fishboneEntity);
      transaction.commit();
      session.close();
      System.out.println("删除成功");
   }

   public static String query () {//查询数据
      gson = new Gson();
      init();
      System.out.println("开始查询");
      Query query = session.createQuery("select fishboneEntity from FishboneEntity fishboneEntity");
      List<FishboneEntity> fishboneEntity = query.list();
      System.out.println(gson.toJson(fishboneEntity));
      return gson.toJson(fishboneEntity);
   }

   public static void main (String[] args) {
//   System.out.println("liaoxiaoqiang");
//      FishboneEntity fishboneEntity = new FishboneEntity(1, "asd", "asdf", "asdf");
//      insert(fishboneEntity);
//      remove (4);
      query();
      System.out.println("successful");
   }
}
