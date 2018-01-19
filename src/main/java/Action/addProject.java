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
import java.util.Map;

@WebServlet("/addProject")
public class addProject extends HttpServlet {
   protected void doPost (HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      Integer projectNumber = Integer.valueOf(request.getParameter("projectNumber"));
      String projectTime = request.getParameter("projectTime");
      String projectName = request.getParameter("projectName");
      String projectRemark = request.getParameter("projectRemark");
      Map<String, String> userInfo = (Map<String, String>) request.getSession().getAttribute("userInfo");
      String userName = userInfo.get("username");
      String result = request.getParameter("result");
      String wordResult = request.getParameter("wordResult");
      FishboneEntity fishboneEntity = new FishboneEntity(projectNumber, userName, projectTime, projectName, projectRemark, result, wordResult);
      PrintWriter out = response.getWriter();
      out.print(DaoDB.insert(fishboneEntity));
   }

   protected void doGet (HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      this.doPost(request, response);
   }
}
