package cn.bjfu.history.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by jxy on 2020/12/2 0002 12:03
 */
@Component
@Mapper
public interface SysLogMapper {
    @Select("select count(*) from syslog")
    public Integer getTotalCount();

    @Select("select count(DISTINCT(user_id)) from syslog")
    public Integer getTotalPersonCount();

    @Select("select  count(*)  from  syslog e  where  date(e.time_value) = curdate()")
    public Integer getPersonCountToday();
}
