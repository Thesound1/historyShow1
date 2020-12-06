package cn.bjfu.history.model;

import lombok.Data;

/**
 * Created by jxy on 2020/12/1 0001 22:35
 */
@Data
public class TCount {
    private String date;
    private String count;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
