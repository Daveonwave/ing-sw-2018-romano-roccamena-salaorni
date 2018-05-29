package mvc;

import mvc.controller.AppController;
import mvc.stubs.AppViewStub;
import mvc.view.others.EmptyView;
import org.junit.Test;

import java.rmi.RemoteException;

public class ControllerTest extends MVCTest {
    //Test sul controllore mvc

    private static final String INVALID_OPERATION_MESSAGE = "invalid user operation accepted";
    private static final String FAILED_OPERATION_MESSAGE = "valid user operation failed";
    private static final String INVALID_STATE_MESSAGE = "invalid user state encountered";

    protected AppController controller;

    protected String token1 = "";
    protected String token2 = "";
    protected String token3 = "";
    protected String token4 = "";

    //Operazioni su test di utenti
    public String validLogin(String name, AppViewStub appView) {
        try {
            return controller.login(name, appView);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }

        return null;
    }
    public String invalidLogin(String name, AppViewStub appView) {
        try {
            controller.login(name, appView);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }

        return null;
    }

    public void validLogout(String token) {
        try {
            controller.logout(token);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidLogout(String token) {
        try {
            controller.logout(token);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }

    //Test
    @Test
    public void userControl() {
        //Crea view di test con controllore locale di prova
        this.controller = createTestController();
        AppViewStub view = new EmptyView(controller);

        //Login validi
        token1 = validLogin(user1.getName(), view);
        token2 = validLogin(user2.getName(), view);

        //Controllo stato registrazione
        if (controller.getModel().users.size()!=2)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token1))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token2))
            testAssertError(INVALID_STATE_MESSAGE);

        //Logut a caso
        invalidLogout("ciao");

        //Controllo stato corretto
        if (controller.getModel().users.size()!=2)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token1))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token2))
            testAssertError(INVALID_STATE_MESSAGE);

        //Login con stessi nomi
        invalidLogin(user1.getName(), view);
        invalidLogin(user2.getName(), view);

        //Controllo correttezza
        if (controller.getModel().users.size()!=2)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token1))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token2))
            testAssertError(INVALID_STATE_MESSAGE);

        //Logut e login di nuovo
        validLogout(token1);
        validLogout(token2);

        token1 = validLogin(user1.getName(), view);
        token2 = validLogin(user2.getName(), view);

        //Controllo correttezza
        if (controller.getModel().users.size()!=2)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token1))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token2))
            testAssertError(INVALID_STATE_MESSAGE);

        //Logout e login malformati
        invalidLogin(null, view);
        invalidLogin("", null);
        invalidLogin(null, null);
        invalidLogin(null, view);
        invalidLogout(null);

        //Nuovi login
        token3 = validLogin(user3.getName(), view);
        token4 = validLogin(user4.getName(), view);

        //Controllo correttezza
        if (controller.getModel().users.size()!=4)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token1))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token2))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token3))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token4))
            testAssertError(INVALID_STATE_MESSAGE);
    }

    public static void main(String[] args) throws RemoteException {
        //Crea un test
        ControllerTest test = new ControllerTest();

        //Lancia i test
        test.userControl();
        System.out.println("user control passed");
    }
}