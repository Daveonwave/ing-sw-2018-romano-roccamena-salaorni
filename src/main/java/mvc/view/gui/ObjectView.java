package mvc.view.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ObjectView {

    private ImageView imageView;

    public ObjectView( ImageView imageView) {
        this.imageView = imageView;
        this.imageView.setImage(imagePath());
    }
    public ImageView getImageView() {
        return imageView;
    }
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public abstract Image imagePath();

}
