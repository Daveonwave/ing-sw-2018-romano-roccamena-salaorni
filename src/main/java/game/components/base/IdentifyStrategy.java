package game.components.base;

public interface IdentifyStrategy<T> {
    //Generica identificazione

    boolean isSame(T obj);
}
