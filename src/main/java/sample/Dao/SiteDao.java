package sample.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteDao {
    private int nowSiteId;
    private String siteName;
    private String startTime;
    private String endTime;
}
