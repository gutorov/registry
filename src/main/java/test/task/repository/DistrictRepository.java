package test.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import test.task.model.District;


@Repository
public interface DistrictRepository extends JpaRepository<District, Long>{
}
