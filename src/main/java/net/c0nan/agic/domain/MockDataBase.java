package net.c0nan.agic.domain;

import net.c0nan.agic.models.ToDoItem;

import java.util.HashMap;
import java.util.Map;

public class MockDataBase {

    private static Map<Long, ToDoItem> thisData = new HashMap<>();

    public static ToDoItem persist(final ToDoItem item) {
        if (item.getId() == null) {
            item.setId(thisData.size() + 1L);
            thisData.put(item.getId(), item);
        }
        return item;
    }

    public static ToDoItem read(final Long id) {
        return thisData.get(id);
    }

}
