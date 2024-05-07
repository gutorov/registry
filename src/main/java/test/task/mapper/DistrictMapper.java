package test.task.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import test.task.dto.DistrictDto;
import test.task.model.District;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DistrictMapper {
    DistrictDto toDto(District district);

    List<DistrictDto> toDto(List<District> district);

    District toEntity(DistrictDto districtDto);

    List<District> toEntity(List<DistrictDto> districtDto);
}
