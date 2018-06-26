package mvc.model.objects;

import java.io.Serializable;

/**
 * Generic card object of the game
 */
public class Card implements Serializable {
    //Carta generica

    private String name;
    private String description;

    //Costruttori
    /**
     * Create new card
     * @param name Name of the card
     * @param description Description of the card
     */
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
    /**
     * Assert equality of two cards
     * @param card card instance to test
     * @return
     */
    public boolean sameCard(Card card) {
        if (card == null)
            return false;

        return card.name.equals(name);
    }
}
