package net.c0nan.agic.models.result;

import lombok.Data;
import net.c0nan.agic.models.ToDoItem;

@Data
public class ToDoTestResult {
    private String input;
    private ToDoItem result;
}
