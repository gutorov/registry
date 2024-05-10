package test.task.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import test.task.dto.FarmerDto;
import test.task.model.farmer.Farmer;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FarmerMapper {

    @Mapping(source = "districtRegisteredAt.id", target = "districtRegisteredAtId")
    FarmerDto toDto(Farmer farmer);
    @Mapping(source = "districtRegisteredAt.id", target = "districtRegisteredAtId")
    List<FarmerDto> toDto(List<Farmer> farmer);


//    @Mapping(target = "districtRegisteredAt", ignore = true)
    Farmer toEntity(FarmerDto farmerDto);
}
