package test.task.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import test.task.model.farmer.Farmer;

import java.util.List;

public interface CustomFarmerRepository{
    List<Farmer> findByExampleWithDateRange(Example<Farmer> example);
}
