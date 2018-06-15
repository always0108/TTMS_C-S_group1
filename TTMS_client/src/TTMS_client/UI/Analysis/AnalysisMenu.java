package UI.Analysis;

import Service.SaleSrv;
import UI.Layout.HomeUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class AnalysisMenu extends GridPane {

    private SaleSrv saleSrv = new SaleSrv();

    public AnalysisMenu(){

        this.setAlignment(Pos.TOP_CENTER);
        this.setHgap(80);
        this.setVgap(20);
        this.setPadding(new Insets(200,30,25,20));


        Hyperlink playSaleAnalysis = new Hyperlink("剧目总票房",new ImageView("/image/Analysis/playAnalysis.png"));
        playSaleAnalysis.setStyle("-fx-border-style: hidden;-fx-content-display: top;-fx-font-size: 20px;-fx-text-fill: #000b00");

        Hyperlink emp_SaleAnalysis = new Hyperlink("员工总销售额",new ImageView("/image/Analysis/empAnalysis.png"));
        emp_SaleAnalysis.setStyle("-fx-border-style: hidden;-fx-content-display: top;-fx-font-size: 20px;-fx-text-fill: #000b00");

        Hyperlink play_SaleAnalysis = new Hyperlink("剧目排片比",new ImageView("/image/Analysis/playPercent.png"));
        play_SaleAnalysis.setStyle("-fx-border-style: hidden;-fx-content-display: top;-fx-font-size: 20px;-fx-text-fill: #000b00");

        this.addRow(0,playSaleAnalysis,emp_SaleAnalysis,play_SaleAnalysis);

        playSaleAnalysis.setOnAction(e->{
            HomeUI.setCenter(new PlayAnalysis(saleSrv.getPlayTicketAmount()));
        });

        emp_SaleAnalysis.setOnAction(e->{
            HomeUI.setCenter(new Employee_Analysis(saleSrv.getselectSaleAmountsByEmployeeName()));
        });

        play_SaleAnalysis.setOnAction(e->{
            new PlayPercentList();
        });
    }
}
