package cn.bjfu.history.model;

import lombok.Data;

/**
 * 站点类型，如森林站，荒漠站
 */
@Data
public class StationType {
    private String stationType;
    private Integer stationCount;

    public StationType() {
    }

    public StationType(String stationType, Integer stationCount) {
        this.stationType = stationType;
        this.stationCount = stationCount;
    }

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public Integer getStationCount() {
        return stationCount;
    }

    public void setStationCount(Integer stationCount) {
        this.stationCount = stationCount;
    }
}
