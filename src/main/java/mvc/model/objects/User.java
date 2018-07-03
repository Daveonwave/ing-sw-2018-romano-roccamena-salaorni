package mvc.model.objects;

import mvc.stubs.AppViewStub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User of the application
 */
public class User implements Serializable {
    //Utente dell'applicazione

    private String name;
    private AppViewStub appView;
    private List<Player> players;

    //Costruttori
    /**
     * Create new user
     * @param name User's name
     * @param appView Application view of the user
     * @param players Players associated with the user
     */
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
    /**
     * Asserts equality of two users
     * @param user User instance
     * @return
     */
    public boolean sameUser(User user) {
        if (user==null)
            return false;

        String otherName = user.getName();

        if (otherName == null)
            return false;

        return otherName.equals(name);
    }

    //Operazioni su giocatori associati
    /**
     * Register a player for the user
     * @param player
     */
    public void addPlayer(Player player){
        players.add(player);
    }
    /**
     * Unregister a player for the user
     * @param player
     */
    public void removePlayer(Player player){
        players.remove(player);
    }
}
