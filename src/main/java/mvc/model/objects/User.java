package mvc.model.objects;

import java.util.List;

public class User {
    //Utente dell'applicazione

    private String name;
    private List<Player> players;

    //Costruttori
    public User(String name, List<Player> players) {
        this.name = name;
        this.players = players;
    }

    //Setter/Getter
    public void setName(String name) {
        this.name = name;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getName() {
        return name;
    }
    public List<Player> getPlayers() {
        return players;
    }
}
