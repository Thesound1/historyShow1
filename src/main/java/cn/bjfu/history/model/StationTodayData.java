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
}
