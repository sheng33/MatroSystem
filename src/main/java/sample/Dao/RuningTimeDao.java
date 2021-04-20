package sample.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuningTimeDao {
    private int id;
    private String nowSite;
    private String train;
    private String line;
    private String nextSite;
    private String state;
    private String runTime;
}
