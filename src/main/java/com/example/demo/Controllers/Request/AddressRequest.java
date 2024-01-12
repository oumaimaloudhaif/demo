package com.example.demo.Controllers.Request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressRequest {
    @NotNull
    @NotEmpty(message = "ADDRESS NAME NOT BE EMPTY")
    private String keyword;
}
