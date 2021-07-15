package sample.controller;

import sample.Dao.LineDao;
import sample.Dao.LineSiteDao;
import sample.Dao.RuningTimeVo;
import sample.util.jdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CoreController {
    public void init() throws SQLException, ParseException {
        ResultSet lines = jdbcUtil.getAllLineInfo();
        ArrayList<LineDao> lineList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取所有线路
        while(lines.next()){
            // 判断线路运行时间是否在开始结束时间内
            if (sdf.parse(lines.getString(3)).compareTo(new Date())<=0&&sdf.parse(lines.getString(4)).compareTo(new Date())>=0){
                LineDao lineDao = new LineDao();
                lineDao.setLineId(lines.getInt(1));
                lineDao.setLineName(lines.getString(2));
                lineDao.setFirstRunTime(lines.getString(3));
                lineDao.setEndRunTime(lines.getString(4));
                lineList.add(lineDao);
            }
        }
        //将线路放入线路站点表中查询出起始站
        ArrayList<LineSiteDao> runLineList = new ArrayList();
        lineList.forEach(dao ->{
            try {
                runLineList.add(jdbcUtil.getLineSiteByLineId(dao.getLineId()));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } );
        //移除错误站点
        runLineList.removeIf(this::checkLineSiteDao);
        //查询所有起始站运营是否开始运行了
        ArrayList<RuningTimeVo> runingTimeVos = new ArrayList<>();
        runLineList.forEach(dao->{
            runingTimeVos.add(jdbcUtil.getRuningInfo(dao));
        });
    }
    private boolean checkLineSiteDao(LineSiteDao dao){
        if (dao.getLineId()==0) return true;
        if (dao.getNowSiteId()==0) return true;
        if (dao.getLayerId()==0) return true;
        if (dao.getOperationDirection()==null) return true;
        if (dao.getLinePosition()==0) return true;
        return false;
    }
}
