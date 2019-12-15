package app.servlets;

import app.model.Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class guru_download
 */
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = Model.getInstance();
        List<String> paths = model.pathList();
        List<String> names = model.nameList();
        request.setAttribute("fileNames", paths);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        List<String> namesToDownload = (List<String>) request.getAttribute("fileNames");
        int j=0;
        if (namesToDownload != null && !namesToDownload.isEmpty()) {
            for (String s : namesToDownload) {
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition", "attachment; filename=\""
                        + names.get(j) + "\"");
                FileInputStream fileInputStream = new FileInputStream(s);
                int i;
                while ((i = fileInputStream.read()) != -1) {
                    out.write(i);
                }
                fileInputStream.close();
                out.close();
                j++;
            }
        }
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}