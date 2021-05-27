package sample.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sample.util.jdbcUtil;

import java.sql.SQLException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuningTimeDao {
    private int id;
    private int nowSite;
    private int train;
    private int line;
    private int nextSite;
    private int state;
    private String runTime;

    public RuningTimeVo getRuningTimeVo() throws SQLException {
        RuningTimeVo vo = new RuningTimeVo();
        vo.setId(this.getId());
        vo.setNowSite(jdbcUtil.getSiteName(this.getNowSite()));
        vo.setTrain(jdbcUtil.getTrainName(this.getTrain()));
        vo.setLine(jdbcUtil.getLineName(this.getLine()));
        vo.setNextSite(jdbcUtil.getSiteName(this.getNextSite()));
        vo.setState(RuningTimeEnum.getName(this.getState()));
        vo.setRunTime(this.getRunTime());
        return vo;
    }
}
