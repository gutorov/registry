package test.task.model.farmer;

import test.task.model.District;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "organisation_name")
    private String organisationName;

    @Column(name = "legal_form")
    @Enumerated(value = EnumType.STRING)
    private LegalForm legalForm;

    @Column(name = "taxpayer_identification_number_inn")
    private int INN;

    @Column(name = "tax_registration_reason_code_kpp")
    private int KPP;

    @Column(name = "primary_state_registration_number_ogrn")
    private int OGRN;

    @OneToOne
    private District registrationDistrict;
    private List<District> cropFieldDistricts;
    private Boolean isArchived;






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
