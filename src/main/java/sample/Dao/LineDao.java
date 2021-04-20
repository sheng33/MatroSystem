package sample.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineDao {
    private int lineId;
    private String lineName;
    private Date firstRunTime;
    private Date endRunTime;
}
