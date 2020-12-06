package cn.bjfu.history.mapper;

import cn.bjfu.history.model.ProvinceStation;
import cn.bjfu.history.model.StationDataCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface StationMapper {
    @Select("SELECT\n" +
            "\te.station_id, \n" +
            "\ts.station_name,\n" +
            "\tcount(*) count\n" +
            "FROM\n" +
            "\tecodata AS e\n" +
            "\tINNER JOIN\n" +
            "\tstation_detail AS s\n" +
            "\tON \n" +
            "\t\te.station_id = s.station_id\n" +
            "WHERE\n" +
            "\tdate(e.data_time) = curdate()\n" +
            "GROUP BY\n" +
            "\tstation_id " +
            "ORDER BY count ASC")
    List<StationDataCount> getStationTodayData();

    @Select("SELECT\n" +
            "\tsys_prov_city_dist.province,COUNT(type1) count\n" +
            "FROM\n" +
            "\tsys_prov_city_dist,\n" +
            "\tstation_detail\n" +
            "WHERE\n" +
            "\tsys_prov_city_dist.id = station_detail.type1\n" +
            "GROUP BY\n" +
            "\tstation_detail.type1")
    List<ProvinceStation> getProvinceStationCount();

    @Select("SELECT\n" +
            "\te.station_id, \n" +
            "\ts.station_name,\n" +
            "\tcount(*) count\n" +
            "FROM\n" +
            "\tecodata AS e\n" +
            "\tINNER JOIN\n" +
            "\tstation_detail AS s\n" +
            "\tON \n" +
            "\t\te.station_id = s.station_id\n" +
            "WHERE\n" +
            "\tdate(e.data_time) >= date_sub(curdate(),interval 1 month)\n" +
            "GROUP BY\n" +
            "\tstation_id ")
    List<StationDataCount> getStationsThisMonthDataCount();
}
