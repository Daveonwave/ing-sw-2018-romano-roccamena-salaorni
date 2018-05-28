package mvc.view.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ObjectView {

    private Button button;
    private ImageView imageView;

    public ObjectView(Button button, ImageView imageView) {
        this.button = button;
        this.imageView = imageView;
        this.imageView.setImage(imagePath());
        if(button != null){
            button.setGraphic(imageView);
        }
    }

    public Button getButton() {
        return button;
    }
    public ImageView getImageView() {
        return imageView;
    }
    public void setButton(Button button) {
        this.button = button;
    }
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public abstract Image imagePath();

}
