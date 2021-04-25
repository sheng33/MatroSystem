package sample.Dao;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LayerDao  {
    private int layerId;
    private int nowSiteId;
    private int layerLevel;
    private int state;

    public int getLayerId() {
        return layerId;
    }

    public void setLayerId(int layerId) {
        this.layerId = layerId;
    }

    public int getNowSiteId() {
        return nowSiteId;
    }

    public void setNowSiteId(int nowSiteId) {
        this.nowSiteId = nowSiteId;
    }

    public int getLayerLevel() {
        return layerLevel;
    }

    public void setLayerLevel(int layerLevel) {
        this.layerLevel = layerLevel;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
