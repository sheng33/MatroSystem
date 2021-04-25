package sample.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TempSiteInfo {
    private int siteId;
    private String siteName;
    private String index;
    private String layerId;
    private String layerName;
    private String siteType;
}
