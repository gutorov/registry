package test.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import test.task.model.farmer.Farmer;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long>, QueryByExampleExecutor<Farmer>, CustomFarmerRepository, JpaSpecificationExecutor<Farmer>{

}
