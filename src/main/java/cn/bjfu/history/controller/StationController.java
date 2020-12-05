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

@RestController
public class StationController {
    @Autowired
    private StationService stationService;

    @GetMapping("/getStationTypeCount")
    public ResultModel getStationTypeCount() {
        List<StationType> stationTypeCount = stationService.getStationTypeCount();
        return ResultModel.ok(stationTypeCount);
    }

    @GetMapping("/getStationTodayData")
    public ResultModel getStationTodayData() {
        List<StationTodayData> stationTodayData = stationService.getStationTodayData();
        if (stationTodayData != null && stationTodayData.size() != 0) {
            return ResultModel.ok(stationTodayData);
        }
        return ResultModel.error("查询失败");
    }

    @GetMapping("/getStationThisMonthData")
    public ResultModel getStationThisMonthData() {
        List<StationTodayData> stationTodayData = stationService.getStationThisMonthData();
        if (stationTodayData != null && stationTodayData.size() != 0) {
            return ResultModel.ok(stationTodayData);
        }
        return ResultModel.error("查询失败");
    }

    @GetMapping("/getProvinceStationCount")
    public ResultModel getProvinceStationCount() {
        List<ProvinceStation> provinceStationCountrovinceStation = stationService.getProvinceStationCount();
        if (provinceStationCountrovinceStation != null && provinceStationCountrovinceStation.size() != 0) {
            return ResultModel.ok(provinceStationCountrovinceStation);
        }
        return ResultModel.error("查询失败");
    }

    @GetMapping("/differenceValue")
    public ResultModel differenceValue() {
        String differenceValue = stationService.getDifferenceValue();
        if (differenceValue != null && !"".equals(differenceValue)) {

            return ResultModel.ok(differenceValue);
        }
        return ResultModel.error("查询失败");
    }
}
