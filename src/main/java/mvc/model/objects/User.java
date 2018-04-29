package mvc.model.objects;

import mvc.view.AppView;

import java.util.List;

public class User {
    //Utente dell'applicazione

    private String name;
    private AppView appView;
    private List<Player> players;

    //Costruttori
    public User(String name, AppView appView, List<Player> players) {
        this.name = name;
        this.appView = appView;
        this.players = players;
    }

    //Setter/Getter
    public void setName(String name) {
        this.name = name;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public void setAppView(AppView appView) {
        this.appView = appView;
    }

    public String getName() {
        return name;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public AppView getAppView() {
        return appView;
    }
}
