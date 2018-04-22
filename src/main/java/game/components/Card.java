package game.components;

public abstract class Card {
    //Carta generica

    private String name;
    private String description;

    //Creatori
    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }

    //Setter/Getter
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
