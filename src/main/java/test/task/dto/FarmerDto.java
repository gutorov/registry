package test.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @Schema(description = "The name of the organisation associated with the farmer, up to 128 characters.", required = true, example = "Farm F")
    private String organisationName;

    @NotNull
    @Schema(description = "The legal form of the organisation, e.g., LLC, S-Corp.", required = true, example = "SOLE_PROPRIETORSHIP")
    private LegalForm legalForm;

    @NotNull
    @DigitCount(min = 10l, max = 12l, message = "INN must be between 10 and 12 digits")
    @Schema(description = "Taxpayer Identification Number, must be between 10 and 12 digits.", required = true, example = "1234567890")
    private Long INN;

    @DigitCount(min = 9l, max = 9l, message = "KPP must be 9 digits")
    @Schema(description = "Tax Registration Reason Code, must be exactly 9 digits.", required = true, example = "246801359")
    private Long KPP;

    @DigitCount(min = 13l, max = 13l, message = "OGRN must be 13 digits")
    @Schema(description = "Primary State Registration Number, must be exactly 13 digits.", required = true, example = "24680135792")
    private Long OGRN;

    @NotNull
    @Schema(description = "The district where the farmer is registered.", required = true, example = "South District")
    private String districtRegisteredAt;

    @NotNull
    @Schema(description = "List of names for districts where the farmer has crop fields.", required = true, example = "[]")
    private List<String> cropFieldDistricts;

    @Schema(
            description = "The date and time when the farmer was registered in the system."
    )
    private LocalDateTime registeredAt;

    @Schema(description = "Start date for the farmer's activity or relevance of the record.", type = "string", format = "date-time", example = "2023-01-01T00:00:00", hidden = true)
    private LocalDateTime startDate;

    @Schema(description = "End date for the farmer's activity or relevance of the record.", type = "string", format = "date-time", example = "2025-12-31T23:59:59", hidden = true)
    private LocalDateTime endDate;

}

