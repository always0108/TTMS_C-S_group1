package UI.Analysis;

import Service.SaleSrv;
import UI.Layout.HomeUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import node.FunButton;

public class UnhandleList extends VBox{

    private SaleSrv saleSrv = new SaleSrv();
    private HBox top;
    private HBox bottom;
    private ScrollPane scrollPane;

    public UnhandleList(Integer emp_id){
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(60, 20, 20, 20));

        top = new HBox();
        top.setAlignment(Pos.TOP_LEFT);
        Label note = new Label("未完成订单");
        note.setStyle("-fx-font-size: 28px");
        top.getChildren().addAll(note);

        scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        //功能按钮框
        bottom = new HBox();
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(20,20,20,20));
        bottom.setSpacing(300);
        FunButton btret = new FunButton("返回");
        bottom.getChildren().addAll(btret);
        btret.setOnAction(e->{
            HomeUI.showNote();
        });
        scrollPane.setContent(new UnhandleTable(saleSrv.selectCancelSaleByEmpoyeeId(emp_id)));
        this.getChildren().addAll(top,scrollPane,bottom);
    }
}
