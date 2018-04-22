package mvc.model.objects;

public class Die {
    //Dado del gioco

    private DieColor color;
    private int shade;

    //Creatori
    public Die(DieColor color, int shade) {
        this.color = color;
        this.shade = shade;
    }

    //Setter/Getter
    public void setColor(DieColor color) {
        this.color = color;
    }
    public void setShade(int shade) {
        this.shade = shade;
    }

    public DieColor getColor() {
        return color;
    }
    public int getShade() {
        return shade;
    }
}
