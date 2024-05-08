package test.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.task.dto.district.DistrictDto;
import test.task.errors.MessageError;
import test.task.mapper.DistrictMapper;
import test.task.model.District;
import test.task.repository.DistrictRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictService {
    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;

    @Transactional
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
        District savedDistrict = districtRepository.save(districtMapper.toEntity(districtDto));
        return districtMapper.toDto(savedDistrict);
    }

    @Transactional
    public DistrictDto updateDistrict(Long id, DistrictDto districtDto){
        District district = getDistrict(id);
        district.setCode(districtDto.getCode());
        district.setName(districtDto.getName());

        return districtMapper.toDto(district);
    }

    @Transactional
    public void archiveDistrict(Long id){
        District district = getDistrict(id);
        district.setArchived(true);
        districtMapper.toDto(district);
    }

    private District getDistrict(Long id){
        return districtRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageError.DISTRICT_NOT_FOUNT_EXCEPTION.getMessage()));
    }
}
