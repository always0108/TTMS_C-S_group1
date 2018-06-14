package TTMS_Server.model;

import java.math.BigDecimal;

public class PlayPercent {

    private String play_name;
    private Double play_amount;

    public PlayPercent() {
    }

    public PlayPercent(String play_name, Double play_amount) {
        this.play_name = play_name;
        this.play_amount = play_amount;
    }

    public String getPlay_name() {
        return play_name;
    }

    public void setPlay_name(String play_name) {
        this.play_name = play_name;
    }

    public Double getPlay_amount() {
        return play_amount;
    }

    public void setPlay_amount(Double play_amount) {
        this.play_amount = play_amount;
    }

}
