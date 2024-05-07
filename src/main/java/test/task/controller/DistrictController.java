package test.task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.task.dto.district.DistrictDto;
import test.task.service.DistrictService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/districts")
@Tag(name = "District Registry")
public class DistrictController {

    private final DistrictService districtService;

    //I chose using @PostMapping for getting operation,
    // because there is filter that should get apply to the search,
    //I understand that I could send fields, by which I will filter data, separately,
    //but that approach would have complicated the further extension,
    //meanwhile when I use @RequestBody I could merely and some other fields in DistrictDto and filter by them
    @PostMapping("/searches")
    @Operation(
            summary = "Get filtered districts",
            description = "Retrieve a list of districts that match the given filters. This endpoint is useful for obtaining a filtered list of districts without retrieving archived records.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "ExampleFilter",
                                    value = "{\"name\": \"Suburban District\", \"code\": \"108\"}"
                            )
                    )
            )
    )
    public List<DistrictDto> getDistrictsByFilters(@RequestBody @Parameter(description = "Filter criteria for searching districts", required = true)
                                                   DistrictDto districtFilter) {
        return districtService.getDistrictsByFilters(districtFilter);
    }

    @PostMapping("/register")
    @Operation(
            summary = "Add district",
            description = "Adds a new district to the registry. This endpoint accepts district details and creates a new district record.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\"name\": \"Eastern East\", \"code\": \"233\"}"
                            )
                    )
            )
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
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "UpdateDistrict",
                                    value = "{\"name\": \"Central District\", \"code\": \"3333\"}"
                            )
                    )
            )
    )
    public DistrictDto updateDistrict(@PathVariable("id") Long id,
                                      @RequestBody @Valid @Parameter(name = "districtDto", description = "Updated district data", required = true) DistrictDto districtDto) {
        return districtService.updateDistrict(id, districtDto);
    }

    @PatchMapping("/archive/{id}")
    @Operation(
            summary = "Archive district",
            description = "Archives a district by its ID, effectively hiding it from regular registry views but not deleting it from the database.",
            parameters = {
                    @Parameter(name = "id", description = "The ID of the district to archive", required = true)
            }
    )
    public DistrictDto archiveDistrict(@PathVariable("id") Long id) {
        return districtService.archiveDistrict(id);
    }
}
