package sample.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LayerDao {
    private int layerId;
    private int nowSiteId;
    private int layerLevel;
    private int state;
}
