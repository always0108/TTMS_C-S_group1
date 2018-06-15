package UI.Analysis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;
import model.PlayPercent;

import java.util.List;


public class OneDayPlayPercentAnalysis extends StackPane{

    public OneDayPlayPercentAnalysis(List<PlayPercent> playPercents){
        PieChart chart = new PieChart();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for(int i=0;i<playPercents.size();i++){
            PlayPercent playPercent = playPercents.get(i);
            pieChartData.add(new PieChart.Data(playPercent.getPlay_name(),playPercent.getPlay_amount()));
        }
        chart.getData().addAll(pieChartData);
        chart.setStyle("-fx-font-size: 20");
        chart.setMaxWidth(500);
        chart.setMaxHeight(500);
        this.getChildren().addAll(chart);
    }

}