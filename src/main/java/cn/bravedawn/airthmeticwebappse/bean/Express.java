package cn.bravedawn.airthmeticwebappse.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Express {

    @Id
    @GeneratedValue
    private int id;

    private int timeId;

    private String express;

    private int result;
}
