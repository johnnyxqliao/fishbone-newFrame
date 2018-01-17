package Action;

import DAO.DaoDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static DAO.DaoDB.check;

@WebServlet("/checkProject")
public class checkProject extends HttpServlet {
   protected void doPost (HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      response.setContentType("text/html;charset=utf-8");
      String projectNumber = request.getParameter("projectNumber");
      PrintWriter out = response.getWriter();
      out.print(check(Integer.valueOf(projectNumber)));
      System.out.println("数据接受成功");
   }

   protected void doGet (HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      this.doPost(request, response);
   }
}
