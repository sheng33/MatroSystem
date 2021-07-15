package sample.util;

import sample.Dao.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static sample.util.TimeCompare.CompareNowTime;

public  class jdbcUtil {
    static SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm:ss");
    public static Connection conn;
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://192.168.200.200:1433;DataBaseName=metroService", "sheng", "sheng");
        }  catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
//    static {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            conn = DriverManager.getConnection("jdbc:sqlserver://192.168.200.200:1433;DataBaseName=metroService", "sheng", "sheng");
//        }  catch (SQLException | ClassNotFoundException throwables) {
//            throwables.printStackTrace();
//        }
//    }
    public static String getSiteName(int Id) throws SQLException{
        Statement stmt = conn.createStatement();
        String sql = "Select siteName From SiteTable Where nowSiteId = "+Id;
        ResultSet resultSet = stmt.executeQuery(sql);
        while(resultSet.next()){
            return resultSet.getString(1);
        }
        return "";
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
        System.out.println(siteId);
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
        String sql = "INSERT INTO trainTable(trainName,trainType,serviceTime,lineId)" +
                " Values('" +trainDao.getTrainName()+ "'," +trainDao.getTrainType()+ ",'" +trainDao.getServiceTime()+ "',"+trainDao.getLineId()+")";
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

    public static LineSiteDao getLineSiteByLineId(int lineID) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "Select * From LineSiteTable " +
                "Where ( isStartSite = 1 or isEndSite = 1 ) and lineId = "+lineID;
        ResultSet resultSet = stmt.executeQuery(sql);
        LineSiteDao lineSiteDao = new LineSiteDao();
        while(resultSet.next()){
            lineSiteDao.setLineId(resultSet.getInt(1));
            lineSiteDao.setNowSiteId(resultSet.getInt(2));
            lineSiteDao.setLayerId(resultSet.getInt(3));
            lineSiteDao.setOperationDirection(resultSet.getString(4));
            lineSiteDao.setLinePosition(resultSet.getInt(5));
            lineSiteDao.setIsStartSite(resultSet.getInt(6));
            lineSiteDao.setIsEndSite(resultSet.getInt(7));
        }
        return lineSiteDao;
    }

    public static LineSiteDao getLineSiteByLineIdAndByStieId(int lineId,int siteId) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "Select * From LineSiteTable " +
                "Where  lineId = "+lineId+" AND nowSiteId = "+siteId;
        ResultSet resultSet = stmt.executeQuery(sql);
        LineSiteDao lineSiteDao = new LineSiteDao();
        while(resultSet.next()){
            lineSiteDao.setLineId(resultSet.getInt(1));
            lineSiteDao.setNowSiteId(resultSet.getInt(2));
            lineSiteDao.setLayerId(resultSet.getInt(3));
            lineSiteDao.setOperationDirection(resultSet.getString(4));
            lineSiteDao.setLinePosition(resultSet.getInt(5));
            lineSiteDao.setIsStartSite(resultSet.getInt(6));
            lineSiteDao.setIsEndSite(resultSet.getInt(7));
        }
        return lineSiteDao;
    }

    //比较时间区间函数
    public static boolean isEffectiveDate(Date startTime, Date endTime) throws ParseException {
        Date nowTime = new Date();
        nowTime = timeSdf.parse(timeSdf.format(nowTime));
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
    private static String isRuningBySiteId(int siteId) throws SQLException, ParseException {
        Statement stmt = conn.createStatement();
        String sql = "Select siteName,startTime,endTime From SiteTable " +
                "Where  nowSiteId = "+siteId;
        ResultSet resultSet = stmt.executeQuery(sql);
        while (resultSet.next()){
            if(isEffectiveDate(timeSdf.parse(resultSet.getString(2)),timeSdf.parse(resultSet.getString(3)))){
                return resultSet.getString(1);
            }
        }
        return "";
    }
    public static int getNextSiteId(int lineId,String operation,int linePosition) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "Select nowSiteId From lineSiteTable " +
                "Where  lineId = "+lineId+"AND operationDirection = '"+operation+"' AND linePosition="+linePosition;
        ResultSet resultSet = stmt.executeQuery(sql);
        int nowSiteId = 0;
        while (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }
    public static int getLineTrain(int lineId) throws SQLException {
        Statement stm = conn.createStatement();
        String sql = "SELECT trainId FROM trainTable WHERE lineId="+lineId;
        ResultSet resultSet = stm.executeQuery(sql);
        while(resultSet.next()){
            return  resultSet.getInt(1);
        }
        return 0;
    }

    public static RuningTimeVo getRuningInfo(LineSiteDao dao)  {
        RuningTimeDao timeDao = new RuningTimeDao();
        try {
            String siteName = isRuningBySiteId(dao.getNowSiteId());
            if (!siteName.equals("")){
                timeDao.setNowSite(dao.getNowSiteId());
                timeDao.setNextSite(getNextSiteId(dao.getLineId(),dao.getOperationDirection(),dao.getLinePosition()+1));
                timeDao.setLine(dao.getLineId());
                timeDao.setState(RuningTimeEnum.Inbound.getValue());
                timeDao.setRunTime("06:00:00");
                timeDao.setTrain(getLineTrain(dao.getLineId()));
                System.out.println(timeDao);
                if(addRuningInfo(timeDao)==1){
                    System.out.println("更新成功");
                }
            }
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static int addRuningInfo(RuningTimeDao runingTimeDao) throws SQLException {
        Statement stm = conn.createStatement();
        String sql = "INSERT INTO runingTimeTable(now_siteId,trainId,lineId,next_siteId,state,runTime)" +
                "Values("+runingTimeDao.getNowSite()+","+runingTimeDao.getTrain()+","+runingTimeDao.getLine()+"" +
                ","+runingTimeDao.getNextSite()+","+runingTimeDao.getState()+",'"+runingTimeDao.getRunTime()+"')";
        return stm.executeUpdate(sql);
    }
}
