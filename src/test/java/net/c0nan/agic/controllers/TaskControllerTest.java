package net.c0nan.agic.controllers;

import com.google.common.base.Strings;
import net.c0nan.agic.BaseTest;
import net.c0nan.agic.models.result.BalanceTestResult;
import net.c0nan.agic.services.TaskService;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertTrue;

public class TaskControllerTest extends BaseTest {

    @TestSubject
    private TasksController controller = new TasksController();

    private BalanceTestResult result;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(controller, "taskService", new TaskService());
    }

    @Test
    public void testSizeValidation() {
        String value = Strings.padStart("", 55, '{');
        value = Strings.padEnd(value, 110, '}');
        result = controller.validateBrackets(value);
        assertTrue(result.isBalanced());
    }

}
