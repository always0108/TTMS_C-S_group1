package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import node.FunButton;
import node.NavigationButton;

public class TestUI {
    public static Pane getTestUI(){
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20,20,20,20));

        FunButton funButton = new FunButton("功能按钮");
        NavigationButton navigationButton = new NavigationButton("导航按钮");
        TextField textField = new TextField();

        gridPane.addColumn(1,funButton,navigationButton,textField);

        return gridPane;
    }

}
