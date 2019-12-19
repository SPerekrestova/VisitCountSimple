package app.servlets;

import app.database.ExecuteStatement;
import app.model.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/create")
public class CreateFileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ExecuteStatement executeStatement;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectGroup = "SELECT card, student FROM main.'Group' WHERE group_name = 'ИСТ-722';";
        String selectSchedule = "SELECT lesson_num, day, time FROM main.'Schedule' WHERE group_name = 'ИСТ-722';";


        try {
            executeStatement.initConnection();
            ResultSet rs =  executeStatement.ExecuteSelect(selectGroup);
            while (rs.next()) {
                String lastName = rs.getString("card");
                System.out.println(lastName + "\n");
            }
            executeStatement.closeConnection();
        } catch (SQLException | ClassNotFoundException e) {}
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}