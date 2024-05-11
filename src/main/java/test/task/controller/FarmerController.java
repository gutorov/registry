package test.task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.task.dto.FarmerDto;
import test.task.service.FarmerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/farmers")
@RequiredArgsConstructor
public class FarmerController {

    private final FarmerService farmerService;

    @PostMapping("/filter")
    public List<FarmerDto> getFarmerByFilters(@RequestBody FarmerDto filter) {
        return farmerService.getFarmersByFilters(filter);
    }

    @GetMapping("/{farmerId}")
    public FarmerDto getFarmerById(@PathVariable("farmerId") Long farmerId) {
        return farmerService.getFarmerById(farmerId);
    }

    @PatchMapping("/{farmerId}")
    public FarmerDto updateFarmer(@PathVariable("farmerId") Long id, @RequestBody FarmerDto farmer) {
        return farmerService.updateFarmer(id, farmer);
    }

    @PostMapping
    public FarmerDto registerFarmer(@RequestBody @Valid FarmerDto farmer) {
        return farmerService.registerFarmer(farmer);
    }

    @DeleteMapping("/{farmerId}")
    public void archiveFarmer(@PathVariable Long farmerId) {
        farmerService.archiveFarmer(farmerId);
    }
}
