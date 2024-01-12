package com.example.demo.Controllers.Request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyRequest {
    @NotNull
    @NotEmpty(message = "COMPANY NAME NOT BE EMPTY")
    private String keyword;
}
