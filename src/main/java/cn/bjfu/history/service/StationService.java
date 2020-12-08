package cn.bjfu.history.service;

import cn.bjfu.history.mapper.StationMapper;
import cn.bjfu.history.model.ProvinceStation;
import cn.bjfu.history.model.StationDataCount;
import cn.bjfu.history.model.StationType;
import cn.bjfu.history.model.TCount;
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
        } else if ("Year".equalsIgnoreCase(type)) {
            result = (String) valueOperations.get("EcodataYear");
        } else {
            return null;
        }

        Integer totalCount = 0;
        List<TCount> tCounts = JSON.parseArray(result, TCount.class);

        if ("Year".equalsIgnoreCase(type)) {
            DecimalFormat df = new DecimalFormat("#.##%");
            if (tCounts != null && tCounts.size() != 0) {
                for (TCount tCount : tCounts) {
                    String count = tCount.getCount();
                    totalCount += Integer.valueOf(count);
                }
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
            Collections.sort(tCounts, new Comparator<StationDataCount>(){
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
        String ret = df.format(result) ;
        return ret;
    }
}
