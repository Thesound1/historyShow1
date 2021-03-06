package cn.bjfu.history.time;

import cn.bjfu.history.mapper.EcodataMapper;
import cn.bjfu.history.mapper.StationMapper;
import cn.bjfu.history.model.StationTodayData;
import cn.bjfu.history.model.TCount;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jxy on 2020/12/1 0001 23:02
 */
@Component
public class EcodataCountTiming {
    @Autowired
    private EcodataMapper ecodataMapper;
    @Autowired
    private StationMapper stationMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0 0 0 * * ?")
    public void saveEcodataByDay() {
        List<TCount> countByDay = ecodataMapper.getCountByDay();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String str = JSON.toJSON(countByDay).toString();
        valueOperations.set("EcodataDay", str);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void saveEcodataByMonth() {
        List<TCount> countByMonth = ecodataMapper.getCountByMonth();
        System.out.println(countByMonth);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String str = JSON.toJSON(countByMonth).toString();
        valueOperations.set("EcodataMonth", str);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void saveEcodataByYear() {
        List<TCount> countByYear = ecodataMapper.getCountByYear();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String str = JSON.toJSON(countByYear).toString();
        valueOperations.set("EcodataYear", str);
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void saveEcodataCounts() {
        Integer totalCount = ecodataMapper.getTotalCount();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("EcodataCounts", totalCount);
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void saveEcodataToday() {
        Integer countToday = ecodataMapper.getCountToday();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("EcodataToday", countToday);
    }

    /**
     * 获取昨天数据量
     */
    @Scheduled(cron = "0 0/10 0/1 * * ? ")
    public void saveEcodataYesterday() {
        Integer countYesterday = ecodataMapper.getCountYesterday();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("EcodataYesterday", countYesterday);
    }

    /**
     * 获取各个站点当日的数据量
     */
    @Scheduled(cron = "0 0/20 0/1 * * ? ")
    public void getStationTodayData() {
        List<StationTodayData> stationTodayData = stationMapper.getStationTodayData();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String str = JSON.toJSON(stationTodayData).toString();
        valueOperations.set("StationTodayData", str);
    }


    public void saveEcodataException() {
        List<TCount> exceptionFlag = ecodataMapper.getExceptionFlag();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String str = JSON.toJSON(exceptionFlag).toString();
        valueOperations.set("EcodataException", str);
    }
}
