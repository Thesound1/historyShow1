package cn.bjfu.history.time;

import cn.bjfu.history.mapper.EcodataMapper;
import cn.bjfu.history.service.EcodataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jxy on 2020/12/1 0001 22:51
 */
@Controller
public class RedisSetByHand {
    @Autowired
    private EcodataMapper ecodataMapper;
    @Autowired
    private EcodataCountTiming ecodataCountTiming;
    @Autowired
    private EcodataService ecodataService;

    @GetMapping("/setEcodataByDay")
    public void setEcodataByDay() {
        ecodataCountTiming.saveEcodataByDay();
    }
    @GetMapping("/setEcodataByMonth")
    public void setEcodataByMonth() {
        ecodataCountTiming.saveEcodataByMonth();
    }
    @GetMapping("/setEcodataByYear")
    public void setEcodataByYear() {
        ecodataCountTiming.saveEcodataByYear();
    }

    @GetMapping("/setStationsThisMonthDataCount")
    public String InitStationsThisMonthData() {
        ecodataCountTiming.setStationsThisMonthDataCount();
        return "redirect:/getStationsThisMonthDataCount";
    }

}
