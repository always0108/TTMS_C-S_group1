package UI.Analysis;

import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import model.PlaySale;

import java.util.List;

public class PlayAnalysis extends StackPane {

        public PlayAnalysis(List<PlaySale> playSales){
            CategoryAxis x = new CategoryAxis();
            NumberAxis y = new NumberAxis();
            BarChart<String,Number> barChart = new BarChart<String,Number>(x,y);
            barChart.setTitle("剧目票房分析");
            x.setLabel("剧目");
            y.setLabel("票房");

            XYChart.Series series1 = new XYChart.Series();
            series1.setName("票房");

            for(int i=0;i<playSales.size();i++){
                PlaySale playSale = playSales.get(i);
                series1.getData().add(new XYChart.Data(playSale.getPlay_name(),playSale.getPlayTicketAmount()));
            }

            barChart.setMaxWidth(600);
            barChart.setMaxHeight(450);

            int sidePadding = 300 - playSales.size()*20;
            if(sidePadding < 0){
                sidePadding = 0;
            }
            this.setPadding(new Insets(20,sidePadding,20,sidePadding));

            barChart.getData().addAll(series1);
            this.getChildren().add(barChart);
        }
}
