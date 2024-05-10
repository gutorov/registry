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
public interface FarmerRepository extends JpaRepository<Farmer, Long>, JpaSpecificationExecutor<Farmer>, CustomFarmerRepository{
    @Query("SELECT e FROM Farmer e WHERE FUNCTION('DATE', e.registeredAt) >= :startDate AND FUNCTION('DATE', e.registeredAt) <= :endDate")
    List<Farmer> findByEventDateBetween(LocalDate startDate, LocalDate endDate);
}
