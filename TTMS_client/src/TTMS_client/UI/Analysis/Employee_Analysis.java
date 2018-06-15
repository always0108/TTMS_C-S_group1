package UI.Analysis;

import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import model.EmployeeSale;

import java.util.List;

public class Employee_Analysis extends StackPane {

    public Employee_Analysis(List<EmployeeSale> employeeSales){
        CategoryAxis x = new CategoryAxis();
        NumberAxis y = new NumberAxis();
        BarChart<String,Number> barChart = new BarChart<String, Number>(x,y);
        barChart.setTitle("员工销售额分析");
        x.setLabel("员工");
        y.setLabel("销售额");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("销售额");

        for(int i=0;i<employeeSales.size();i++){
            EmployeeSale employeeSale = employeeSales.get(i);
            series1.getData().add(new XYChart.Data(employeeSale.getEmp_name(),employeeSale.getSaleAmount()));
        }

        barChart.setMaxWidth(600);
        barChart.setMaxHeight(450);

        int sidePadding = 300 - employeeSales.size()*20;
        if(sidePadding < 0){
            sidePadding = 0;
        }
        this.setPadding(new Insets(20,sidePadding,20,sidePadding));

        barChart.getData().addAll(series1);
        this.getChildren().add(barChart);
    }
}
