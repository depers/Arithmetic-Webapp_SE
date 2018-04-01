package cn.bravedawn.airthmeticwebappse.bean.front;

import lombok.Data;

import java.util.List;

@Data
public class Series {

    private String name;
    private String type;
    private List<Integer> data;

}
