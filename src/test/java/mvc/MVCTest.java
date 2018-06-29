package mvc;

import config.TimerConfig;
import mvc.controller.AppController;
import mvc.model.objects.*;
import base.BaseTest;
import mvc.model.objects.enums.DieColor;
import mvc.view.others.EmptyView;

import java.util.ArrayList;
import java.util.List;

public class MVCTest extends BaseTest {
    //Test su controllo mvc

    private static final String INVALID_OPERATION_MESSAGE = "invalid match operation accepted";
    private static final String FAILED_OPERATION_MESSAGE = "valid match operation failed";
    private static final String INVALID_STATE_MESSAGE = "invalid match state encountered";

    protected final User user1 = new User("john", new EmptyView(createTestController()));
    protected final User user2 = new User("fred", new EmptyView(createTestController()));
    protected final User user3 = new User("jack", new EmptyView(createTestController()));
    protected final User user4 = new User("bob", new EmptyView(createTestController()));

    //Crea controller locale di test
    public AppController createTestController() {
        AppController output = new AppController(new TimerConfig(30000, 30000));

        output.getModel().resetAppModel();

        return output;
    }

    //Crea liste di utenti di test
    public List<User> createTwoUsersList() {
        List<User> result = new ArrayList<User>();

        result.add(user1);
        result.add(user2);

        return result;
    }
    public List<User> createThreeUsersList() {
        List<User> result = new ArrayList<User>();

        result.add(user1);
        result.add(user2);
        result.add(user3);

        return result;
    }
    public List<User> createFourUsersList() {
        List<User> result = new ArrayList<User>();

        result.add(user1);
        result.add(user2);
        result.add(user3);
        result.add(user4);

        return result;
    }

    //Crea altri oggetti
    public Die createInvalidDie1() {
        return new Die(DieColor.YELLOW, 1);
    }
    public Cell createInvalidCell1() {
        return new Cell(createInvalidDie1(), new NoRestriction(), 2, 2);
    }
    public Window createInvalidWindow1() {
        return new Window(null, 5, "");
    }
    public ToolCardInput createInvalidToolCardInput1() {
        return new ToolCardInput(null, null, null, null, 0, null, createInvalidDie1(), createInvalidDie1(), 1, true);
    }
}
