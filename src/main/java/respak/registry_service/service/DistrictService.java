package respak.registry_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import respak.registry_service.mapper.DistrictMapper;
import respak.registry_service.repository.DistrictRepository;
import respak.registry_service.dto.DistrictDto;
import respak.registry_service.model.District;
import respak.registry_service.validation.DistrictValidation;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DistrictService {
    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;
    private final DistrictValidation districtValidation;

    public List<DistrictDto> getDistrictsByFilters(DistrictDto districtFilter) {
        District district = districtMapper.toEntity(districtFilter);
        district.setArchived(false);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues();
        Example<District> districtExample = Example.of(district, matcher);

        List<District> filteredDistricts = districtRepository.findAll(districtExample);
        return districtMapper.toDto(filteredDistricts);
    }

    @Transactional
    public DistrictDto registerDistrict(DistrictDto districtDto){
        districtValidation.validateDuplicateNameInBD(districtDto.getName());
        District savedDistrict = districtRepository.save(districtMapper.toEntity(districtDto));
        return districtMapper.toDto(savedDistrict);
    }

    @Transactional
    public DistrictDto updateDistrict(Long id, DistrictDto districtDto){
        District district = districtValidation.validateAndFindDistrictById(id);
        district.setCode(districtDto.getCode());
        district.setName(districtDto.getName());
        district.setUpdated_at(LocalDateTime.now());

        return districtMapper.toDto(district);
    }

    @Transactional
    public void archiveDistrict(Long id){
        District district = districtValidation.validateAndFindDistrictById(id);
        district.setArchived(true);
    }

}
