package sample.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
