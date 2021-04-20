package sample.util;

import java.sql.*;

public  class jdbcUtil {
    public static Connection conn;
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://172.18.103.66: 1433;DataBaseName=metroService", "sheng", "sheng");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    public static String getSiteName(int Id) throws SQLException{
        Statement stmt = conn.createStatement();
        String sql = "Select siteName From SiteTable Where now_siteId = "+Id;
        ResultSet resultSet = stmt.executeQuery(sql);
        while(resultSet.next()){
            return resultSet.getString(1);
        }
        return resultSet.getString(1);
    }

    public static String getTrainName(int Id) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "Select trainName From train_Table Where trainId = "+Id;
        ResultSet resultSet = stmt.executeQuery(sql);
        while(resultSet.next()){
            return resultSet.getString(1);
        }
        return resultSet.getString(1);
    }
    public static String getLineName(int Id) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "Select lineName From LineTable Where lineId = "+Id;
        ResultSet resultSet = stmt.executeQuery(sql);
        while(resultSet.next()){
            return resultSet.getString(1);
        }
        return resultSet.getString(1);
    }

    public static int addLayer(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(sql);
    }

    public static ResultSet getRuningTimeAll() throws SQLException {
        String sql = "SELECT * FROM runingTimeTable";
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    }


}
