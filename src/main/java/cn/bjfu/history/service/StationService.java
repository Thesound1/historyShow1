package cn.bjfu.history.service;

import cn.bjfu.history.mapper.StationMapper;
import cn.bjfu.history.model.ProvinceStation;
import cn.bjfu.history.model.StationDataCount;
import cn.bjfu.history.model.StationType;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
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

    public List<StationDataCount> getStationTodayData() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String result = (String) valueOperations.get("StationTodayData");
        List<StationDataCount> tCounts = JSON.parseArray(result, StationDataCount.class);
        return tCounts;
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
        double result = (b - a) / a * 100;
        DecimalFormat df = new DecimalFormat("#.00");
        String ret = df.format(result) + "%";
        return ret;
    }
}
