package test.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;
import test.task.model.District;
import test.task.model.farmer.LegalForm;
import test.task.validation.annotations.DigitCount;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class FarmerDto {

    @NotNull
    @Size(max = 128, message = "The organisation name length must be less than 128 characters long")
    private String organisationName;//filter

    @NotNull
    private LegalForm legalForm;

    @NotNull
    @DigitCount(min = 10, max = 12, message = "INN must be between 10 and 12 digits")
    private Long INN;//filter

    @DigitCount(min = 9, max = 9, message = "KPP must be 9 digits")
    private Long KPP;

    @DigitCount(min = 13, max = 13, message = "OGRN must be 9 digits")
    private Long OGRN;

    @NotNull
    private Long districtRegisteredAtId;//form district



    @NotNull
    private List<Long> cropFieldDistrictsIds;//from districts to its ids

    private LocalDateTime registeredAt;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
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

