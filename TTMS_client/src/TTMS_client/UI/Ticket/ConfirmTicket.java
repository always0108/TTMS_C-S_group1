package UI.Ticket;

import UI.Layout.HomeUI;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Sale;
import model.SeatAndTicket;
import node.FunButton;
import node.MessageBar;
import util.DateFormat;
import util.Httpclient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfirmTicket extends VBox {

    public ConfirmTicket(List<SeatAndTicket> seatAndTickets) {
        this.setAlignment(Pos.TOP_LEFT);
        this.setSpacing(40);
        this.setPadding(new Insets(20,20,20,20));
        Text title = new Text("请确认订单");
        title.setFont(Font.font(28));

        BigDecimal total = new BigDecimal("0");

        HBox tickets = new HBox();
        tickets.setAlignment(Pos.CENTER);
        tickets.setSpacing(20);
        for(SeatAndTicket seatAndTicket : seatAndTickets){
            VBox oneTicket = new VBox();
            oneTicket.setStyle("-fx-background-color: gray");
            oneTicket.setAlignment(Pos.CENTER);
            oneTicket.setSpacing(5);
            Label pos = new Label(seatAndTicket.getSeat_row()+"排"+
                                        seatAndTicket.getSeat_column()+"列");
            Label price = new Label(seatAndTicket.getTicket_price().toString()+"元");
            total = total.add(seatAndTicket.getTicket_price());
            oneTicket.getChildren().addAll(pos,price);
            tickets.getChildren().add(oneTicket);
        }

        HBox priceArea = new HBox();
        priceArea.setAlignment(Pos.CENTER_RIGHT);
        priceArea.setSpacing(30);
        priceArea.setPadding(new Insets(20,40,20,20));
        Text totalprice = new Text("共计:");
        totalprice.setFont(Font.font(20));
        Text totalpriceValue = new Text(total.toString());
        totalpriceValue.setFont(Font.font(24));
        priceArea.getChildren().addAll(totalprice,totalpriceValue);

        HBox btGroup = new HBox();
        btGroup.setAlignment(Pos.CENTER);
        btGroup.setSpacing(50);
        FunButton btok = new FunButton("确认");
        FunButton btret = new FunButton("返回");

        btGroup.getChildren().addAll(btok,btret);

        btok.setOnAction(e->{
            //生成订单
            btok.setDisable(true);
            Task<JSONObject> task = new Task<JSONObject>() {
                @Override
                protected JSONObject call() throws Exception {
                    String url = "/sale/create";
                    Map<String, Object> data = new HashMap<>();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("tickets",seatAndTickets);
                    String jsonStr = JSON.toJSONString(jsonObject);
                    data.put("json",jsonStr);
                    String res = Httpclient.post(url, data);
                    return JSON.parseObject(res);
                }

                @Override
                protected void running() {

                }

                @Override
                protected void succeeded() {
                    super.succeeded();
                    JSONObject jsonObject = getValue();
                    if(jsonObject.get("flag").equals(true)){
                        MessageBar.showMessageBar("订单成功生成");
                        Sale sale = jsonObject.getObject("content",Sale.class);
                        HomeUI.setCenter(new Pay(sale));
                    }else{
                        MessageBar.showMessageBar(jsonObject.get("content").toString());
                    }
                    btok.setDisable(false);
                    updateMessage("Done!");
                }

                @Override
                protected void cancelled() {
                    super.cancelled();
                    updateMessage("Cancelled!");
                }

                @Override
                protected void failed() {
                    super.failed();
                    updateMessage("Failed!");
                }
            };
            Thread thread = new Thread(task);
            thread.start();
        });

        btret.setOnAction(e->{
            //取消交易
        });

        this.getChildren().addAll(title,tickets,priceArea,btGroup);

    }

}
