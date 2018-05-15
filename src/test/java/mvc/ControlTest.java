package mvc;

import mvc.controller.AppController;
import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;
import objects.BaseTest;
import objects.EmptyView;
import org.junit.Test;

import java.rmi.RemoteException;

public class ControlTest extends BaseTest {
    //Test sul controllo mvc

    //Test
    @Test
    public void twoPlayerLocal1() {
        //Crea view di test con controllore locale di prova
        AppViewStub view = new EmptyView(new AppController());

        //Controllore di prova
        AppControllerStub controller = view.getAppController();

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

        //Logut a caso
        try {
            controller.logout("ciao");

            testAssertError("random logout accepted without errors");
        } catch (RemoteException e) {
        }

        //Login con stessi nomi
        try {
            controller.login(name1, view);
            controller.login(name2, view);

            testAssertError("multiple name login accepted without errors");
        } catch (RemoteException e) {
        }

        //Logut e login di nuovo
        try {
            controller.logout(token1);
            controller.logout(token2);
            token1 = controller.login(name1, view);
            token2 = controller.login(name2, view);
        } catch (RemoteException e) {
            testAssertError("during logout and relogin: " + e.getMessage());
        }

        //Partecipa partita
        try {
            controller.joinMatch(token1, 2);
            controller.joinMatch(token2, 2);
        } catch (RemoteException e) {
            testAssertError("during join mvc.match: " + e.getMessage());
        } catch (Exception e) {
            testAssertError("in fase di sviluppo matchCreator");
        }
    }

    public static void main(String[] args) throws RemoteException {
        //Crea un test
        ControlTest test = new ControlTest();

        //Lancia i test
        test.twoPlayerLocal1();
    }
}