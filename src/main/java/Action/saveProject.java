package Action;

import DAO.DaoDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/saveProject")
public class saveProject extends HttpServlet {
   protected void doPost (HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      String username = request.getParameter("username");
      String Id = request.getParameter("Id");
      String exportData = request.getParameter("exportData");
      System.out.println("项目数据为：" + exportData+"项目Id为：" + Id+"用户名是：" + username);
      DaoDB.modify(username, Integer.valueOf(Id), exportData);
   }

   protected void doGet (HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      this.doPost(request, response);
   }
}
