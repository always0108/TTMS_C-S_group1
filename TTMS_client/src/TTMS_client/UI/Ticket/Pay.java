package UI.Ticket;

import UI.Layout.HomeUI;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Sale;
import node.FunButton;
import node.MessageBar;
import util.Httpclient;

public class Pay extends VBox {
    public Pay(Sale sale){
        this.setAlignment(Pos.TOP_LEFT);
        this.setSpacing(20);
        this.setPadding(new Insets(20,20,20,20));

        Text title = new Text("请支付");
        title.setFont(Font.font(28));

        GridPane center = new GridPane();
        center.setAlignment(Pos.CENTER);
        center.setHgap(50);
        center.setVgap(30);
        center.setPadding(new Insets(20,20,20,20));
        Text note = new Text("您共消费"+sale.getSale_payment()+"元,请扫码支付!");
        note.setFont(Font.font(20));
        Text wechatnote = new Text("微信支付");
        Text alipaynote = new Text("支付宝");
        wechatnote.setFont(Font.font(20));
        alipaynote.setFont(Font.font(20));
        ImageView wechat = new ImageView(new Image("/image/Pay/wechat.jpg"));
        ImageView alipay = new ImageView(new Image("/image/Pay/alipay.jpg"));
        wechat.setFitHeight(300);
        wechat.setFitWidth(300);
        alipay.setFitWidth(300);
        alipay.setFitHeight(300);
        center.add(note,0,0,2,1);
        center.addRow(1,wechat,alipay);
        center.addRow(2,wechatnote,alipaynote);

        HBox btGroup = new HBox();
        btGroup.setAlignment(Pos.CENTER);
        btGroup.setSpacing(50);
        FunButton btok = new FunButton("付款完成");
        FunButton btret = new FunButton("取消订单");

        btGroup.getChildren().addAll(btok,btret);

        btok.setOnAction(e->{
            btok.setDisable(true);
            //将订单状态改为已付款
            Task<JSONObject> task = new Task<JSONObject>() {
                @Override
                protected JSONObject call() throws Exception {
                    String url = "/sale/pay?id="+sale.getSale_ID()+"&flag=1";
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
                    String url = "/sale/cancel?id="+sale.getSale_ID()+"&flag=1";
                    String res = Httpclient.get(url);
                    System.out.println(res);
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
