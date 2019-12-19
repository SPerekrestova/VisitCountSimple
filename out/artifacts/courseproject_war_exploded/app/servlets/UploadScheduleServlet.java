package app.servlets;

import app.database.PrepareStatement;
import app.model.Model;
import app.parsing.Read;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/uploadSchedule")
public class UploadScheduleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model model = Model.getInstance();
        PrepareStatement stmt = new PrepareStatement();
        List<String> paths = model.pathList();
        HashMap resultMap = new HashMap<>();
        if (paths != null && !paths.isEmpty()) {
            for (String s : paths) {
               resultMap = (HashMap) Read.readScheduleFromExcel(s);
            }
            try {
                stmt.prepareSchedulerInsert(resultMap);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        req.setAttribute("schedule", resultMap);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/uploadSchedule.jsp");
        requestDispatcher.forward(req, resp);
    }
}
