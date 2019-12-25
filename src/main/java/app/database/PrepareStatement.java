package app.database;

import java.sql.SQLException;
import java.util.*;

/*
    Class for preparing statement with inbound data for executing in DB
 */

public class PrepareStatement {

    private ExecuteStatement executeStatement;

    // Preparing inserting info about groups
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
                ExecuteStatement.initConnection();
                ExecuteStatement.ExecuteInsert(finalQuery);
                ExecuteStatement.closeConnection();
            }
        }
    }
    // preparing inserting info for schedule
    public void prepareSchedulerInsert(HashMap scheduler) throws SQLException, ClassNotFoundException {

        executeStatement = new ExecuteStatement();
        StringBuilder queryString = new StringBuilder();
        StringBuilder first = new StringBuilder();
        List<String> finalQuery = new ArrayList<>();
        if (scheduler != null) {
            try {
                Map.Entry<String, TreeMap<Integer, TreeSet>> entry = (Map.Entry<String, TreeMap<Integer, TreeSet>>) scheduler.entrySet().iterator().next();
                String groupName = entry.getKey();
                first.append("INSERT INTO main.'Schedule' (group_name, lesson_num, day, time) VALUES('").append(groupName).append("', ");
                TreeMap<Integer, TreeSet> lessonsMap = entry.getValue();
                int lesson_num = 0;
                for (Map.Entry<Integer, TreeSet> line : lessonsMap.entrySet()) {
                        for (Object value : line.getValue()) {
                            lesson_num++;
                            queryString.append(first).append(lesson_num).append(", '").append(line.getKey()).append("', '").append(value.toString()).append("');");
                            finalQuery.add(queryString.toString());
                            queryString = new StringBuilder();
                        }
                }

            } catch (Exception у) {
                у.printStackTrace();
            }
            finally {
                ExecuteStatement.initConnection();
                ExecuteStatement.ExecuteInsert(finalQuery);
                ExecuteStatement.closeConnection();
            }
        }
    }
}
