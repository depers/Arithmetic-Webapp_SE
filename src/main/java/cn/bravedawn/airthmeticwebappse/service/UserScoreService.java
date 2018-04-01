package cn.bravedawn.airthmeticwebappse.service;

public interface UserScoreService {

    boolean insertUser(String uuId, int times, int score);

    int findUserTimesByUuId(String uuId);

    int findScoreByUUidAndTimes(String uuId, int times);
}
