package mvc.controller;

import mvc.model.objects.*;
import mvc.view.ViewResponse;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Controller extends Remote{
    //Controllore remoto

    //User
    String login(String name, ViewResponse view) throws RemoteException;
    void logout(String token) throws RemoteException;

    //Match
    String joinMatch(String tokenUser, int playersCount) throws RemoteException;
    void leaveMatch(String tokenUser, String tokenMatch) throws RemoteException;
    void chooseWindow(String tokenUser, String tokenMatch, Window window) throws RemoteException;
    void placeDie(String tokenUser, String tokenMatch, Cell cell, Die die) throws RemoteException;
    void useToolCard(String tokenUser, String tokenMatch, Match match, ToolCard toolCard) throws RemoteException;
    void endTurn(String tokenUser, String tokenMatch) throws RemoteException;
}
