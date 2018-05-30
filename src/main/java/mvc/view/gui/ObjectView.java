package mvc.view.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ObjectView {
    //Veduta grafica di un oggetto

    private Button button;
    private ImageView imageView;

    //Costruttori
    public ObjectView(Button button, ImageView imageView) {
        this.button = button;
        this.imageView = imageView;
        this.imageView.setImage(imagePath());
        if(button != null){
            button.setGraphic(imageView);
        }
    }

    //Setter/Getter
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

    //Percorso immagine
    public abstract Image imagePath();

}
