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

@WebServlet("/uploadGroup")
public class UploadGroupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model model = Model.getInstance();
        PrepareStatement stmt = new PrepareStatement();
        List<String> paths = model.pathList();
        HashMap resultMap = new HashMap<>();
        if (paths != null && !paths.isEmpty()) {
            for (String s : paths) {
               resultMap = Read.readGroupFromExcel(s);
            }
            try {
                stmt.prepareGroupInsert(resultMap);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        req.setAttribute("groupList", resultMap);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/uploadGroup.jsp");
        requestDispatcher.forward(req, resp);
    }
}
