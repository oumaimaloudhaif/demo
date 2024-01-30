package com.example.demo.Dto;

import com.example.demo.Enums.Priority;
import com.example.demo.Enums.TaskStatus;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public record TaskDTO (String name,String description,@Enumerated(EnumType.STRING) Priority priority,@Enumerated(EnumType.STRING) TaskStatus taskStatus){
}
