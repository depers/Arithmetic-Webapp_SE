package cn.bravedawn.airthmeticwebappse.serviceImpl;

import cn.bravedawn.airthmeticwebappse.bean.UserScore;
import cn.bravedawn.airthmeticwebappse.repository.UserScoreRepository;
import cn.bravedawn.airthmeticwebappse.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserScoreServiceImpl implements UserScoreService{

    @Autowired
    private UserScoreRepository userScoreRepository;


    @Override
    public boolean insertUser(String uuId, int times) {
        UserScore userScore = new UserScore();
        userScore.setUuId(uuId);
        userScore.setTimes(times);
        userScore = userScoreRepository.save(userScore);
        if (userScore.getId() != -1){
            return true;
        }
        return false;
    }

    @Override
    public int findUserTimesByUuId(String uuId) {
        int times = userScoreRepository.getTimesByUuId(uuId);
        return times;
    }
}
