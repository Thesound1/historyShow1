package cn.bjfu.history.model;

import lombok.Data;

/**
 * 用来包装今天新获取的数据的总量
 */
@Data
public class StationTodayData {
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
}
