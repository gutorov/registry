package respak.registry_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import respak.registry_service.dto.DistrictDto;
import respak.registry_service.service.DistrictService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/districts")
@Tag(name = "District Registry")
public class DistrictController {

    private final DistrictService districtService;

    @GetMapping
    @Operation(
            summary = "Search districts based on filters",
            description = "Retrieves a list of districts that match the given filters. " +
                    "Provide the filters as query parameters. For example, ?name=Central&code=CD100&archivalStatus=false"
    )
    public List<DistrictDto> getDistrictsByFilters(
            @Parameter(description = "Filter criteria for searching districts", required = true)
            DistrictDto districtFilter) {
        return districtService.getDistrictsByFilters(districtFilter);
    }

    @PostMapping
    @Operation(
            summary = "Add district",
            description = "Adds a new district to the registry. This endpoint accepts district details and creates a new district record."
    )
    public DistrictDto registerDistrict(@RequestBody @Valid @Parameter(description = "District data to be registered", required = true)
                                        DistrictDto districtDto) {
        return districtService.registerDistrict(districtDto);
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Update district",
            description = "Updates the data of an existing district in the registry based on the provided ID and data payload. Only non-null fields will be updated.",
            parameters = {
                    @Parameter(name = "id", description = "The ID of the district to update", required = true)
            }
    )
    public DistrictDto updateDistrict(@PathVariable("id") Long id,
                                      @RequestBody @Valid @Parameter(name = "districtDto", description = "Updated district data", required = true) DistrictDto districtDto) {
        return districtService.updateDistrict(id, districtDto);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Archive district",
            description = "Archives a district by its ID, effectively hiding it from regular registry views but not deleting it from the database.",
            parameters = {
                    @Parameter(name = "id", description = "The ID of the district to archive", required = true)
            }
    )
    public void archiveDistrict(@PathVariable("id") Long id) {
        districtService.archiveDistrict(id);
    }
}
