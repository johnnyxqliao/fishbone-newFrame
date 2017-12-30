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
      String username = request.getParameter("username");
      System.out.println("项目id是："+projectId+"用户名是："+username);
      DaoDB.remove(Integer.valueOf(projectId),username);
   }

   protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doPost(request, response);
   }
}
