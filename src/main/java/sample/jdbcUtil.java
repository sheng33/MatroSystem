package sample;

import java.sql.*;

public class jdbcUtil {
    public static Connection conn;
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://172.18.103.66: 1433;DataBaseName=metroService", "sheng", "sheng");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    public int addLayer(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(sql);
    }


}
