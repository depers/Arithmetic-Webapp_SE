package cn.bravedawn.airthmeticwebappse.repository;

import cn.bravedawn.airthmeticwebappse.bean.backstage.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserScoreRepository extends JpaRepository<UserScore, Integer>,
        JpaSpecificationExecutor<UserScore>{

    @Query(nativeQuery = true, value = "select count(1) FROM  user_score WHERE uu_id =:uuid")
    int getTimesByUuId(@Param("uuid") String uuid);

    UserScore findByUuIdAndTimes(String uuId, int times);
}
