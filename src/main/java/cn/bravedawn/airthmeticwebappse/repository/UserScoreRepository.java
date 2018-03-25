package cn.bravedawn.airthmeticwebappse.repository;

import cn.bravedawn.airthmeticwebappse.bean.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserScoreRepository extends JpaRepository<UserScore, Integer>,
        JpaSpecificationExecutor<UserScore>{

    @Query(nativeQuery = true, value = "select count(1) FROM  user_score WHEN uuid =:uuid")
    int getTimesByUuId(@Param("uuid") String uuid);
}
