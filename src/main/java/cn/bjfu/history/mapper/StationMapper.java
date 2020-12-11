package cn.bjfu.history.mapper;

import cn.bjfu.history.model.CityDataCount;
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

    @Select("SELECT\n" +
            "\tsys_prov_city_dist.city city_name,\n" +
            "\tCOUNT(*) data_count \n" +
            "FROM\n" +
            "\tecodata,\n" +
            "\tstation_detail,\n" +
            "\tsys_prov_city_dist \n" +
            "WHERE\n" +
            "\tsys_prov_city_dist.id = station_detail.type2 \n" +
            "\tAND ecodata.station_id = station_detail.station_id \n" +
            "\tAND date( ecodata.data_time ) = CURDATE() \n" +
            "GROUP BY\n" +
            "\tstation_detail.type2")
    List<CityDataCount> getEachCityTodayDataCount();

    @Select("SELECT\n" +
            "\tSUM( lose_num ) \n" +
            "FROM\n" +
            "\tabnormal_log \n" +
            "WHERE\n" +
            "\tMONTH ( ab_begin_time ) = MONTH (\n" +
            "\tCURDATE()) \n" +
            "\tAND YEAR ( ab_begin_time )= YEAR (\n" +
            "\tCURDATE())")
    Integer getCurrentMonthAbnormalDataCount();

    @Select("SELECT\n" +
            "\tDATEDIFF(\n" +
            "\t\tMAX( data_time ),\n" +
            "\tMIN( data_time )) \n" +
            "FROM\n" +
            "\tecodata")
    Integer getExecutionDays();
}
