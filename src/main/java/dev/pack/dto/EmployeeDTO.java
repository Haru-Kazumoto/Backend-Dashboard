package dev.pack.dto;

import dev.pack.model.enumeration.Gender;
import dev.pack.model.enumeration.JobRole;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeDTO {

    @NotEmpty(message = "Name is required.")
    private String name;

    @NotNull(message = "Id employee cannot be null.")
    @NotEmpty(message = "Id employee cannot be empty.")
    @Size(min = 3, max = 5, message = "Id employee must be in 3 to 5 characters.")
    @Pattern(regexp = "EM[0-9]+", message = "Id pattern must start with 'EM'.")
    private String numberEmployee;

    @Email(message = "Email pattern doesn't valid.")
    @NotEmpty(message = "Email is required.")
    private String email;

    @NotNull(message = "Gender is required.")
    private Gender gender;

    @NotNull(message = "Job Role is required.")
    private JobRole role;
}
