package com.demo.Xparties.Tinder.Dto.PersonDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Valid
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PersonRequestDto {

    @NotNull
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Integer age;

    @NotNull
    private Character gender;

}
