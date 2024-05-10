package test.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import test.task.dto.FarmerDto;
import test.task.mapper.FarmerMapper;
import test.task.model.farmer.Farmer;
import test.task.repository.CustomFarmerRepositoryImpl;
import test.task.repository.FarmerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmerService {

    private final FarmerRepository farmerRepository;
//    private final CustomFarmerRepositoryImpl farmerRepositoryIml;
    private final FarmerMapper farmerMapper;

    public List<FarmerDto> getFarmersByFilters(FarmerDto filter){

        Example<Farmer> farmerFilter = Example.of(farmerMapper.toEntity(filter));

//        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();

        return farmerMapper.toDto(farmerRepository.findByExampleWithDateRange(farmerFilter));
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

//Organization Name (required, filterable)
//Legal Form (Corporation, Sole Proprietorship, Individual)
//Taxpayer Identification Number (INN) (required, filterable)
//Tax Registration Reason Code (KPP)
//Primary State Registration Number (OGRN)
//Registration District (linked to District ID, filterable)
//Districts of Crop Fields (multiple selection, linked to District)
//Registration Date (filterable)
//Archival Status (Yes/No, filterable)
}
