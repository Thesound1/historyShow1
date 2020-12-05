package cn.bjfu.history.time;

import cn.bjfu.history.mapper.EcodataMapper;
import cn.bjfu.history.service.EcodataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jxy on 2020/12/1 0001 22:51
 */
@RestController
public class EcodataInit {
    @Autowired
    private EcodataMapper ecodataMapper;
    @Autowired
    private EcodataCountTiming ecodataCountTiming;
    @Autowired
    private EcodataService ecodataService;

    @GetMapping("/Init")
    public void test() {
        ecodataCountTiming.saveEcodataByDay();
        ecodataCountTiming.saveEcodataByMonth();
        ecodataCountTiming.saveEcodataByYear();
        ecodataCountTiming.getStationThisMonthData();
    }

}
