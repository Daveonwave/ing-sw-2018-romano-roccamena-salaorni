package mvc.model.objects;

import mvc.model.objects.enums.DieColor;

public class Die {
    //Dado del gioco

    private DieColor color;
    private int shade;
    private int xPosition;
    private int yPosition;

    //Creatori

    public Die(DieColor color, int shade, int xPosition, int yPosition) {
        this.color = color;
        this.shade = shade;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    //Setter/Getter

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

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

    //metodo che cambia il valore di un dado con il suo opposto

    public void oppositeShade(){
        switch (this.shade){
            case 1:
                this.setShade(6);
                break;
            case 2:
                this.setShade(5);
                break;
            case 3:
                this.setShade(4);
                break;
            case 4:
                this.setShade(3);
                break;
            case 5:
                this.setShade(2);
                break;
            case 6:
                this.setShade(1);
                break;
                default:
                    System.out.println("invalid shade value");
        }
    }
}
