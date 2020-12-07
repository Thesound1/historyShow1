package cn.bjfu.history;

import cn.bjfu.history.model.StationType;
import cn.bjfu.history.service.StationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class MyTest {
    @Autowired
    private StationService stationService;

    @Test
    public void test01() {
//        System.out.println(stationService.getStationTypeCount());
    }

    @Test
    public void test02() {
        double a = 9;
        double b = 4;
        double result = (b - a) / a * 100;
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(result) + "%");

    }

    @Test
    public void test03() {
        StationType stationType1 = new StationType("森林站", 39);
        StationType stationType2 = new StationType("湿地站", 1);
        StationType stationType3 = new StationType("荒漠站", 3);
        StationType stationType4 = new StationType("草原站", 2);
        StationType stationType5 = new StationType("城市站", 2);
        Integer totalStation = stationType1.getStationCount();
        totalStation += stationType2.getStationCount();
        totalStation += stationType3.getStationCount();
        totalStation += stationType4.getStationCount();
        totalStation += stationType5.getStationCount();
        System.out.println(totalStation);
    }

    @Test
    public void test04() {
        Map<String, String> stationTypeCountPercent = new HashMap<>();
        StationType stationType1 = new StationType("森林站", 39);
        StationType stationType2 = new StationType("湿地站", 1);
        StationType stationType3 = new StationType("荒漠站", 3);
        StationType stationType4 = new StationType("草原站", 2);
        StationType stationType5 = new StationType("城市站", 2);
        Integer totalStation = stationType1.getStationCount();
        totalStation += stationType2.getStationCount();
        totalStation += stationType3.getStationCount();
        totalStation += stationType4.getStationCount();
        totalStation += stationType5.getStationCount();

        DecimalFormat df = new DecimalFormat("#.00");
        stationTypeCountPercent.put(stationType1.getStationType(), df.format((double) stationType1.getStationCount() / (double) totalStation * 100) + "%");
        stationTypeCountPercent.put(stationType2.getStationType(), df.format((double) stationType2.getStationCount() / (double) totalStation * 100) + "%");
        stationTypeCountPercent.put(stationType3.getStationType(), df.format((double) stationType3.getStationCount() / (double) totalStation * 100) + "%");
        stationTypeCountPercent.put(stationType4.getStationType(), df.format((double) stationType4.getStationCount() / (double) totalStation * 100) + "%");
        stationTypeCountPercent.put(stationType5.getStationType(), df.format((double) stationType5.getStationCount() / (double) totalStation * 100) + "%");
        System.out.println(stationTypeCountPercent);

    }
}
