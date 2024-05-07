package test.task.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.task.dto.DistrictDto;
import test.task.service.DistrictService;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/districts")
public class DistrictController {
    private final DistrictService districtService;
    //+Получение списка районов, внесенных в реестр. Реализовать фильтрацию возвращаемого списка по названию и коду района.
    //-Добавление района
    //-Изменение записи района
    //-Отправить в архив (архивные не выводим в реестр)

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

    @PutMapping("/{id}")
    @Operation(description = "Altering the data of the district")
    public DistrictDto updateDistrict(@PathParam("id") Long id, @RequestBody @Valid DistrictDto districtDto){
        return districtService.updateDistrict(id, districtDto);
    }
}