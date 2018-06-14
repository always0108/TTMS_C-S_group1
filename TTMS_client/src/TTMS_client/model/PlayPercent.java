package model;

import java.math.BigDecimal;

public class PlayPercent {

    private String play_name;
    private double play_amount;

    public PlayPercent() {
    }

    public PlayPercent(String play_name, double play_amount) {
        this.play_name = play_name;
        this.play_amount = play_amount;
    }

    public String getPlay_name() {
        return play_name;
    }

    public void setPlay_name(String play_name) {
        this.play_name = play_name;
    }

    public double getPlay_amount() {
        return play_amount;
    }

    public void setPlay_amount(double play_amount) {
        this.play_amount = play_amount;
    }

}
