package game.components;

import game.components.base.IdentifyStrategy;
import game.components.base.DieColor;
import game.components.base.Shade;

import java.util.ArrayList;
import java.util.List;

public class Die implements IdentifyStrategy<Die> {
    //Dado del gioco

    private DieColor color;
    private Shade shade;

    //Creatori
    public Die(DieColor color, Shade shade) {
        this.color = color;
        this.shade = shade;
    }

    //Setter/Getter
    public void setColor(DieColor color) {
        this.color = color;
    }
    public void setShade(Shade shade) {
        this.shade = shade;
    }

    public DieColor getColor() {
        return color;
    }
    public Shade getShade() {
        return shade;
    }

    //Identificazione
    public boolean isSame(Die obj) {
        return color == obj.color && shade == obj.shade;
    }






    public static void main(String[] args) {
        int numberOfDice = 100;

        List<Die> rolledDice = new ArrayList<Die>();

        for(int i = 0; i < numberOfDice; i++){
            rolledDice.add(new Die(DieColor.randomColor(), Shade.randomShade()));
        }

        for (Die dado: rolledDice) {
            System.out.println("Dado " + dado.color + " con valore " + dado.getShade());
        }






    }
}
