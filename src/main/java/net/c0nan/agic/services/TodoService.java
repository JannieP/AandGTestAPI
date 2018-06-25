package net.c0nan.agic.services;

import net.c0nan.agic.exception.NotFoundException;
import net.c0nan.agic.exception.ValidationException;
import net.c0nan.agic.models.ToDoItem;
import net.c0nan.agic.models.request.ToDoItemAddRequest;
import net.c0nan.agic.models.request.ToDoItemUpdateRequest;
import org.springframework.stereotype.Service;

import static net.c0nan.agic.domain.MockDataBase.persist;
import static net.c0nan.agic.domain.MockDataBase.read;
import static net.c0nan.agic.utils.TimeStampUtil.getTimeStamp;

@Service
public class TodoService {

    private static final Integer MAX_LENGTH = 50;

    public ToDoItem postNew(final ToDoItemAddRequest body) {
        validateInput(body.getText());
        ToDoItem result;
        synchronized (this) {
            result = persist(map(body));
        }
        return result;
    }

    public ToDoItem get(final Integer id) {
        ToDoItem item = read(id);
        if (item == null) {
            throw new NotFoundException(String.format("Item with id %s not found", id.toString()));
        }
        return item;
    }

    public ToDoItem update(final Integer id, final ToDoItemUpdateRequest body) {

        ToDoItem item = get(id);

        if (body.getText() != null) {
            validateInput(body.getText());
            item.setText(body.getText());
        }
        if (body.getCompleted() != null) {
            item.setCompleted(body.getCompleted());
        }
        return read(id);
    }

    private ToDoItem map(final ToDoItemAddRequest body) {
        ToDoItem item = new ToDoItem();
        item.setCreatedAt(getTimeStamp());
        item.setText(body.getText());
        return item;
    }

    public void validateInput(final String input) {
        if (input.length() > MAX_LENGTH) {
            throw new ValidationException(String.format("Must be between 1 and %d chars long", MAX_LENGTH));
        }
    }

}
