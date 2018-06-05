package mvc.view.gui;

import javafx.scene.image.ImageView;
import mvc.view.gui.DieView;

import java.util.List;

public class RoundView {

    private ImageView imageView;
    private List<DieView> dieViews;
    private int round;

    public RoundView(ImageView imageView, List<DieView> dieViews, int round) {
        this.imageView = imageView;
        this.dieViews = dieViews;
        this.round = round;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public List<DieView> getDieViews() {
        return dieViews;
    }

    public int getRound() {
        return round;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setDieViews(List<DieView> dieViews) {
        this.dieViews = dieViews;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
