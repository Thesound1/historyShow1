package cn.bjfu.history.service;

import cn.bjfu.history.mapper.StationMapper;
import cn.bjfu.history.model.*;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class StationService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StationMapper stationMapper;

    public List<List> getCountsPercent(String type) {
        List listResult = new ArrayList<>();
        List timeResult = new ArrayList<>();
        List countResult = new ArrayList<>();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String result;
        if ("Day".equalsIgnoreCase(type)) {
            result = (String) valueOperations.get("EcodataDay");
        } else if ("Month".equalsIgnoreCase(type)) {
            result = (String) valueOperations.get("EcodataMonth");
        } else if ("HalfYear".equalsIgnoreCase(type)) {
            result = (String) valueOperations.get("EcodataMonth");
        } else {
            return null;
        }

        Integer totalCount = 0;
        List<TCount> tCounts = JSON.parseArray(result, TCount.class);
        if ("HalfYear".equalsIgnoreCase(type)) {
            tCounts = tCounts.subList(tCounts.size() - 6, tCounts.size());
            DecimalFormat df = new DecimalFormat("#.##%");
            if (tCounts != null && tCounts.size() != 0) {
                for (TCount tCount : tCounts) {
                    String count = tCount.getCount();
                    totalCount += Integer.valueOf(count);
                }
                redisTemplate.opsForValue().set("lastSixMonthsDataCount", totalCount);
                for (TCount tCount : tCounts) {
                    String date = tCount.getDate();
                    Integer count = Integer.valueOf(tCount.getCount());

                    timeResult.add(date);
                    countResult.add(df.format((double) count / (double) totalCount));
                }
            }
        } else {
            for (TCount tCount : tCounts) {
                String date = tCount.getDate();
                String count = tCount.getCount();
                timeResult.add(date);
                countResult.add(count);
            }
            if ("Month".equalsIgnoreCase(type)) {
                String currentMonthDataCount = (String) countResult.get(countResult.size() - 1);
                redisTemplate.opsForValue().set("currentMonthDataCount", currentMonthDataCount);
            }
        }
        listResult.add(timeResult);
        listResult.add(countResult);
        return listResult;
    }


    public Map<String, String> getStationTypeCountPercent() {
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


        return stationTypeCountPercent;
    }

    public List<StationDataCount> getStationTodayData() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String result = (String) valueOperations.get("StationTodayData");
        List<StationDataCount> tCounts = JSON.parseArray(result, StationDataCount.class);
        if (tCounts != null && tCounts.size() != 0) {
            Collections.sort(tCounts, new Comparator<StationDataCount>() {
                public int compare(StationDataCount o1, StationDataCount o2) {
                    if (o1.getCount() > o2.getCount()) {
                        return -1;
                    }
                    if (o1.getCount() == o2.getCount()) {
                        return 0;
                    }
                    return 1;
                }
            });
            return tCounts;
        }
        return null;
    }

    public List<StationDataCount> getStationsThisMonthDataCount() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String result = (String) valueOperations.get("StationsThisMonthDataCount");
        List<StationDataCount> tCounts = JSON.parseArray(result, StationDataCount.class);
        return tCounts;
    }

    public List<ProvinceStation> getProvinceStationCount() {
        List<ProvinceStation> provinceStationCount = stationMapper.getProvinceStationCount();
        return provinceStationCount;
    }

    public String getDifferenceValue() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String todayCountStr = String.valueOf(valueOperations.get("EcodataToday"));
        String yesterdayCountStr = String.valueOf(valueOperations.get("EcodataYesterday"));
        Integer yesterdayCount = Integer.valueOf(yesterdayCountStr);
        Integer todayCount = Integer.valueOf(todayCountStr);

        double a = yesterdayCount;
        double b = todayCount;
        double result = (b - a) / a;
        DecimalFormat df = new DecimalFormat("#.##%");
        String ret = df.format(result);
        return ret;
    }

    public String getEcodatayesterdayCount() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String yesterdayCountStr = String.valueOf(valueOperations.get("EcodataYesterday"));
        Double yesterdayCount = Double.valueOf(yesterdayCountStr);
        return String.format("%.2f", yesterdayCount / 10000);
    }

    public String getEcodatatodayCount() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String todayCountStr = String.valueOf(valueOperations.get("EcodataToday"));
        Double todayCount = Double.valueOf(todayCountStr);
        return String.format("%.2f", todayCount / 10000);
    }

    public List<CityDataCount> eachCityTodayDataCount() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String eachCityTodayDataCount = (String) valueOperations.get("eachCityTodayDataCount");
        List<CityDataCount> cityDataCount = JSON.parseArray(eachCityTodayDataCount, CityDataCount.class);
        return cityDataCount;
    }

//    public String getStationsThisMonthDataCountAvg() {
//        String stationsThisMonthDataCountAvg = (String) redisTemplate.opsForValue().get("StationsThisMonthDataCountAvg");
//        return stationsThisMonthDataCountAvg;
//    }

    /**
     * 用来显示环境监测指标
     *
     * @return
     */
    public List<String> getEnvironmentIndex() {
        List<String> environmentIndex = new ArrayList<>();
        environmentIndex.add("温度");
        environmentIndex.add("湿度");
        environmentIndex.add("风速");
        environmentIndex.add("降水量");
        environmentIndex.add("PM2.5");
        Collections.shuffle(environmentIndex);
        return environmentIndex;
    }

    public String getLastSixMonthsDataCount() {
        String lastSixMonthsDataCount = String.valueOf(redisTemplate.opsForValue().get("lastSixMonthsDataCount"));
        return lastSixMonthsDataCount;
    }

    public Integer getCurrentMonthAbnormalDataCount() {
        Integer currentMonthAbnormalDataCount = stationMapper.getCurrentMonthAbnormalDataCount();
        if (currentMonthAbnormalDataCount == null) {
            return 0;
        }
        return currentMonthAbnormalDataCount;
    }

    public String getCurrentMonthDataCount() {
        String currentMonthDataCount = String.valueOf(redisTemplate.opsForValue().get("currentMonthDataCount"));
        return currentMonthDataCount;
    }

    public String getExecutionDays() {
        return String.valueOf(redisTemplate.opsForValue().get("executionDays"));

    }
}
