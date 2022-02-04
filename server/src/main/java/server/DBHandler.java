package server;

import java.sql.*;

public class DBHandler {
    private static Connection connection;
    private static PreparedStatement prStAuth;
    private static PreparedStatement prStReg;
    private static PreparedStatement prStChange;

    public static boolean connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:userss.db");
            try {
                prStAuth = connection.prepareStatement("SELECT name FROM userss WHERE login = ? and password = ?;");
                prStReg = connection.prepareStatement("INSERT INTO userss (login, password, name) VALUES (?, ?, ?);");
                prStChange = connection.prepareStatement("UPDATE userss SET 'name' = ? WHERE name = ?;");
                return true;
            } catch (SQLException e){
                e.printStackTrace();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            disconnect();
            return false;
        }
    }

    public static void disconnect() {
        try {
            prStAuth.close();
            prStReg.close();
            prStChange.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getNicknameByLoginAndPassword(String login, String password){
        String name = null;
        try {
            prStAuth.setString(1, login);
            prStAuth.setString(2, password);
            ResultSet rs = prStAuth.executeQuery();
            while(rs.next()){
                name = rs.getString(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static boolean registration(String login, String password, String name){
        try {
            prStReg.setString(1, login);
            prStReg.setString(2, password);
            prStReg.setString(3, name);
            prStReg.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean changeName(String name){
        try {
            prStChange.setString(1, name);
            prStChange.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

