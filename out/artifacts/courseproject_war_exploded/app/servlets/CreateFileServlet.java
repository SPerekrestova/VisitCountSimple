package app.servlets;

import app.database.ExecuteStatement;
import app.parsing.Write;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;


@WebServlet("/create")
public class CreateFileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectGroup = "SELECT card, student FROM main.'Group' WHERE group_name = 'ИСТ-722';";
        String selectSchedule = "SELECT lesson_num, day, time FROM main.'Schedule' WHERE group_name = 'ИСТ-722';";
        HashMap<Integer, String> groupMap = new HashMap<>();
        HashMap<Integer, SortedSet<String>> lessons = new HashMap<>();
        SortedSet<String> time = new TreeSet<String>();
        int day = 0;
        try {
            ExecuteStatement.initConnection();
            ResultSet rs =  ExecuteStatement.ExecuteSelect(selectGroup);
            while (rs.next()) {
                String card = rs.getString("card");
                String student = rs.getString("student");
                groupMap.put(Integer.valueOf(card), student);
            }
            LinkedHashMap<Integer, String> sortedGroupMap =
                    groupMap.entrySet().stream().
                            sorted(Map.Entry.comparingByValue()).
                            collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                    (e1, e2) -> e1, LinkedHashMap::new));
            ExecuteStatement.closeConnection();

            ExecuteStatement.initConnection();
            ResultSet rs2 =  ExecuteStatement.ExecuteSelect(selectSchedule);
            while (rs2.next()) {
                day = Integer.parseInt(rs2.getString("day"));
                //день помещается впервые
                if (!lessons.containsKey(day)) {
                    time = new TreeSet<>();
                    time.add(rs2.getString("time"));
                } //день уже есть
                else {
                    time.add(rs2.getString("time"));
                }
                lessons.put(day, time);
            }
            Map<Integer, SortedSet<String>> sortedLessons = new TreeMap<Integer, SortedSet<String>>(lessons);
            ExecuteStatement.closeConnection();

            Write.writeIntoExcel(sortedGroupMap, sortedLessons);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        request.setAttribute("result", "Success!");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/create.jsp");
        requestDispatcher.forward(request, response);
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}