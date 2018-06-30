package mvc.model.objects;

import java.io.Serializable;

/**
 * Input for the usage of a tool card
 */
public class ToolCardInput implements Serializable {
    //Input utilizzo di carte strumento

    private Cell originCell1;
    private Cell originCell2;
    private Cell destinationCell1;
    private Cell destinationCell2;

    private int roundTrackRound;
    private Die roundTrackDie;

    private Die payDie;
    private Die chosenDie;

    private int chosenShade;

    private boolean increaseShade;

    //Costruttori
    /**
     * Generic constructor
     * @param originCell1 first start cell
     * @param originCell2 second start cell
     * @param destinationCell1 first destination cell
     * @param destinationCell2 second destination cell
     * @param roundTrackRound round of the round track
     * @param roundTrackDie die of the round track
     * @param payDie die to spent
     * @param chosenDie selected die
     * @param chosenShade selected shade
     * @param increaseShade increase the value of a die
     */
    public ToolCardInput(Cell originCell1, Cell originCell2, Cell destinationCell1, Cell destinationCell2, int roundTrackRound, Die roundTrackDie, Die payDie, Die chosenDie, int chosenShade, boolean increaseShade) {
        this.originCell1 = originCell1;
        this.originCell2 = originCell2;
        this.destinationCell1 = destinationCell1;
        this.destinationCell2 = destinationCell2;
        this.roundTrackRound = roundTrackRound;
        this.roundTrackDie = roundTrackDie;
        this.payDie = payDie;
        this.chosenDie = chosenDie;
        this.chosenShade = chosenShade;
        this.increaseShade = increaseShade;
    }
    //Lathekin
    /**
     * Constructor used for "Lathekin"
     * @param originCell1 first start cell
     * @param originCell2 second start cell
     * @param destinationCell1 first destination cell
     * @param destinationCell2 second destination cell
     */
    public ToolCardInput(Cell originCell1, Cell originCell2, Cell destinationCell1, Cell destinationCell2) {
        this(originCell1, originCell2, destinationCell1, destinationCell2, 0, null, null, null, 0, true);
    }
    //Alesatore per lamina di rame, Pennello per eglomise
    /**
     * Constructor for "Alesatore di Rame" and "Pennello per Englomise"
     * @param originCell1 start cell
     * @param destinationCell1 destination cell
     */
    public ToolCardInput(Cell originCell1, Cell destinationCell1) {
        this(originCell1, null, destinationCell1, null);
    }
    //Taglierina circolare
    /**
     * Constructor for "Taglierina Circolare"
     * @param roundTrackRound round of the round track
     * @param roundTrackDie die of the round track
     * @param chosenDie selected die
     */
    public ToolCardInput(int roundTrackRound, Die roundTrackDie, Die chosenDie) {
        this(null, null, null, null, roundTrackRound, roundTrackDie, null, chosenDie, 0, true);
    }
    //Taglierina manuale
    /**
     * Constructor for "Taglierina Manuale"
     * @param originCell1 first start cell
     * @param originCell2 second start cell
     * @param destinationCell1 first destination cell
     * @param destinationCell2 second destination cell
     * @param roundTrackRound round of the round track
     * @param roundTrackDie die of the round track
     */
    public ToolCardInput(Cell originCell1, Cell originCell2, Cell destinationCell1, Cell destinationCell2, int roundTrackRound, Die roundTrackDie) {
        this(originCell1, originCell2, destinationCell1, destinationCell2, roundTrackRound, roundTrackDie, null, null, 0, true);
    }
    //Pennello per pasta salda, Tampone diamantato
    /**
     * Constructor for "Pennello per Pasta Salda" and "Tampone Diamantato"
     * @param chosenDie selected die
     */
    public ToolCardInput(Die chosenDie) {
        this(null, null, null, null, 0, null, null, chosenDie, 0, true);
    }
    //Diluente per pasta salda
    /**
     * Constructor for "Diluente per Pasta Salda"
     * @param chosenDie selected die
     * @param chosenShade selected shade
     */
    public ToolCardInput(Die chosenDie, int chosenShade) {
        this(null, null, null, null, 0, null, null, chosenDie, chosenShade, true);
    }
    //Pinza sgrossatrice
    /**
     * Constructor for "Pinza Sgrossatrice"
     * @param chosenDie selected die
     * @param increaseShade increase value of a die
     */
    public ToolCardInput(Die chosenDie, boolean increaseShade) {
        this(null, null, null, null, 0, null, null, chosenDie, 0, increaseShade);
    }
    //Tenaglia a rotelle, Martelletto, Riga di sughero
    /**
     * Constructor for "Tenaglia a Rotelle", "Martelletto" and "Riga di Sughero"
     */
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
    public void setChosenShade(int chosenShade) {
        this.chosenShade = chosenShade;
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
    public int getChosenShade() {
        return chosenShade;
    }
    public boolean getIncreaseShade() {
        return increaseShade;
    }
}
