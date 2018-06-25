package net.c0nan.agic.services;

import com.google.common.base.Strings;
import net.c0nan.agic.BaseTest;
import net.c0nan.agic.controllers.TasksController;
import net.c0nan.agic.exception.ValidationException;
import net.c0nan.agic.models.result.BalanceTestResult;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;

import static net.c0nan.agic.utils.TaskTestUtil.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class TaskServiceTest extends BaseTest {

    @TestSubject
    private TaskService taskService = new TaskService();

    private BalanceTestResult result;

    @Before
    public void setup() {
    }

    @Test
    public void testBasicValidateBrackets() {

        result = taskService.validateBrackets(BASIC_WORKING);
        assertTrue(result.isBalanced());

        result = taskService.validateBrackets(BASIC_NOT_WORKING);
        assertFalse(result.isBalanced());

        result = taskService.validateBrackets(BASIC_NOT_WORKING2);
        assertFalse(result.isBalanced());

        result = taskService.validateBrackets(BASIC_NOT_WORKING_EDGE);
        assertFalse(result.isBalanced());

    }

    @Test
    public void testComplexValidateBrackets() {
        result = taskService.validateBrackets(COMPLEX_WORKING);
        assertTrue(result.isBalanced());

        result = taskService.validateBrackets(COMPLEX_NOT_WORKING);
        assertFalse(result.isBalanced());

        result = taskService.validateBrackets(COMPLEX_NOT_WORKING2);
        assertFalse(result.isBalanced());
    }

    @Test
    public void testSizeValidation() {

        TasksController controller = new TasksController();
        setField(controller, "taskService", taskService);

        String value = "";
        value = Strings.padStart(value, 55, '{');
        value = Strings.padEnd(value, 110, '}');
        result = taskService.validateBrackets(value);
        try {
            controller.validateBrackets(value);
        } catch (Exception ex) {
            assertTrue(ex instanceof ValidationException);
        }

    }

}
