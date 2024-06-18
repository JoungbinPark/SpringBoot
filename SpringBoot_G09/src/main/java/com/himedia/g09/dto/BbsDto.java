package com.himedia.g09.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BbsDto {
    private int id;
    @NotEmpty(message="writer is Empty")
    @NotNull(message="writer is Null")
    private String writer;

    @NotEmpty(message="title is Empty")
    @NotNull(message="title is Null")
    private String title;

    @NotEmpty(message="content is Empty")
    @NotNull(message="content is Null")
    private String content;
}
