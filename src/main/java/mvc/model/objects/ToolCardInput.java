package mvc.model.objects;

import java.io.Serializable;

public class ToolCardInput implements Serializable {
    //Input utilizzo di carte strumento

    private Cell originCell1, originCell2;
    private Cell destinationCell1, destinationCell2;

    private int roundTrackRound;
    private Die roundTrackDie;

    private Die payDie;
    private Die chosenDie;

    private int choosenShade;

    private boolean increaseShade;

    //Costruttori
    public ToolCardInput(Cell originCell1, Cell originCell2, Cell destinationCell1, Cell destinationCell2, int roundTrackRound, Die roundTrackDie, Die payDie, Die chosenDie, int choosenShade, boolean increaseShade) {
        this.originCell1 = originCell1;
        this.originCell2 = originCell2;
        this.destinationCell1 = destinationCell1;
        this.destinationCell2 = destinationCell2;
        this.roundTrackRound = roundTrackRound;
        this.roundTrackDie = roundTrackDie;
        this.payDie = payDie;
        this.chosenDie = chosenDie;
        this.choosenShade = choosenShade;
        this.increaseShade = increaseShade;
    }
    //Lathekin
    public ToolCardInput(Cell originCell1, Cell originCell2, Cell destinationCell1, Cell destinationCell2) {
        this(originCell1, originCell2, destinationCell1, destinationCell2, 0, null, null, null, 0, true);
    }
    //Alesatore per lamina di rame, Pennello per eglomise
    public ToolCardInput(Cell originCell1, Cell destinationCell1) {
        this(originCell1, null, destinationCell1, null);
    }
    //Taglierina circolare
    public ToolCardInput(int roundTrackRound, Die roundTrackDie, Die chosenDie) {
        this(null, null, null, null, roundTrackRound, roundTrackDie, null, chosenDie, 0, true);
    }
    //Taglierina manuale
    public ToolCardInput(Cell originCell1, Cell originCell2, Cell destinationCell1, Cell destinationCell2, int roundTrackRound, Die roundTrackDie) {
        this(originCell1, originCell2, destinationCell1, destinationCell2, roundTrackRound, roundTrackDie, null, null, 0, true);
    }
    //Pennello per pasta salda, Tampone diamantato
    public ToolCardInput(Die chosenDie) {
        this(null, null, null, null, 0, null, null, chosenDie, 0, true);
    }
    //Diluente per pasta salda
    public ToolCardInput(Die chosenDie, int choosenShade) {
        this(null, null, null, null, 0, null, null, chosenDie, choosenShade, true);
    }
    //Pinza sgrossatrice
    public ToolCardInput(Die chosenDie, boolean increaseShade) {
        this(null, null, null, null, 0, null, null, chosenDie, 0, increaseShade);
    }
    //Tenaglia a rotelle, Martelletto, Riga di sughero
    public ToolCardInput() {
        this(null, null, null, null, 0, null, null, null, 0, true);
    }

    //Setter//Getter
    public void setOriginCell1(Cell originCell1) {
        this.originCell1 = originCell1;
    }
    public void setOriginCell2(Cell originCell2) {
        this.originCell2 = originCell2;
    }
    public void setDestinationCell1(Cell destinationCell1) {
        this.destinationCell1 = destinationCell1;
    }
    public void setDestinationCell2(Cell destinationCell2) {
        this.destinationCell2 = destinationCell2;
    }
    public void setRoundTrackRound(int roundTrackRound) {
        this.roundTrackRound = roundTrackRound;
    }
    public void setRoundTrackDie(Die roundTrackDie) {
        this.roundTrackDie = roundTrackDie;
    }
    public void setPayDie(Die payDie) {
        this.payDie = payDie;
    }
    public void setChosenDie(Die chosenDie) {
        this.chosenDie = chosenDie;
    }
    public void setChoosenShade(int choosenShade) {
        this.choosenShade = choosenShade;
    }
    public void setIncreaseShade(boolean increaseShade) {
        this.increaseShade = increaseShade;
    }

    public Cell getOriginCell1() {
        return originCell1;
    }
    public Cell getOriginCell2() {
        return originCell2;
    }
    public Cell getDestinationCell1() {
        return destinationCell1;
    }
    public Cell getDestinationCell2() {
        return destinationCell2;
    }
    public int getRoundTrackRound() {
        return roundTrackRound;
    }
    public Die getRoundTrackDie() {
        return roundTrackDie;
    }
    public Die getPayDie() {
        return payDie;
    }
    public Die getChosenDie() {
        return chosenDie;
    }
    public int getChoosenShade() {
        return choosenShade;
    }
    public boolean getIncreaseShade() {
        return increaseShade;
    }
}
