package sample.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineDao {
    private int lineId;
    private String lineName;
    private String firstRunTime;
    private String endRunTime;

    public LineDao(String lineName, String firstRunTime, String endRunTime) {
        this.lineName = lineName;
        this.firstRunTime = firstRunTime;
        this.endRunTime = endRunTime;
    }
}
