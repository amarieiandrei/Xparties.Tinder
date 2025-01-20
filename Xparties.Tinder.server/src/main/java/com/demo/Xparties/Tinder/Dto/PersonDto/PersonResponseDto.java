package com.demo.Xparties.Tinder.Dto.PersonDto;

import com.demo.Xparties.Tinder.Model.Enums.PersonGender;
import jakarta.validation.Valid;
import lombok.*;

@Valid
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PersonResponseDto {

    private String externalId;

    private String email;

    private String firstName;

    private String lastName;

    private Integer age;

    private PersonGender gender;

    // TODO: create password important for Person
}
