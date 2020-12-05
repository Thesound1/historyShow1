package cn.bjfu.history.model;

import lombok.Data;

/**
 * 用来包装：一个省有多少站点
 */
@Data
public class ProvinceStation {
    private String province;
    private Integer count;
}
