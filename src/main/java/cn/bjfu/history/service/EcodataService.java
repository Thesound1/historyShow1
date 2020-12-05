package cn.bjfu.history.service;

import cn.bjfu.history.mapper.EcodataMapper;
import cn.bjfu.history.model.TCount;
import com.alibaba.fastjson.JSON;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jxy on 2020/12/1 0001 22:51
 */
@Service
public class EcodataService {
    @Autowired
    private EcodataMapper ecodataMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    public List<List> getCounts(String type) {
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
            result = "";
        }
        List<TCount> tCounts = JSON.parseArray(result, TCount.class);
        if (tCounts != null && tCounts.size() != 0) {
            for (TCount tCount : tCounts) {
                String date = tCount.getDate();
                String count = tCount.getCount();
                timeResult.add(date);
                countResult.add(count);
            }
            listResult.add(timeResult);
            listResult.add(countResult);
        }
        return listResult;
    }

    public String getTotalCount() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Integer ecodataCounts = (Integer) valueOperations.get("EcodataCounts");
        return ecodataCounts + "";
    }

    public String saveEcodataTodays() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Integer EcodataToday = (Integer) valueOperations.get("EcodataToday");
        return EcodataToday + "";
    }


    public String getTotalStations() {
        Integer totalStations = ecodataMapper.getTotalStations();
        return totalStations + "";
    }

    public List<List> getEcodataException() {
        List listResult = new ArrayList<>();
        List exceptionResult = new ArrayList<>();
        List countResult = new ArrayList<>();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String result = (String) valueOperations.get("EcodataException");
        System.out.println(result);
        List<TCount> tCounts = JSON.parseArray(result, TCount.class);
        if (tCounts != null && tCounts.size() != 0) {
            for (TCount tCount : tCounts) {
                String exceptionFlg = tCount.getDate();
                if (exceptionFlg == null) {
                    exceptionFlg = "-1";
                }
                String count = tCount.getCount();
                exceptionResult.add(exceptionFlg);
                countResult.add(count);
            }
            listResult.add(listResult);
            listResult.add(countResult);
        }
        return listResult;
    }

}
