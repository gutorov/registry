package respak.registry_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import respak.registry_service.dto.FarmerDto;
import respak.registry_service.service.FarmerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/farmers")
@RequiredArgsConstructor
public class FarmerController {

    private final FarmerService farmerService;

    @PostMapping("/filter")
    @Operation(summary = "Filter farmers", description = "Retrieve a list of farmers based on specified filters.")
    public List<FarmerDto> getFarmerByFilters(
            @Parameter(description = "Farmer filter criteria", required = true)
            @RequestBody FarmerDto filter) {
        return farmerService.getFarmersByFilters(filter);
    }

    @GetMapping("/{farmerId}")
    @Operation(summary = "Retrieve a farmer", description = "Fetch a single farmer by their unique ID.")
    public FarmerDto getFarmerById(
            @Parameter(description = "The unique identifier of the farmer to retrieve", required = true)
            @PathVariable("farmerId") Long farmerId) {
        return farmerService.getFarmerById(farmerId);
    }

    @PutMapping("/{farmerId}")
    @Operation(summary = "Replace a farmer", description = "Completely replace a farmer's details with the new data provided for the given ID. This operation requires specifying all aspects of the farmer as it replaces the entire resource.")
    public FarmerDto replaceFarmer(
            @Parameter(description = "The ID of the farmer to replace", required = true)
            @PathVariable("farmerId") Long id,
            @Parameter(description = "The new farmer details to replace the existing one", required = true)
            @RequestBody @Valid FarmerDto farmerDto) {
        return farmerService.updateFarmer(id, farmerDto);
    }

    @PostMapping
    @Operation(summary = "Register a new farmer", description = "Add a new farmer to the system.")
    public FarmerDto registerFarmer(
            @Parameter(description = "The farmer details to register", required = true)
            @RequestBody @Valid FarmerDto farmer) {
        return farmerService.registerFarmer(farmer);
    }

    @DeleteMapping("/{farmerId}")
    @Operation(summary = "Archive a farmer", description = "Soft delete (archive) a farmer by their ID, effectively marking them as inactive in the system.")
    public void archiveFarmer(
            @Parameter(description = "The ID of the farmer to archive", required = true)
            @PathVariable Long farmerId) {
        farmerService.archiveFarmer(farmerId);
    }
}
