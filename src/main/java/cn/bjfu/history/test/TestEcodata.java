package cn.bjfu.history.test;

import cn.bjfu.history.mapper.EcodataMapper;
import cn.bjfu.history.service.EcodataService;
import cn.bjfu.history.time.EcodataCountTiming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jxy on 2020/12/1 0001 22:51
 */
@RestController
public class TestEcodata {
    @Autowired
    private EcodataMapper ecodataMapper;
    @Autowired
    private EcodataCountTiming ecodataCountTiming;
    @Autowired
    private EcodataService ecodataService;

    @RequestMapping("/get")
    public void test() {
        ecodataCountTiming.saveEcodataByDay();
    }

    @RequestMapping("/get1")
    public void test1() {
        ecodataCountTiming.saveEcodataByMonth();
        ecodataCountTiming.saveEcodataByYear();
    }
}
