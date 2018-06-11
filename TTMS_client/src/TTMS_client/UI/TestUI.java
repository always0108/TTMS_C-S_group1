package UI;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Calendar;
import java.util.Date;


public class TestUI {

//    public static Pane getTestUI(){

//        HBox hBox = new HBox();
//        hBox.setSpacing(30);
//        hBox.setPadding(new Insets(20,20,20,20));
//        hBox.setAlignment(Pos.CENTER);

//        CalendarTimePicker timePicker = new CalendarTimePicker();
//        Button button = new Button("确认");
//        button.setOnAction(e->{
//            System.out.println(timePicker.getCalendar());
//        });
//
//        hBox.getChildren().addAll(timePicker,button);
//        GridPane gridPane = new GridPane();
//        int limit = 60;
//        TextField time = new TextField("0");
//        Button up = new Button("+");
//        Button down = new Button("-");
//        gridPane.add(time,0,0,1,2);
//        gridPane.addColumn(1,up,down);
//
//        up.setOnAction(e->{
//            int tmp = Integer.valueOf(time.getText());
//            if(tmp < limit){
//                time.setText(String.valueOf(tmp+1));
//            }
//        });
//
//        down.setOnAction(e->{
//            int tmp = Integer.valueOf(time.getText());
//            if(tmp > 0){
//                time.setText(String.valueOf(tmp-1));
//            }
//        });
//        return gridPane;
//    }
        public void test(){
            Calendar calendar = Calendar.getInstance();
            System.out.println(calendar.get(Calendar.YEAR));
            System.out.println(calendar.get(Calendar.MONTH)+1);
            System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        }
}
