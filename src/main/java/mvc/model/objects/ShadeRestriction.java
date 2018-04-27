package mvc.model.objects;

public class ShadeRestriction extends CellRestriction {
    //Restrizione di shade

    private int shade;

    //Costruttori
    public ShadeRestriction(int shade) {
        this.shade = shade;
    }

    //Setter/Getter
    public void setShade(int shade) {
        this.shade = shade;
    }

    public int getShade() {
        return shade;
    }

    //Verifica che un dado rispetti la restrizione
    public boolean canPlaceDie(Die die) {
        return shade == die.getShade();
    }
}
