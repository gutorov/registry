package test.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.task.dto.FarmerDto;
import test.task.mapper.FarmerMapper;
import test.task.model.District;
import test.task.model.farmer.Farmer;
import test.task.repository.FarmerRepository;
import test.task.validation.DistrictValidation;
import test.task.validation.FarmerValidation;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmerService {

    private final FarmerRepository farmerRepository;
    private final FarmerMapper farmerMapper;
    private final DistrictValidation districtValidation;
    private final FarmerValidation farmerValidation;

    public List<FarmerDto> getFarmersByFilters(FarmerDto filter){

        Example<Farmer> farmerFilter = Example.of(farmerMapper.toEntity(filter));

        return farmerMapper.toDto(farmerRepository.findByExampleWithDateRange(farmerFilter));
    }

    public FarmerDto getFarmerById(Long farmerId){
        Farmer farmer = farmerRepository.findById(farmerId).orElseThrow(
                () -> new EntityNotFoundException("Farmer with id:" + farmerId+ " was not found"));
        return farmerMapper.toDto(farmer);
    }

    @Transactional
    public FarmerDto registerFarmer(FarmerDto farmerDto){
        Farmer farmer = farmerMapper.toEntity(farmerDto);
        District districtRegisteredAt = districtValidation.validateAndFindDistrictByName(farmerDto.getDistrictRegisteredAt());
        List<District> cropFieldDistricts = districtValidation.validateAndFindDistrictsByNames(farmerDto.getCropFieldDistricts());

        farmer.setDistrictRegisteredAt(districtRegisteredAt);
        farmer.setCropFieldDistricts(cropFieldDistricts);

        return farmerMapper.toDto(farmerRepository.save(farmer));
    }

    @Transactional
    public FarmerDto updateFarmer(Long farmerId, FarmerDto farmerDto){
        Farmer oldFarmer = farmerValidation.validateAndFindFarmerById(farmerId);


        Farmer updatedFarmer = farmerMapper.toEntity(farmerDto);

        District districtRegisteredAt = districtValidation.validateAndFindDistrictByName(farmerDto.getDistrictRegisteredAt());
        List<District> cropFieldDistricts = districtValidation.validateAndFindDistrictsByNames(farmerDto.getCropFieldDistricts());

        updatedFarmer.setId(farmerId);
        updatedFarmer.setCropFieldDistricts(cropFieldDistricts);
        updatedFarmer.setDistrictRegisteredAt(districtRegisteredAt);

        return farmerMapper.toDto(updatedFarmer);
    }

    @Transactional
    public void archiveFarmer(Long farmerId){
        Farmer farmer = farmerValidation.validateAndFindFarmerById(farmerId);
        farmer.setArchived(true);
    }
}
