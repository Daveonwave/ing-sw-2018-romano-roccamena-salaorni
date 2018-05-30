package mvc.model.objects;

import java.io.Serializable;

public class Card implements Serializable {
    //Carta generica

    private String name;
    private String description;

    //Costruttori
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

    //Verifica uguaglianze
    public boolean sameCard(Card card) {
        if (card == null)
            return false;

        return card.name.equals(name);
    }
}
