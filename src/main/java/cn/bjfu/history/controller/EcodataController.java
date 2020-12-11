package cn.bjfu.history.controller;

import cn.bjfu.history.model.ResultModel;
import cn.bjfu.history.service.EcodataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by jxy on 2020/12/2 0002 8:50
 */
@RestController
public class EcodataController {
    @Autowired
    private EcodataService ecodataService;

//    @GetMapping("/getCounts/{type}")
//    public ResultModel getCounts(@PathVariable String type) {
//        List<List> countByDay = ecodataService.getCounts(type);
//        if (countByDay.size() == 0) {
//            return ResultModel.error("500", "服务器报错");
//        } else {
//            return ResultModel.ok(countByDay);
//        }
//    }

    @GetMapping("/totalCount")
    public ResultModel totalCount() {
        String totalCount = ecodataService.getTotalCount();
        if (totalCount == "" || totalCount == null) {
            return ResultModel.error("500", "服务器报错");
        } else {
            return ResultModel.ok(totalCount);
        }
    }

    @GetMapping("/ecodataToday")
    public ResultModel ecodataToday() {
        String ecodataToday = ecodataService.saveEcodataTodays();
        if (ecodataToday == "" || ecodataToday == null) {
            return ResultModel.error("500", "服务器报错");
        } else {
            return ResultModel.ok(ecodataToday);
        }
    }

    @GetMapping("/countStations")
    public ResultModel countStations() {
        String ecodataToday = ecodataService.getTotalStations();
        if (ecodataToday == "" || ecodataToday == null) {
            return ResultModel.error("500", "服务器报错");
        } else {
            return ResultModel.ok(ecodataToday);
        }
    }
}
