package sample.util;

import sample.Dao.LayerDao;
import sample.Dao.LineSiteDao;
import sample.Dao.TrainDao;

import java.sql.*;

public  class jdbcUtil {
    public static void main(String[] args) {

    }
    public static Connection conn;
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://172.18.103.66:1433;DataBaseName=metroService", "sheng", "sheng");
        }  catch (SQLException | ClassNotFoundException throwables) {
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

    public static ResultSet getRuningTimeAll() throws SQLException {
        String sql = "SELECT * FROM runingTimeTable";
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    }

    public static int addLayer(LayerDao layerDao) throws SQLException {
        String sql = "INSERT  INTO layerTable(nowSiteId,layerlevel,state)" +
                "VALUES("+layerDao.getNowSiteId()+","+layerDao.getLayerLevel()+","+layerDao.getState()+")";
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(sql);
    }

    public static int addTrain(TrainDao trainDao) throws SQLException {
        String sql = "INSERT INTO trainTable(trainName,trainType,serviceTime)" +
                " Values("+trainDao.getTrainName()+","+trainDao.getTrainType()+","+trainDao.getServiceTime()+")";
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(sql);
    }

    public static int addLineSiteTable(LineSiteDao lineSiteDao) throws SQLException {
        String sql = "INSERT INTO lineSiteTable(nowSiteId,layerId,operationDirection,linePosition,isStartSite,isEndSite)" +
                "VALUES ("+lineSiteDao.getNowSiteId()+","+lineSiteDao.getLayerId()+","+lineSiteDao.getLayerId()+","+lineSiteDao.getOperationDirection()+","+lineSiteDao.getLinePosition()+"" +
                ","+lineSiteDao.getIsStartSite()+","+lineSiteDao.getIsEndSite()+")";
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(sql);
    }

}
