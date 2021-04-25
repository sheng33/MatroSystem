package sample.util;

import sample.Dao.*;

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
        String sql = "Select siteName From SiteTable Where nowSiteId = "+Id;
        ResultSet resultSet = stmt.executeQuery(sql);
        while(resultSet.next()){
            return resultSet.getString(1);
        }
        return resultSet.getString(1);
    }
    public static int getSiteId(String siteName) throws SQLException{
        Statement stmt = conn.createStatement();
        String sql = "Select nowSiteId From SiteTable Where siteName = '"+siteName+"'";
        ResultSet resultSet = stmt.executeQuery(sql);
        while(resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }

    public static String getTrainName(int Id) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "Select trainName From trainTable Where trainId = "+Id;
        ResultSet resultSet = stmt.executeQuery(sql);
        while(resultSet.next()){
            return resultSet.getString(1);
        }
        return resultSet.getString(1);
    }
    public static ResultSet getAllLineInfo() throws SQLException{
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM LineTable";
        return stmt.executeQuery(sql);
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
    public static int getLineId(String name) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "Select lineId From LineTable Where lineName = '"+name+"'";
        ResultSet resultSet = stmt.executeQuery(sql);
        while(resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }
    public static int getLayerId(int siteId,int layerLevel) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "Select layerId FROM layerTable Where nowSiteId="+siteId+"AND layerLevel="+layerLevel;
        ResultSet resultSet = stmt.executeQuery(sql);
        while(resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }
    public static ResultSet getLayerAllBySiteId(int siteId) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "Select * FROM layerTable Where nowSiteId="+siteId;
        System.out.println(sql);
        return  stmt.executeQuery(sql);
    }


    public static ResultSet getRuningTimeAll() throws SQLException {
        String sql = "SELECT * FROM runingTimeTable";
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    }
    public static int addSite(SiteDao siteDao) throws SQLException{
        String sql = "INSERT INTO SiteTable(siteName,startTime,endTime)" +
                "VALUES('"+siteDao.getSiteName()+"','"+siteDao.getStartTime()+"','"+siteDao.getEndTime()+"')";
        Statement statement = conn.createStatement();
        return statement.executeUpdate(sql);
    }
    public static int addLayer(LayerDao layerDao) throws SQLException {
        String sql = "INSERT  INTO layerTable(nowSiteId,layerlevel,state)" +
                "VALUES("+layerDao.getNowSiteId()+","+layerDao.getLayerLevel()+","+layerDao.getState()+")";
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(sql);
    }

    public static int addTrain(TrainDao trainDao) throws SQLException {
        String sql = "INSERT INTO trainTable(trainName,trainType,serviceTime)" +
                " Values('" +trainDao.getTrainName()+ "'," +trainDao.getTrainType()+ ",'" +trainDao.getServiceTime()+ "')";
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(sql);
    }
    public static int addLine(LineDao lineDao) throws SQLException{
        String sql = "INSERT INTO LineTable(lineName,firstRunTime,endRunTime)" +
                " Values('" +lineDao.getLineName()+ "','" +lineDao.getFirstRunTime()+ "','" +lineDao.getEndRunTime()+ "')";
        System.out.println(sql);
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(sql);
    }

    public static int addLineSiteTable(LineSiteDao lineSiteDao) throws SQLException {
        String sql = "INSERT INTO lineSiteTable(lineId,nowSiteId,layerId,operationDirection,linePosition,isStartSite,isEndSite)" +
                "VALUES ("+lineSiteDao.getLineId()+","+lineSiteDao.getNowSiteId()+","+lineSiteDao.getLayerId()+",'"+lineSiteDao.getOperationDirection()+"',"+lineSiteDao.getLinePosition()+"" +
                ","+lineSiteDao.getIsStartSite()+","+lineSiteDao.getIsEndSite()+")";
        System.out.println(sql);
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(sql);
    }

    public static int getSiteNameId(String text) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "Select nowSiteId From SiteTable Where siteName = '"+text+"'";
        ResultSet resultSet = stmt.executeQuery(sql);
        while(resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }
}
