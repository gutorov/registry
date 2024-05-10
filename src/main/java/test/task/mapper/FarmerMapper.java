package test.task.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import test.task.dto.FarmerDto;
import test.task.model.District;
import test.task.model.farmer.Farmer;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FarmerMapper {

    @Mapping(source = "districtRegisteredAt.id", target = "districtRegisteredAtId")
    @Mapping(source = "cropFieldDistricts", target = "cropFieldDistrictsIds", qualifiedByName = "map")
    FarmerDto toDto(Farmer farmer);
    @Mapping(source = "districtRegisteredAt.id", target = "districtRegisteredAtId")
    @Mapping(source = "cropFieldDistricts", target = "cropFieldDistrictsIds", qualifiedByName = "map")
    List<FarmerDto> toDto(List<Farmer> farmer);


    @Mapping(target = "districtRegisteredAt", ignore = true)
    @Mapping(target = "cropFieldDistricts", ignore = true)
    Farmer toEntity(FarmerDto farmerDto);

    @Named("map")
    default List<Long> toIds(List<District> districts){
        return districts.stream()
                .map(district -> district.getId())
                .collect(Collectors.toList());
    }
}
