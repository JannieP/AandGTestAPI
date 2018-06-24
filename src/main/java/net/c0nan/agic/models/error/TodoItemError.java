package net.c0nan.agic.models.error;

import lombok.Data;

import java.util.ArrayList;

@Data
public class TodoItemError<T extends ErrorDetail> {
    private ArrayList<T> details = new ArrayList<>();
    private String name;
}
