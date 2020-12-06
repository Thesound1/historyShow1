package cn.bjfu.history.model;

import lombok.Data;

/**
 * 用来包装各站点数据的总量
 */
@Data
public class StationDataCount {
    private Integer stationId;
    private String stationName;
    private Integer count;

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public StationDataCount() {
    }

    public StationDataCount(Integer stationId, String stationName, Integer count) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.count = count;
    }
}
