package sample.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LayerDao {
    private int layerId;
    private int nowSiteId;
    private int layerLevel;
    private int state;
}
