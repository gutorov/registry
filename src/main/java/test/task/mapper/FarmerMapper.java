package test.task.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import org.mapstruct.ReportingPolicy;
import test.task.dto.FarmerDto;
import test.task.model.District;
import test.task.model.farmer.Farmer;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FarmerMapper {

    @Mapping(source = "districtRegisteredAt.name", target = "districtRegisteredAt")
    @Mapping(source = "cropFieldDistricts", target = "cropFieldDistricts", qualifiedByName = "toDistrictNames")
    FarmerDto toDto(Farmer farmer);
    @Mapping(source = "districtRegisteredAt.name", target = "districtRegisteredAt")
    @Mapping(source = "cropFieldDistricts", target = "cropFieldDistricts", qualifiedByName = "toDistrictNames")
    List<FarmerDto> toDto(List<Farmer> farmer);


    @Mapping(target = "districtRegisteredAt", ignore = true)
    @Mapping(target = "cropFieldDistricts", ignore = true)
    Farmer toEntity(FarmerDto farmerDto);

    @Mapping(target = "districtRegisteredAt", ignore = true)
    @Mapping(target = "cropFieldDistricts", ignore = true)
    void updateFarmer(FarmerDto farmerDto, @MappingTarget Farmer farmer);

    @Named("toDistrictNames")
    default List<String> toDistrictNames(List<District> districts){
        return districts.stream()
                .map(District::getName)
                .collect(Collectors.toList());
    }
}
