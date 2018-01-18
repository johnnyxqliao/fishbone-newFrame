package Action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static DAO.DaoDB.check;
import static DAO.DaoDB.checkTempId;

@WebServlet("/checkTempProject")
public class checkTempProject extends HttpServlet {
   protected void doPost (HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      response.setContentType("text/html;charset=utf-8");
      String tempProjectId = request.getParameter("tempProjectId");
      PrintWriter out = response.getWriter();
      out.print(checkTempId(Integer.valueOf(tempProjectId)));
//      System.out.println("数据接受成功");
   }

   protected void doGet (HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      this.doPost(request, response);
   }
}
