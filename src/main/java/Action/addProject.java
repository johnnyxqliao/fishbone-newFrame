package Action;

import DAO.DaoDB;
import fishbone.FishboneEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addProject")
public class addProject extends HttpServlet {
   protected void doPost (HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      String projectId = request.getParameter("projectNumber");
      String projectTime = request.getParameter("projectTime");
      String projectName = request.getParameter("projectName");
      String projectRemark = request.getParameter("projectRemark");
      FishboneEntity fishboneEntity = new FishboneEntity(Integer.valueOf(projectId), projectTime, projectName, projectRemark);
      DaoDB.insert(fishboneEntity);
      System.out.println("add" + fishboneEntity + "successful");
   }

   protected void doGet (HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      this.doPost(request, response);
   }
}
