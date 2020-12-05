package cn.bjfu.history;

import cn.bjfu.history.service.StationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DecimalFormat;

@SpringBootTest
public class MyTest {
    @Autowired
    private StationService stationService;

    @Test
    public void test01() {
        System.out.println(stationService.getStationTypeCount());
    }

    @Test
    public void test02() {
        double a = 9;
        double b = 4;
        double result = (b - a) / a * 100;
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(result) + "%");

    }
}
