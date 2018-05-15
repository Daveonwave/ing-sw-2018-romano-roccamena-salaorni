package mvc.view.gui;

import mvc.model.objects.Window;

public class WindowView {

    private Window window;
    private String imagePath;

    public WindowView(Window window, String imagePath) {
        this.window = window;
        this.imagePath = imagePath;
    }

    public Window getWindow() {
        return window;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
