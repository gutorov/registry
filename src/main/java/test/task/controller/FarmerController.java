package test.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.task.dto.FarmerDto;
import test.task.service.FarmerService;

import java.util.List;

@RestController("/api/v1/farmers")
@RequiredArgsConstructor
public class FarmerController {

    private final FarmerService farmerService;

    @PostMapping("/filter")
    public List<FarmerDto> getFarmerByFilters(@RequestBody FarmerDto filter){
        return farmerService.getFarmersByFilters(filter);
    }


//название организации (обязательное, фильтр)
//организационно-правовая форма (ЮР, ИП, ФЛ)
//ИНН (обязательное, фильтр)
//КПП
//ОГРН
//район регистрации (связь с районом/ID - района) (фильтр)
//районы посевных полей (множественный выбор, связь с районом)
//дата регистрации (фильтр)
//статус архивности (да/нет) (фильтр)

    //Необходимо реализовать следующие запросы:
    //Получение списка фермеров, внесенных в реестр. Реализовать фильтрацию возвращаемого списка по указанным атрибутам.
    //Получение данных по фермеру. По районам необходимо предоставлять наименования.
    //Добавление фермера
    //Изменение записи фермера
    //Отправить в архив (архивные не выводим в реестр)

}
