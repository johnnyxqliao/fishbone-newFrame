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
      Integer projectNumber = Integer.valueOf(request.getParameter("projectNumber"));
      String exportData = request.getParameter("exportData");
      String tempId = request.getParameter("tempId");
      DaoDB.modify(projectNumber, exportData, tempId, "appData");
      if (0 != Integer.valueOf(tempId)) {//当前存在模板层项目Id,对模板项目和app项目进行绑定
         DaoDB.updateTempTable(Integer.valueOf(tempId), projectNumber);
      }
   }

   protected void doGet (HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      this.doPost(request, response);
   }
}