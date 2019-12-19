package app.servlets;

import app.model.Model;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public UploadServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/upload.jsp");
        requestDispatcher.forward(req, resp);


    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        name= new String(name.getBytes("Cp1251"), StandardCharsets.UTF_8);
                        File filePath = new File("C:\\temp" + File.separator + name);
                        item.write(filePath);
                        app.entities.File file = new app.entities.File(filePath.getAbsolutePath(), name);
                        Model model = Model.getInstance();
                        model.add(file);
                    }
                }
                //File uploaded successfully
                request.setAttribute("gurumessage", "File Uploaded Successfully");
            } catch (Exception ex) {
                request.setAttribute("gurumessage", "File Upload Failed due to " + ex);
            }
        }else {
            request.setAttribute("gurumessage", "No File found");
        }

        doGet(request, response);
    }
}