package app.servlets;

import app.model.Model;
import app.parsing.Read;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ResultServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model model = Model.getInstance();
        List<String> names = model.list();
        if (names != null && !names.isEmpty()) {
            for (String s : names) {
                Read.readFromExcel(s);
            }
        }
        req.setAttribute("fileNames", names);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/result.jsp");
        requestDispatcher.forward(req, resp);
    }
}
