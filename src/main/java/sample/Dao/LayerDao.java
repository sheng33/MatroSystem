package sample.Dao;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class LayerDao  {
    private int layerId;
    private int nowSiteId;
    private int layerLevel;
    private int state;
}
