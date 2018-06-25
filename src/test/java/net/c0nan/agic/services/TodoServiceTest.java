package net.c0nan.agic.services;

import net.c0nan.agic.BaseTest;
import net.c0nan.agic.exception.NotFoundException;
import net.c0nan.agic.exception.ValidationException;
import net.c0nan.agic.models.ToDoItem;
import net.c0nan.agic.models.request.ToDoItemAddRequest;
import net.c0nan.agic.models.request.ToDoItemUpdateRequest;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;

import static net.c0nan.agic.utils.ToDoTestUtil.TEXT;
import static net.c0nan.agic.utils.ToDoTestUtil.TEXT2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TodoServiceTest extends BaseTest {

    @TestSubject
    private TodoService todoService = new TodoService();

    private Long id = 0L;

    @Before
    public void setup() {
    }

    @Test
    public void testPostNewMaxLength() {
        ToDoItemAddRequest request = new ToDoItemAddRequest();
        request.setText(TEXT + TEXT + TEXT);

        try {
            ToDoItem item = todoService.postNew(request);
        } catch (Exception ex) {
            assertTrue(ex instanceof ValidationException);
        }

    }

    @Test
    public void testPostNew() {
        ToDoItemAddRequest request = new ToDoItemAddRequest();
        request.setText(TEXT);
        ToDoItem item = todoService.postNew(request);

        id = item.getId();

        assertEquals(item.getText(), TEXT);
        assertTrue(item.getId() > 0);

    }

    @Test
    public void testGet() {

        testPostNew();

        ToDoItem item = todoService.get(id);

        assertEquals(item.getText(), TEXT);
        assertTrue(item.getId() > 0);
        assertEquals(item.getId(), id);
        assertEquals(item.getCompleted(), false);

    }

    @Test
    public void testGetNotFound() {

        try {
            todoService.get(12345L);
        } catch (Exception ex) {
            assertTrue(ex instanceof NotFoundException);
        }

    }

    @Test
    public void testPatch() {

        testGet();

        ToDoItemUpdateRequest request = new ToDoItemUpdateRequest();

        ToDoItem item = todoService.update(id, request);

        assertEquals(item.getText(), TEXT);
        assertTrue(item.getId() > 0);
        assertEquals(item.getId(), id);
        assertEquals(item.getCompleted(), false);

        request.setText(TEXT2);
        request.setCompleted(true);

        item = todoService.update(id, request);

        assertEquals(item.getText(), TEXT2);
        assertTrue(item.getId() > 0);
        assertEquals(item.getId(), id);
        assertEquals(item.getCompleted(), true);

    }

}
