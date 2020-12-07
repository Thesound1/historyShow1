package cn.bjfu.history.controller;

import cn.bjfu.history.model.ProvinceStation;
import cn.bjfu.history.model.ResultModel;
import cn.bjfu.history.model.StationTodayData;
import cn.bjfu.history.model.StationType;
import cn.bjfu.history.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StationController {
    @Autowired
    private StationService stationService;

    /**
     * 获取各站点类型的数量的百分比
     *
     * @return
     */
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
    @GetMapping("/getStationTodayData")
    public ResultModel getStationTodayData() {
        List<StationTodayData> stationTodayData = stationService.getStationTodayData();
        if (stationTodayData != null && stationTodayData.size() != 0) {
            return ResultModel.ok(stationTodayData);
        }
        return ResultModel.error("查询失败");
    }

    /**
     * 各个省份的站点数
     *
     * @return
     */
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
    @GetMapping("/differenceValue")
    public ResultModel differenceValue() {
        String differenceValue = stationService.getDifferenceValue();
        if (differenceValue != null && !"".equals(differenceValue)) {

            return ResultModel.ok(differenceValue);
        }
        return ResultModel.error("查询失败");
    }
}
