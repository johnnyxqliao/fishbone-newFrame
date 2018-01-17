package Action;

import DAO.DaoDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/saveProject")
public class saveProject extends HttpServlet {
   protected void doPost (HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      Integer projectNumber = Integer.valueOf(request.getParameter("projectNumber"));
      String exportData = request.getParameter("exportData");
      DaoDB.modify(projectNumber, exportData, "appData");
   }

   protected void doGet (HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      this.doPost(request, response);
   }
}
