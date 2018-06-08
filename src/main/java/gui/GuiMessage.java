package gui;

import javafx.scene.control.Alert;

public final class GuiMessage {
    //Funzioni gui di comunicazione con utente

    public static void showInfo(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public static void showInfo(String title, String message) {
        showInfo(title, "", message);
    }
    public static void showError(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public static void showError(String title, String message) {
        showError(title, "", message);
    }
}