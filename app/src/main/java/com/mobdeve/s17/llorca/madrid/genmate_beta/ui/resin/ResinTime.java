package com.mobdeve.s17.llorca.madrid.genmate_beta.ui.resin;

public class ResinTime {

    private int futureResinAmount;
    private String timeToGetResinAmount;
    private int minsTilResinAmount;

    public ResinTime(String timeToGetResinAmount, int futureResinAmount, int minsTilResinAmount) {
        this.futureResinAmount = futureResinAmount;
        this.timeToGetResinAmount = timeToGetResinAmount;
        this.minsTilResinAmount = minsTilResinAmount;
    }

    public int getMinsTilResinAmount() {
        return minsTilResinAmount;
    }

    public void setMinsTilResinAmount(int minsTilResinAmount) {
        this.minsTilResinAmount = minsTilResinAmount;
    }

    public String getTimeToGetResinAmount() {
        return timeToGetResinAmount;
    }

    public void setTimeToGetResinAmount(String timeToGetResinAmount) {
        this.timeToGetResinAmount = timeToGetResinAmount;
    }

    public int getFutureResinAmount() {
        return futureResinAmount;
    }

    public void setFutureResinAmount(int futureResinAmount) {
        this.futureResinAmount = futureResinAmount;
    }
}
