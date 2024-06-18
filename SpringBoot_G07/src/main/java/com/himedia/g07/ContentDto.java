package com.himedia.g07;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContentDto {

    @NotNull(message="Writer is Null")
    @NotEmpty(message="Writer is Empty")
    @Size(min=4, max=20, message="Writer min4, max20")
    private String writer;

    @NotNull(message="Content is Null")
    @NotEmpty(message="Content is Empty")
    private String content;
}
