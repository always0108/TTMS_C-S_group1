package UI.Analysis;

import UI.Layout.HomeUI;
import UI.Ticket.Pay;
import UI.Ticket.Return;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.Sale;
import node.TableButton;

import java.util.List;

public class UnhandleTable extends GridPane {

    public UnhandleTable(List<Sale> sales){

        this.setPadding(new Insets(20,20,20,20));
        this.setHgap(30);
        this.setVgap(20);
        this.setAlignment(Pos.CENTER);

        if(sales == null || sales.size() == 0){
            Label note = new Label("没有取消的订单");
            note.setStyle("-fx-font-size: 20px");
            this.add(note,0,0);
        }else {
            Label sale_ID = new Label("ID");
            Label sale_time = new Label("时间");
            Label sale_type = new Label("类型");
            this.addRow(0, sale_ID,sale_time,sale_type);
            for (int i = 0; i < sales.size(); i++) {
                Sale sale = sales.get(i);
                Label Sale_sale_ID = new Label(sale.getSale_ID().toString());
                Label Sale_sale_time = new Label(sale.getSale_time().toString());
                Label Sale_sale_type = new Label(sale.getSale_type().toString());

                TableButton btDeal = new TableButton("处理");

                btDeal.setOnAction(e->{
                    if(sale.getSale_type() == Short.parseShort("1")){
                        HomeUI.setCenter(new Pay(sale));
                    }else{
                        HomeUI.setCenter(new Return(sale));
                    }
                });
                this.addRow(i+1,Sale_sale_ID,Sale_sale_time,Sale_sale_type,btDeal);
            }
        }
    }
}
