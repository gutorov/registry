package test.task.dto.district;

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

    @NotNull
    private Long code;

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 100, message = "Name must be in the range of 3-100 symbols")
    private String name;
}
