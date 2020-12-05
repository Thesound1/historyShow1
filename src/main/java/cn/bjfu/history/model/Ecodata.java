package cn.bjfu.history.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Created by jxy on 2020/12/1 0001 22:29
 */
@Data
@ToString
public class Ecodata {
    private Long datanumId;
    private Integer stationId;
    private Integer deviceId;
    private Integer datatypeId;
    private Date dataTime;
    private Double dataValue;
    private Integer exceptionFlag;
}
