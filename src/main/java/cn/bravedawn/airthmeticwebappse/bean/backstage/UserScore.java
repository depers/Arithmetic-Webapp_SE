package cn.bravedawn.airthmeticwebappse.bean.backstage;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class UserScore {

    @Id
    @GeneratedValue
    private int id;

    private String uuId;

    private int times;

    private int score;
}
