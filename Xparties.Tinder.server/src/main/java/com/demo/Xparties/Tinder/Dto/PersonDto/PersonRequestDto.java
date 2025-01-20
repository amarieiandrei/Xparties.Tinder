package com.demo.Xparties.Tinder.Dto.PersonDto;

import com.demo.Xparties.Tinder.Model.Enums.PersonGender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Valid
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PersonRequestDto {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Integer age;

    @NotNull
    private PersonGender gender;
}
