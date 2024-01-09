package com.example.demo.Controllers.Request;

import com.example.demo.Entities.Task;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskRequest extends Task {
    @NotNull
    @NotEmpty(message = "TASK NAME NOT BE EMPTY")
    private String keyword;
}
