package sample.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineSiteDao {
    private int lineId;
    private int nowSiteId;
    private int layerId;
    private String operationDirection;
    private int linePosition;
    private int isStartSite;
    private int isEndSite;
}
