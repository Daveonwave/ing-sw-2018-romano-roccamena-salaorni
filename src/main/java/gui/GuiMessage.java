package gui;

import javafx.scene.control.Alert;


/**
 * creator of alert messagges
 */
public final class GuiMessage {
    //Funzioni gui di comunicazione con utente


    private GuiMessage() {
    }

    /**
     * creates an Alert showing an info message
     * @param title title of the Alert
     * @param header header of the Alert
     * @param content message of the Alert
     */
    private static void showInfo(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * calls the show Info method with header
     * @param title title of the Alert
     * @param message message of the Alert
     */
    private static void showInfo(String title, String message) {
        showInfo(title, "", message);
    }

    /**
     * creates an Alert showing an error message
     * @param title title of the Alert
     * @param header header of the Alert
     * @param content message of the Alert
     */
    private static void showError(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * calls the sow error method with header
     * @param title title of the Alert
     * @param message message of the Alert
     */
    private static void showError(String title, String message) {
        showError(title, "", message);
    }

    /**
     * calls the show info method with title
     * @param message message of the alert
     */
    public static void showInfo(String message) {
        GuiMessage.showInfo(FXGuiConstant.TITLE, message);
    }

    /**
     * calls the show error method with title
     * @param message message of the Alert
     */
    public static void showError(String message) {
        GuiMessage.showError(FXGuiConstant.TITLE, message);
    }
}