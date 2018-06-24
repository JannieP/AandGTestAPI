package net.c0nan.agic.models;

import lombok.Data;

@Data
public class TodoBody {
    private String text;
    private Boolean completed;
}
