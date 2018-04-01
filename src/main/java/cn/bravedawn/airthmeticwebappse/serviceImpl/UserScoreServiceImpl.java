package cn.bravedawn.airthmeticwebappse.serviceImpl;

import cn.bravedawn.airthmeticwebappse.bean.backstage.UserScore;
import cn.bravedawn.airthmeticwebappse.repository.UserScoreRepository;
import cn.bravedawn.airthmeticwebappse.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserScoreServiceImpl implements UserScoreService{

    @Autowired
    private UserScoreRepository userScoreRepository;


    @Override
    public boolean insertUser(String uuId, int times, int score) {
        UserScore userScore = new UserScore();
        userScore.setUuId(uuId);
        userScore.setTimes(times);
        userScore.setScore(score);
        userScore = userScoreRepository.save(userScore);
        if (userScore.getId() != -1){
            return true;
        }
        return false;
    }

    @Override
    public int findUserTimesByUuId(String uuId) {
        int times = userScoreRepository.getTimesByUuId(uuId);
        return times + 1;
    }

    @Override
    public int findScoreByUUidAndTimes(String uuId, int times) {
        return userScoreRepository.findByUuIdAndTimes(uuId, times).getScore();
    }


}
