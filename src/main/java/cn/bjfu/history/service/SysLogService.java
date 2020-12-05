package cn.bjfu.history.service;

import cn.bjfu.history.mapper.SysLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jxy on 2020/12/2 0002 12:05
 */
@Service
public class SysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    public Integer getSysLogTotalCount() {
        return sysLogMapper.getTotalCount();
    }

    public Integer getTotalPersonCount() {
        return sysLogMapper.getTotalPersonCount() + 850;
    }

    public Integer getPersonCountToday() {
        return sysLogMapper.getPersonCountToday();
    }
}
