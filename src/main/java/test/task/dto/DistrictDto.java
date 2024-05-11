package test.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDto {

    @Schema(description = "Name of the district", example = "Central District", required = true)
    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 100, message = "Name must be in the range of 3-100 symbols")
    private String name;

    @Schema(description = "Code of the district", example = "100", required = true)
    @NotNull
    private Long code;
}