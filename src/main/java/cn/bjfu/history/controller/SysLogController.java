package cn.bjfu.history.controller;

import cn.bjfu.history.model.ResultModel;
import cn.bjfu.history.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jxy on 2020/12/2 0002 12:06
 */
@RestController
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @GetMapping("/getSysLogTotalCount")
    public ResultModel getSysLogTotalCount() {
        Integer sysLogTotalCount = sysLogService.getSysLogTotalCount();
        if (sysLogTotalCount == 0) {
            return ResultModel.error("500", "服务器错误");
        } else {
            return ResultModel.ok(sysLogTotalCount);
        }
    }

    @GetMapping("/getTotalPersonCount")
    public ResultModel getTotalPersonCount() {
        Integer sysLogTotalCount = sysLogService.getTotalPersonCount();
        if (sysLogTotalCount == 0) {
            return ResultModel.error("500", "服务器错误");
        } else {
            return ResultModel.ok(sysLogTotalCount);
        }
    }

    @GetMapping("/getPersonCountToday")
    public ResultModel getPersonCountToday() {
        Integer sysLogTotalCount = sysLogService.getPersonCountToday();
        if (sysLogTotalCount == null) {
            return ResultModel.error("查询失败");
        }
            return ResultModel.ok(sysLogTotalCount);
    }

}
