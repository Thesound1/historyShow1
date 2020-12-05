package cn.bjfu.history.mapper;

import cn.bjfu.history.model.TCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jxy on 2020/12/1 0001 22:30
 */
@Component
@Mapper
public interface EcodataMapper {
    @Select("SELECT DATE(data_time) AS date,\n" +
            "       COUNT(*) AS count\n" +
            "FROM ecodata\n" +
            "GROUP BY DATE(data_time)\n")
    public List<TCount> getCountByDay();

    @Select("SELECT DATE(DATE_SUB(data_time, INTERVAL DAYOFMONTH(data_time) - 1 DAY)) AS date,\n" +
            "       COUNT(*) AS count\n" +
            "FROM ecodata\n" +
            "GROUP BY DATE(DATE_SUB(data_time, INTERVAL DAYOFMONTH(data_time) - 1 DAY))\n")
    public List<TCount> getCountByMonth();

    @Select("SELECT DATE(DATE_SUB(data_time, INTERVAL DAYOFYEAR(data_time) - 1 DAY)) AS date,\n" +
            "       COUNT(*) AS count\n" +
            "FROM ecodata\n" +
            "GROUP BY DATE(DATE_SUB(data_time, INTERVAL DAYOFYEAR(data_time) - 1 DAY))\n")
    public List<TCount> getCountByYear();

    @Select("select Count(*) from ecodata")
    public Integer getTotalCount();

    @Select("select  count(*)  from  ecodata e  where  date(e.data_time) = curdate()")
    public Integer getCountToday();

    @Select("select  count(*)  from  ecodata e  where  date(e.data_time) = curdate() - 1")
    public Integer getCountYesterday();


    @Select("select  count(DISTINCT(e.station_id)) from  ecodata e  ")
    public Integer getTotalStations();

    @Select("SELECT `exceptionFlag` AS `date`,\n" +
            "       COUNT(*) AS count\n" +
            "FROM ecodata\n" +
            "GROUP BY `exceptionFlag`")
    public List<TCount> getExceptionFlag();
}
