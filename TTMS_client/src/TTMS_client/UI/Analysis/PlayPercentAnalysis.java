package UI.Analysis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;
import model.PlayPercent;

import java.util.List;


public class PlayPercentAnalysis extends StackPane{

    public PlayPercentAnalysis(List<PlayPercent> playPercents){
        PieChart chart = new PieChart();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for(int i=0;i<playPercents.size();i++){
            PlayPercent playPercent = playPercents.get(i);
            System.out.println(playPercent.getPlay_name()+"-->"+playPercent.getPlay_amount());
            pieChartData.add(new PieChart.Data(playPercent.getPlay_name(),playPercent.getPlay_amount()));
        }
        chart.getData().addAll(pieChartData);

        chart.setTitle("今日电影上映占比");
        chart.setStyle("-fx-font-size: 20");
        chart.setMaxWidth(500);
        chart.setMaxHeight(500);

        this.getChildren().add(chart);
    }
}