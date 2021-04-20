package sample.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuningTimeDao {
    private String id;
    private int nowSiteId;
    private int trainId;
    private int lineId;
    private int nextSiteId;
    private int state;
    private String runTime;
}
