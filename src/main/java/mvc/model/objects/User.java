package mvc.model.objects;

import mvc.stubs.AppViewStub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
    //Utente dell'applicazione

    private String name;
    private AppViewStub appView;
    private List<Player> players;

    //Costruttori
    public User(String name, AppViewStub appView, List<Player> players) {
        this.name = name;
        this.appView = appView;
        this.players = players;
    }
    public User(String name, AppViewStub appView) {
        this(name, appView, new ArrayList<Player>());
    }

    //Setter/Getter
    public void setName(String name) {
        this.name = name;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public void setAppView(AppViewStub appView) {
        this.appView = appView;
    }

    public String getName() {
        return name;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public AppViewStub getAppView() {
        return appView;
    }

    //Verifica uguaglianze
    public boolean sameUser(User user) {
        if (user==null)
            return false;

        String otherName = user.getName();

        if (otherName == null)
            return false;

        return otherName.equals(name);
    }

    //Operazioni su giocatori associati
    public void addPlayer(Player player){
        players.add(player);
    }
    public void removePlayer(Player player){
        players.remove(player);
    }
}
