package cn.bjfu.history.service;

import cn.bjfu.history.mapper.StationMapper;
import cn.bjfu.history.model.ProvinceStation;
import cn.bjfu.history.model.StationTodayData;
import cn.bjfu.history.model.StationType;
import cn.bjfu.history.model.TCount;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class StationService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StationMapper stationMapper;

    public List<StationType> getStationTypeCount() {
        List<StationType> stationTypeCount = new ArrayList<>();
        stationTypeCount.add(new StationType("森林站", 39));
        stationTypeCount.add(new StationType("湿地站", 1));
        stationTypeCount.add(new StationType("荒漠站", 3));
        stationTypeCount.add(new StationType("草原站", 2));
        stationTypeCount.add(new StationType("城市站", 2));
        return stationTypeCount;
    }

    public List<StationTodayData> getStationTodayData() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String result = (String) valueOperations.get("StationTodayData");
        List<StationTodayData> tCounts = JSON.parseArray(result, StationTodayData.class);
        if (tCounts != null && tCounts.size() != 0) {
            Collections.sort(tCounts, new Comparator<StationTodayData>() {
                public int compare(StationTodayData o1, StationTodayData o2) {
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

    public List<StationTodayData> getStationThisMonthData() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String result = (String) valueOperations.get("StationThisMonthData");
        List<StationTodayData> tCounts = JSON.parseArray(result, StationTodayData.class);
//        if (tCounts != null && tCounts.size() != 0) {
//            Collections.sort(tCounts, new Comparator<StationTodayData>() {
//                public int compare(StationTodayData o1, StationTodayData o2) {
//                    if (o1.getCount() > o2.getCount()) {
//                        return -1;
//                    }
//                    if (o1.getCount() == o2.getCount()) {
//                        return 0;
//                    }
//                    return 1;
//                }
//            });
//            return tCounts;
//        }
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
        double result = (b - a) / a * 100;
        DecimalFormat df = new DecimalFormat("#.00");
        String ret = df.format(result) + "%";
        return ret;
    }
}
