package test.task.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    //Not sure how should I get necessary districts not using @PostMapping
    @PostMapping("/search")
    @Operation(description = "Getting a list of districts included in the register by name and code")
    public List<DistrictDto> getDistrictsByFilters(@RequestBody DistrictDto districtFilter) {
        return districtService.getDistrictsByFilters(districtFilter);
    }

    @PostMapping("/register")
    @Operation(description = "Adding a new district to the register")
    public DistrictDto registerDistrict(@RequestBody @Valid DistrictDto districtDto) {
        return districtService.registerDistrict(districtDto);
    }

    @PatchMapping("/{id}")
    @Operation(description = "Altering the data of the district")
    public DistrictDto updateDistrict(@PathVariable("id") Long id, @RequestBody @Valid DistrictDto districtDto) {
        return districtService.updateDistrict(id, districtDto);
    }

    @PatchMapping("/archive/{id}")
    @Operation(description = "Archiving the district")
    public DistrictDto archiveDistrict(@PathVariable("id") Long id) {
        return districtService.archiveDistrict(id);
    }
}
