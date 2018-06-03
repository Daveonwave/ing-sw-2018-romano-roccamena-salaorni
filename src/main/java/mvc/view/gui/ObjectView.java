package mvc.view.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ObjectView {
    //Veduta grafica di un oggetto

    private ImageView imageView;

    public ObjectView( ImageView imageView) {
        this.imageView = imageView;
        this.imageView.setImage(imagePath());

    }
    //Setter/Getter
    public ImageView getImageView() {
        return imageView;
    }
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    //Percorso immagine
    public abstract Image imagePath();

}
