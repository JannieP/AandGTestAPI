package net.c0nan.agic.domain;

import net.c0nan.agic.models.ToDoItem;

import java.util.HashMap;
import java.util.Map;

public class MockDataBase {

    private static Map<Integer, ToDoItem> thisData = new HashMap<>();

    public static ToDoItem persist(final ToDoItem item) {
        if (item.getId() == null) {
            item.setId(thisData.size() + 1);
            thisData.put(item.getId(), item);
        }
        return item;
    }

    public static ToDoItem read(final Integer id) {
        return thisData.get(id);
    }

}
