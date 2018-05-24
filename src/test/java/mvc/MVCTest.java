package mvc;

import mvc.controller.AppController;
import mvc.model.objects.User;
import base.BaseTest;
import base.EmptyView;

import java.util.ArrayList;
import java.util.List;

public class MVCTest extends BaseTest {
    //Test su controllo mvc

    public final User user1 = new User("john", new EmptyView(createTestController()));
    public final User user2 = new User("fred", new EmptyView(createTestController()));
    public final User user3 = new User("jack", new EmptyView(createTestController()));
    public final User user4 = new User("bob", new EmptyView(createTestController()));

    //Crea controller locale di test
    public static AppController createTestController() {
        return new AppController();
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
}
