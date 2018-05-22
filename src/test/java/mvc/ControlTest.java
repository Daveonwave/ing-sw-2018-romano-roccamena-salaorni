package mvc;

import mvc.controller.AppController;
import mvc.stubs.AppViewStub;
import objects.BaseTest;
import objects.EmptyView;
import org.junit.Test;

import java.rmi.RemoteException;

public class ControlTest extends BaseTest {
    //Test sul controllo mvc

    private static final String INVALID_ACTION_MESSAGE = "invalid user operation accepted";
    private static final String FAILED_OPERATION_MESSAGE = "correct user operation failed";

    //Test
    @Test
    public void userControl() {
        //Crea view di test con controllore locale di prova
        AppController controller = new AppController();
        AppViewStub view = new EmptyView(controller);

        //Dati partecipanti
        String name1 = "jack";
        String name2 = "john";

        String token1 = "";
        String token2 = "";

        //Login
        try {
            token1 = controller.login(name1, view);
            token2 = controller.login(name2, view);
        } catch (RemoteException e) {
            testAssertError("during login: " + e.getMessage());
        }

        if (controller.getModel().users.size()!=2)
            testAssertError(FAILED_OPERATION_MESSAGE);
        if (!controller.getModel().users.containsKey(token1))
            testAssertError(FAILED_OPERATION_MESSAGE);
        if (!controller.getModel().users.containsKey(token2))
            testAssertError(FAILED_OPERATION_MESSAGE);

        //Logut a caso
        try {
            controller.logout("ciao");
            testAssertError(INVALID_ACTION_MESSAGE);
        } catch (RemoteException e) {
        }

        if (controller.getModel().users.size()!=2)
            testAssertError(FAILED_OPERATION_MESSAGE);
        if (!controller.getModel().users.containsKey(token1))
            testAssertError(FAILED_OPERATION_MESSAGE);
        if (!controller.getModel().users.containsKey(token2))
            testAssertError(FAILED_OPERATION_MESSAGE);

        //Login con stessi nomi
        try {
            controller.login(name1, view);
            testAssertError(INVALID_ACTION_MESSAGE);
        } catch (RemoteException e) {
        }
        try {
            controller.login(name2, view);
            testAssertError(INVALID_ACTION_MESSAGE);
        } catch (RemoteException e) {
        }

        //Controllo correttezze registrazioni
        if (controller.getModel().users.size()!=2)
            testAssertError(FAILED_OPERATION_MESSAGE);
        if (!controller.getModel().users.containsKey(token1))
            testAssertError(FAILED_OPERATION_MESSAGE);
        if (!controller.getModel().users.containsKey(token2))
            testAssertError(FAILED_OPERATION_MESSAGE);

        //Logut e login di nuovo
        try {
            controller.logout(token1);
            controller.logout(token2);
            token1 = controller.login(name1, view);
            token2 = controller.login(name2, view);
        } catch (RemoteException e) {
            testAssertError("during logout and relogin: " + e.getMessage());
        }

        if (controller.getModel().users.size()!=2)
            testAssertError(FAILED_OPERATION_MESSAGE);
        if (!controller.getModel().users.containsKey(token1))
            testAssertError(FAILED_OPERATION_MESSAGE);
        if (!controller.getModel().users.containsKey(token2))
            testAssertError(FAILED_OPERATION_MESSAGE);

        //Logout e login malformati
        try {
            controller.login(null, view);
            testAssertError(INVALID_ACTION_MESSAGE);
        } catch (RemoteException e) {
        }
        try {
            controller.login("", null);
            testAssertError(INVALID_ACTION_MESSAGE);
        } catch (RemoteException e) {
        }
        try {
            controller.login(null, null);
            testAssertError(INVALID_ACTION_MESSAGE);
        } catch (RemoteException e) {
        }
        try {
            controller.logout(null);
            testAssertError(INVALID_ACTION_MESSAGE);
        } catch (RemoteException e) {
        }
    }

    public static void main(String[] args) throws RemoteException {
        //Crea un test
        ControlTest test = new ControlTest();

        //Lancia i test
        test.userControl();

    }
}