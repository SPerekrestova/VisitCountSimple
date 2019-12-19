package app.database;

import java.sql.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.sql.DriverManager.*;

public class ExecuteStatement {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:sqlite:C:\\sqlite\\course_project.db";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void initConnection() throws SQLException, ClassNotFoundException {
        // opening database connection to MySQL server
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection(url);
        System.out.println("Success!");
        con.setAutoCommit(false);

        // getting Statement object to execute query
        stmt = con.createStatement();
    }

    public static void ExecuteInsert(List<String> query) {
        try {
            con.setAutoCommit(false);
            AtomicInteger i = new AtomicInteger();
            query.forEach((line) -> {
                try {
                    stmt.addBatch(line);
                    i.getAndIncrement();
                    if (i.get() % 1000 == 0 || i.get() == query.size()) {
                        stmt.executeBatch(); // Execute every 1000 items.
                    }
                    con.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet ExecuteSelect(String query) {
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void closeConnection() {
        //close connection ,stmt and resultset here
        try {
            con.close();
        } catch (SQLException se) { /*can't do anything */ }
        try {
            stmt.close();
        } catch (SQLException se) { /*can't do anything */ }
    }

//    public static void main(String[] args) throws SQLException {
//        try {
//            initConnection();
//            rs = ExecuteSelect("SELECT * FROM main.'Group' WHERE group_name = 'ИСТ-722';");
//            while (rs.next()) {
//                String lastName = rs.getString("card");
//                System.out.println(lastName + "\n");
//            }
//            closeConnection();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }
}