package Action;

import DAO.DaoDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeProject")
public class removeProject extends HttpServlet {
   protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String projectId = request.getParameter("counter");
      System.out.println("项目id是："+projectId);
      DaoDB.remove(Integer.valueOf(projectId));
   }

   protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doPost(request, response);
   }
}
