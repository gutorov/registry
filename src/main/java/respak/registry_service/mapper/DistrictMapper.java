package respak.registry_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import respak.registry_service.dto.DistrictDto;
import respak.registry_service.model.District;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DistrictMapper {
    DistrictDto toDto(District district);

    List<DistrictDto> toDto(List<District> district);

    District toEntity(DistrictDto districtDto);
}
