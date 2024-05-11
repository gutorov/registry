package test.task.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import test.task.model.farmer.Farmer;
import test.task.repository.FarmerRepository;

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
