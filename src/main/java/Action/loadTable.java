package Action;

import DAO.DaoDB;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loadTable")
public class loadTable extends HttpServlet {
   protected void doPost (HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
//      request.setCharacterEncoding("text/html");
      Gson gson = new Gson();
      response.setContentType("text/html;charset=utf-8");
      String queryResult = DaoDB.query();
      queryResult = "{" + "\"data\"" + ":" + queryResult + "}";
      PrintWriter out = response.getWriter();
      System.out.println(queryResult);
      out.print(queryResult);
   }

   protected void doGet (HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      this.doPost(request, response);
   }
}
