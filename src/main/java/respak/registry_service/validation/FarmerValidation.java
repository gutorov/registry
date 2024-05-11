package respak.registry_service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import respak.registry_service.model.farmer.Farmer;
import respak.registry_service.repository.FarmerRepository;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class FarmerValidation {
    private final FarmerRepository farmerRepository;
    public Farmer validateAndFindFarmerById(Long farmerId){
        return farmerRepository.findById(farmerId).orElseThrow(
                () -> new EntityNotFoundException("Farmer with ID " + farmerId + " not found"));

    }
}
