package cn.bravedawn.airthmeticwebappse.service;

public interface UserScoreService {

    boolean insertUser(String uuId, int times);

    int findUserTimesByUuId(String uuId);
}
