package UI.Analyze;

import Service.SaleSrv;
import UI.Layout.HomeUI;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import node.FunButton;


public class AnalysisMenu extends GridPane {

    private SaleSrv saleSrv = new SaleSrv();

    public AnalysisMenu(){
        this.setHgap(20);
        this.setVgap(20);
        this.setPadding(new Insets(25,25,25,25));

        FunButton empSaleAnalysis = new FunButton("员工销售分析");
        FunButton playSaleAnalysis = new FunButton("剧目票房分析");
        this.addRow(0,empSaleAnalysis,playSaleAnalysis);

        Label sale = new Label();
        Label label = new Label("输入员工id查询：");
        TextField textField = new TextField();
        FunButton funButton = new FunButton("查询");
        this.add(label,0,1);
        this.add(textField,1,1);
        this.add(funButton,2,1);
        this.add(sale,1,2);

        empSaleAnalysis.setOnAction(e->{
            HomeUI.setCenter(new EmployAnalysis(saleSrv.getEmployeeAnalysis()));
        });

        playSaleAnalysis.setOnAction(e->{
            HomeUI.setCenter(new PlayAnalysis(saleSrv.getPlayTicketAmount()));
        });

        funButton.setOnAction(e->{
            sale.setText(saleSrv.getEmployeeAnalysisByEmployeeId(Integer.valueOf(textField.getText())).toString());
        });


    }
}
