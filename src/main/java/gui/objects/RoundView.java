package gui.objects;

import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.List;

/**
 * view of a round
 */
public class RoundView implements Serializable{

    private ImageView imageView;
    private List<DieView> dieViews;
    private int round;

    /**
     *
     * @param imageView image view of this round
     * @param dieViews dice left in the round
     * @param round round associated to this round view
     */
    public RoundView(ImageView imageView, List<DieView> dieViews, int round) {
        this.imageView = imageView;
        this.dieViews = dieViews;
        this.round = round;
    }

    //Getter/Setter
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
    public void setRound(int round) {
        this.round = round;
    }
}
