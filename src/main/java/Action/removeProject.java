package Action;

import DAO.DaoDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/removeProject")
public class removeProject extends HttpServlet {
   protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String projectNumber = request.getParameter("projectNumber");
//      Map<String, String> userInfo = (Map<String, String>) request.getSession().getAttribute("userInfo");
//      String userName = userInfo.get("username");
      System.out.println("项目id是："+projectNumber);
      DaoDB.remove(Integer.valueOf(projectNumber));
   }

   protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doPost(request, response);
   }
}
