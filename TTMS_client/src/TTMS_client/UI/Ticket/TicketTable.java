package UI.Ticket;

import Service.TicketSrv;
import UI.Layout.HomeUI;
import UI.Sell.ChoosePlay;
import UI.Sell.PlaySchedule;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.SeatAndTicket;
import node.FunButton;

import java.util.ArrayList;
import java.util.List;


public class TicketTable extends VBox {

        public static List<SeatAndTicket> tickets = new ArrayList();
        private TicketSrv ticketSrv = new TicketSrv();
        private HBox top;
        private Text center;
        private GridPane seatsTable;
        private HBox btGroup;

        public TicketTable() {}

        public TicketTable(Integer sched_id,Integer flag){
            this.setPadding(new Insets(20,20,20,20));
            this.setSpacing(20);
            this.setAlignment(Pos.TOP_CENTER);

            center = new Text("屏幕中央");
            center.setFont(Font.font(20));

            //座位图
            seatsTable = new GridPane();
            seatsTable.setAlignment(Pos.CENTER);
            seatsTable.setVgap(10);
            seatsTable.setHgap(10);

            tickets.clear();
            List<SeatAndTicket> seatAndTickets = ticketSrv.getTicketByScheduleId(sched_id);
            for (SeatAndTicket seatAndTicket : seatAndTickets){
                OneTicket oneTicket = new OneTicket(seatAndTicket,flag);
                seatsTable.add(oneTicket,oneTicket.getSeatAndTicket().getSeat_column(),
                        oneTicket.getSeatAndTicket().getSeat_row());
            }

            btGroup = new HBox();
            btGroup.setAlignment(Pos.CENTER_LEFT);
            btGroup.setSpacing(30);

            btGroup = new HBox();
            btGroup.setAlignment(Pos.CENTER);
            btGroup.setSpacing(50);
            FunButton btok = new FunButton("确认");
            FunButton btret = new FunButton("返回");

            btGroup.getChildren().addAll(btok,btret);

            btok.setOnAction(e->{
                HomeUI.setCenter(new ConfirmTicket(tickets,flag,sched_id));
            });

            btret.setOnAction(e->{
                HomeUI.setCenter(new ChoosePlay(flag));
            });

            this.getChildren().addAll(center,seatsTable,btGroup);
        }
    }
