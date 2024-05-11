package test.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.task.model.District;

import java.util.List;


@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findAllByNameIn(List<String> names);

    District findByName(String name);

    boolean existsByName(String name);
}
