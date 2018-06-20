package gui.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class ObjectView implements Serializable{
    //Veduta grafica di un oggetto

    private ImageView imageView;

    //Costruttori
    public ObjectView( ImageView imageView) {
        this.imageView = imageView;

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
