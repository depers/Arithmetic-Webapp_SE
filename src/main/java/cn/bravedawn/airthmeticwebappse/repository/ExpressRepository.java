package cn.bravedawn.airthmeticwebappse.repository;

import cn.bravedawn.airthmeticwebappse.bean.Express;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExpressRepository extends JpaRepository<Express, Integer>,
        JpaSpecificationExecutor<Express> {
}
