package test.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.task.model.farmer.LegalForm;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchFarmerDto {

    private Optional<String> organisationName = Optional.empty();
    private Optional<LegalForm> legalForm = Optional.empty();
    private Optional<Long> INN = Optional.empty();
    private Optional<Long> KPP = Optional.empty();
    private Optional<Long> OGRN = Optional.empty();
    private Optional<String> districtRegisteredAt = Optional.empty();
    private Optional<List<String>> cropFieldDistricts = Optional.empty();
    private Optional<LocalDateTime> registeredAt = Optional.empty();
    private Optional<LocalDateTime> startDate = Optional.empty();
    private Optional<LocalDateTime> endDate = Optional.empty();
}
