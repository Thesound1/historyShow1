package cn.bjfu.history.controller;

import cn.bjfu.history.model.CityDataCount;
import cn.bjfu.history.model.ProvinceStation;
import cn.bjfu.history.model.ResultModel;
import cn.bjfu.history.model.StationDataCount;
import cn.bjfu.history.service.StationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StationController {
    @Autowired
    private StationService stationService;

    /**
     * 按年份、月份、日获取数据的总量
     *
     * @param type 可选值为：year,month,day。大小写不敏感
     * @return
     */
    @ApiOperation(value = "按时间划分，获取数据量")
    @ApiImplicitParam(name = "type", value = "时间划分范围，具体为年，月，日。可选值为：HalfYear,month,day。大小写不敏感", required = true, paramType = "path")
    @GetMapping("/getCountsByDate/{type}")
    public ResultModel getCountsPercent(@PathVariable String type) {
        List<List> countsPercent = stationService.getCountsPercent(type);
        if (countsPercent != null && countsPercent.size() != 0) {
            return ResultModel.ok(countsPercent);
        }
        return ResultModel.error("查询失败");
    }


    /**
     * 获取各站点类型的数量的百分比
     *
     * @return
     */
    @ApiOperation("获取各种类型的生态站，在数量上的占比情况，如森林站占比80%，城市站占比5%")
    @GetMapping("/getStationTypeCountPercent")
    public ResultModel getStationTypeCount() {
        Map<String, String> stationTypeCountPercent = stationService.getStationTypeCountPercent();
        if (stationTypeCountPercent != null && stationTypeCountPercent.size() != 0) {
            return ResultModel.ok(stationTypeCountPercent);
        }
        return ResultModel.error("查询失败");
    }

    /**
     * 今日各个站点收集的数据
     *
     * @return
     */
    @ApiOperation("今日各个站点收集的数据")
    @GetMapping("/getStationTodayData")
    public ResultModel getStationTodayData() {
        List<StationDataCount> stationTodayData = stationService.getStationTodayData();
        if (stationTodayData != null && stationTodayData.size() != 0) {
            return ResultModel.ok(stationTodayData);
        }
        return ResultModel.error("查询失败");
    }

    /**
     * 本月各站点收集数据量
     *
     * @return
     */
    @ApiOperation("本月各站点收集数据量")
    @GetMapping("/getStationsThisMonthDataCount")
    public ResultModel getStationThisMonthData() {
        List<StationDataCount> StationsThisMonthDataCount = stationService.getStationsThisMonthDataCount();
        if (StationsThisMonthDataCount != null && StationsThisMonthDataCount.size() != 0) {
            return ResultModel.ok(StationsThisMonthDataCount);
        }
        return ResultModel.error("查询失败");
    }

    /**
     * 各个省份的站点数
     *
     * @return
     */
    @ApiOperation("各个省份的站点数，如北京的生态站的数量为5")
    @GetMapping("/getProvinceStationCount")
    public ResultModel getProvinceStationCount() {
        List<ProvinceStation> provinceStationCountrovinceStation = stationService.getProvinceStationCount();
        if (provinceStationCountrovinceStation != null && provinceStationCountrovinceStation.size() != 0) {
            return ResultModel.ok(provinceStationCountrovinceStation);
        }
        return ResultModel.error("查询失败");
    }

    /**
     * 今日与昨日，收集的数据量的变化率
     *
     * @return
     */
    @ApiOperation("今日与昨日，收集的数据量的变化率")
    @GetMapping("/differenceValue")
    public ResultModel differenceValue() {
        String differenceValue = stationService.getDifferenceValue();
        if (differenceValue != null && !"".equals(differenceValue)) {

            return ResultModel.ok(differenceValue);
        }
        return ResultModel.error("查询失败");
    }

    /**
     * 今日数据量
     *
     * @return
     */
    @ApiOperation("今日数据量")
    @GetMapping("/getEcodatatodayCount")
    public ResultModel getEcodatatodayCount() {
        String ecodatatodayCount = stationService.getEcodatatodayCount();
        return ResultModel.ok(ecodatatodayCount);
    }

    /**
     * 昨日数据量
     *
     * @return
     */
    @ApiOperation("昨日数据量")
    @GetMapping("/getEcodatayesterdayCount")
    public ResultModel getEcodatayesterdayCount() {
        String ecodatayesterdayCount = stationService.getEcodatayesterdayCount();
        return ResultModel.ok(ecodatayesterdayCount);

    }

    @ApiOperation("统计各城市今日的数据收集量")
    @GetMapping("/getEachCityTodayDataCount")
    public ResultModel getEachCityTodayDataCount() {
        List<CityDataCount> cityDataCounts = stationService.eachCityTodayDataCount();
        if (cityDataCounts != null && cityDataCounts.size() != 0) {
            return ResultModel.ok(cityDataCounts);
        }
        return ResultModel.error("查询失败");
    }

//    @ApiOperation("本月各个站点收集收集数量的平均值")
//    @GetMapping("/getStationsThisMonthDataCountAvg")
//    public ResultModel getStationsThisMonthDataCountAvg() {
//        String stationsThisMonthDataCountAvg = stationService.getStationsThisMonthDataCountAvg();
//        if (stationsThisMonthDataCountAvg != null && !"".equals(stationsThisMonthDataCountAvg)) {
//            return ResultModel.ok(stationsThisMonthDataCountAvg);
//        }
//        return ResultModel.error("查询失败");
//    }

    @ApiOperation("环境监测指标，如温度，湿度，降水量")
    @GetMapping("/getEnvironmentIndex")
    public ResultModel getEnvironmentIndex() {
        List<String> environmentIndex = stationService.getEnvironmentIndex();
        if (environmentIndex != null && environmentIndex.size() != 0) {
            return ResultModel.ok(environmentIndex);
        }
        return ResultModel.error("查询失败");
    }
}
