package app.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.sql.DriverManager.*;

public class PrepareStatement {

    private ExecuteStatement executeStatement;

    public void prepareGroupInsert(HashMap group) throws SQLException, ClassNotFoundException {

        executeStatement = new ExecuteStatement();
        StringBuilder queryString = new StringBuilder();
        StringBuilder first = new StringBuilder();
        List<String> finalQuery = new ArrayList<>();
        if (group != null) {
            try {
                Map.Entry<String, HashMap<String, String>> entry = (Map.Entry<String, HashMap<String, String>>) group.entrySet().iterator().next();
                String groupName = entry.getKey();
                first.append("INSERT INTO main.'Group' (group_name, card, student) VALUES('").append(groupName).append("', ");
                HashMap<String, String> studentsMap = entry.getValue();
                for (Map.Entry<String, String> line : studentsMap.entrySet()) {
                    queryString.append(first).append(line.getKey()).append(", '").append(line.getValue()).append("');");
                    finalQuery.add(queryString.toString());
                    queryString = new StringBuilder();
                }

            } catch (Exception у) {
                у.printStackTrace();
            }
            finally {
                executeStatement.initConnection();
                executeStatement.ExecuteInsert(finalQuery);
                executeStatement.closeConnection();
            }
        }
    }

    public void prepareSchedulerInsert(HashMap scheduler) throws SQLException, ClassNotFoundException {

        executeStatement = new ExecuteStatement();
        StringBuilder queryString = new StringBuilder();
        StringBuilder first = new StringBuilder();
        List<String> finalQuery = new ArrayList<>();
        if (scheduler != null) {
            try {
                Map.Entry<String, HashMap<String, String>> entry = (Map.Entry<String, HashMap<String, String>>) scheduler.entrySet().iterator().next();
                String groupName = entry.getKey();
                first.append("INSERT INTO main.'Group' (group_name, card, student) VALUES('").append(groupName).append("', ");
                HashMap<String, String> studentsMap = entry.getValue();
                for (Map.Entry<String, String> line : studentsMap.entrySet()) {
                    queryString.append(first).append(line.getKey()).append(", '").append(line.getValue()).append("');");
                    finalQuery.add(queryString.toString());
                    queryString = new StringBuilder();
                }

            } catch (Exception у) {
                у.printStackTrace();
            }
            finally {
                executeStatement.initConnection();
                executeStatement.ExecuteInsert(finalQuery);
                executeStatement.closeConnection();
            }
        }
    }
}
