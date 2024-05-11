package respak.registry_service.repository;

import org.springframework.data.domain.Example;
import respak.registry_service.model.farmer.Farmer;

import java.util.List;

public interface CustomFarmerRepository {
    List<Farmer> findByExampleWithDateRange(Example<Farmer> example);
}
