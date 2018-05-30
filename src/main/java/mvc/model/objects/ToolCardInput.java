package mvc.model.objects;

public class ToolCardInput {
    //Input utilizzo di carte strumento

    private Cell originCell1, originCell2;
    private Cell destinationCell1, destinationCell2;

    private int roundTrackRound;
    private Die roundTrackDie;

    private Die payDie;
    private Die choosenDie;

    private int choosenShade;

    private boolean increaseShade;

    //Costruttori
    public ToolCardInput(Cell originCell1, Cell originCell2, Cell destinationCell1, Cell destinationCell2, int roundTrackRound, Die roundTrackDie, Die payDie, Die choosenDie, int choosenShade, boolean increaseShade) {
        this.originCell1 = originCell1;
        this.originCell2 = originCell2;
        this.destinationCell1 = destinationCell1;
        this.destinationCell2 = destinationCell2;
        this.roundTrackRound = roundTrackRound;
        this.roundTrackDie = roundTrackDie;
        this.payDie = payDie;
        this.choosenDie = choosenDie;
        this.choosenShade = choosenShade;
        this.increaseShade = increaseShade;
    }
    public ToolCardInput(Cell originCell1, Cell originCell2, Cell destinationCell1, Cell destinationCell2) {
        this(originCell1, originCell2, destinationCell1, destinationCell2, 0, null, null, null, 0, true);
    }
    public ToolCardInput(int roundTrackRound, Die roundTrackDie, Die choosenDie) {
        this(null, null, null, null, roundTrackRound, roundTrackDie, null, choosenDie, 0, true);
    }
    public ToolCardInput(Die choosenDie) {
        this(null, null, null, null, 0, null, null, choosenDie, 0, true);
    }
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
    public void setChoosenDie(Die choosenDie) {
        this.choosenDie = choosenDie;
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
    public Die getChoosenDie() {
        return choosenDie;
    }
    public int getChoosenShade() {
        return choosenShade;
    }
    public boolean getIncreaseShade() {
        return increaseShade;
    }
}
