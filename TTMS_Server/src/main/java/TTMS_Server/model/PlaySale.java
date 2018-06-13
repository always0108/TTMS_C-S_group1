package TTMS_Server.model;

import java.math.BigDecimal;

public class PlaySale {
    String play_name;
    BigDecimal playTicketAmount;

    public PlaySale() {}

    public PlaySale(String play_name, BigDecimal playTicketAmount) {
        this.play_name = play_name;
        this.playTicketAmount = playTicketAmount;
    }

    public String getPlay_name() {
        return play_name;
    }

    public void setPlay_name(String play_name) {
        this.play_name = play_name;
    }

    public BigDecimal getPlayTicketAmount() {
        return playTicketAmount;
    }

    public void setPlayTicketAmount(BigDecimal playTicketAmount) {
        this.playTicketAmount = playTicketAmount;
    }
}
