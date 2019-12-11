package app.database;

import java.sql.*;

public class ExecuteStatement {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:sqlite:C:\\sqlite\\course_project.db";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void initConnection() throws SQLException {
        // opening database connection to MySQL server
        con = DriverManager.getConnection(url);
        System.out.println("Success!");

        // getting Statement object to execute query
        stmt = con.createStatement();
    }
    public static void ExecuteInsert(String query) {
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void ExecuteSelect(String query) {
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeConnection() {
        //close connection ,stmt and resultset here
        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
    }

    public static void main(String[] args) throws SQLException {
        initConnection();
    }
}
