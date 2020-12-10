package cn.bjfu.history;

import cn.bjfu.history.model.Ecodata;
import cn.bjfu.history.model.StationType;
import cn.bjfu.history.service.EcodataService;
import cn.bjfu.history.service.StationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyTest {
    @Autowired
    private StationService stationService;
    @Autowired
    private EcodataService ecodataService;


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

        DecimalFormat df = new DecimalFormat("#.##%");
        stationTypeCountPercent.put(stationType1.getStationType(), df.format((double) stationType1.getStationCount() / (double) totalStation));
        stationTypeCountPercent.put(stationType2.getStationType(), df.format((double) stationType2.getStationCount() / (double) totalStation));
        stationTypeCountPercent.put(stationType3.getStationType(), df.format((double) stationType3.getStationCount() / (double) totalStation));
        stationTypeCountPercent.put(stationType4.getStationType(), df.format((double) stationType4.getStationCount() / (double) totalStation));
        stationTypeCountPercent.put(stationType5.getStationType(), df.format((double) stationType5.getStationCount() / (double) totalStation));
        System.out.println(stationTypeCountPercent);

    }

    @Test
    public void test05() {
        List<List> year = stationService.getCountsPercent("day");
        System.out.println(year);
        List<List> year1 = ecodataService.getCounts("day");
        System.out.println(year1);
    }

    @Test
    public void test06() {
        System.out.println(stationService.getEcodatatodayCount());
        System.out.println(stationService.getEcodatayesterdayCount());
    }

    @Test
    public void test07() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        integers.add(8);
        integers.add(9);
        integers.add(10);
        integers.add(11);
        integers.add(12);
//        System.out.println(integers.size()-6);
        System.out.println(integers.subList(integers.size() - 6, integers.size()));

    }
}
