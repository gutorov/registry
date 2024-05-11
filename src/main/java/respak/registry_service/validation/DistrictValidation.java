package respak.registry_service.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import respak.registry_service.model.District;
import respak.registry_service.repository.DistrictRepository;
import respak.registry_service.exception.DistrictAlreadyExistsException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DistrictValidation {

    private final DistrictRepository districtRepository;

    public District validateAndFindDistrictById(Long districtId) {
        return districtRepository.findById(districtId).orElseThrow(
                () -> new EntityNotFoundException("District with ID " + districtId + " not found")
        );
    }

    public District validateAndFindDistrictByName(String name) {
        District district = districtRepository.findByName(name);
        if (district == null) {
            log.warn("District with Name " + name + " not found");
            throw new EntityNotFoundException("District with Name " + name + " not found");
        }
        return district;
    }

    public List<District> validateAndFindDistrictsByNames(List<String> districtNames) {
        List<District> districts = districtRepository.findAllByNameIn(districtNames);
        if (districts == null || districts.size() < districtNames.size()) {
            log.warn("Some districts were not found in the data base");
            throw new EntityNotFoundException("Some or none districts were found in the data base");
        }
        return districts;
    }

    public void validateDuplicateNameInBD(String districtName) {
        if (districtRepository.existsByName(districtName)) {
            throw new DistrictAlreadyExistsException(districtName);
        }
    }
}
