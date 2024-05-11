package respak.registry_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import respak.registry_service.model.farmer.Farmer;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long>, QueryByExampleExecutor<Farmer>, CustomFarmerRepository, JpaSpecificationExecutor<Farmer>{

}
