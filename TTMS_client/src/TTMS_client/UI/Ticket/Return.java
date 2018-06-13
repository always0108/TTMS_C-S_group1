package UI.Ticket;

import UI.Layout.HomeUI;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Sale;
import node.FunButton;
import node.MessageBar;
import util.Httpclient;

public class Return extends VBox {

    public Return(Sale sale){
        this.setAlignment(Pos.TOP_LEFT);
        this.setSpacing(20);
        this.setPadding(new Insets(20,20,20,20));

        Text title = new Text("退款界面");
        title.setFont(Font.font(32));

        HBox center = new HBox();
        center.setPadding(new Insets(80,20,80,20));
        center.setAlignment(Pos.CENTER);
        Text note = new Text("共需退款"+sale.getSale_payment()+"元");
        note.setFont(Font.font(28));
        center.getChildren().add(note);

        HBox btGroup = new HBox();
        btGroup.setAlignment(Pos.CENTER);
        btGroup.setSpacing(50);
        FunButton btok = new FunButton("退款");
        FunButton btret = new FunButton("取消");

        btGroup.getChildren().addAll(btok,btret);

        btok.setOnAction(e->{
            btok.setDisable(true);
            //将订单状态改为已付款
            Task<JSONObject> task = new Task<JSONObject>() {
                @Override
                protected JSONObject call() throws Exception {
                    String url = "/sale/pay?id="+sale.getSale_ID()+"&flag=-1";
                    String res = Httpclient.get(url);
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
                        MessageBar.showMessageBar(jsonObject.get("content").toString());
                    }else{
                        MessageBar.showMessageBar(jsonObject.get("content").toString());
                    }
                    HomeUI.showNote();
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
            btret.setDisable(true);
            //将订单状态改为已付款
            Task<JSONObject> task = new Task<JSONObject>() {
                @Override
                protected JSONObject call() throws Exception {
                    String url = "/sale/cancel?id="+sale.getSale_ID()+"&flag=-1";
                    String res = Httpclient.get(url);
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
                        MessageBar.showMessageBar(jsonObject.get("content").toString());
                    }else{
                        MessageBar.showMessageBar(jsonObject.get("content").toString());
                    }
                    HomeUI.showNote();
                    btret.setDisable(false);
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

        this.getChildren().addAll(title,center,btGroup);
    }
}
