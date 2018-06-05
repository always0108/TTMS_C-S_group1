package UI.Studio;

import UI.HomeUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.Studio;

import java.util.List;

public class StudioTable extends GridPane {
    public StudioTable() {
    }

    public StudioTable(List<Studio> studios) {
        this.setPadding(new Insets(20,20,20,20));
        this.setHgap(30);
        this.setVgap(20);
        this.setAlignment(Pos.CENTER);

        if(studios == null){
            Label note = new Label("没有找到相应的演出厅");
            note.setStyle("-fx-font-size: 20px;-fx-text-alignment: center");
            this.add(note,0,0);
        }else {
            Label id = new Label("ID");
            Label name = new Label("名称");
            Label row = new Label("行数");
            Label col = new Label("列数");
            Label seatCount = new Label("座位总数");

            this.addRow(0,id,name,row,col,seatCount);

            for(int i = 0; i < studios.size(); i++){
                Studio studio = studios.get(i);
                Label studio_id = new Label(studio.getStudio_id().toString());
                Label studio_name = new Label(studio.getStudio_name());
                Label studio_rows = new Label(studio.getStudio_row_count().toString());
                Label studio_cols = new Label(studio.getStudio_col_count().toString());
                Label studio_seatCount = new Label(studio.getStudio_seat_count().toString());
                Button btDetail = new Button("详请");
                Button btModify = new Button("修改");
                Button btDelete = new Button("删除");

                btDetail.setOnAction(e->{
                    HomeUI.setCenter(new StudioDetail(studio));
                });

                btModify.setOnAction(e->{
                    HomeUI.setCenter(new StudioModify(studio));
                });

                btDelete.setOnAction(e->{
                    HomeUI.setCenter(new StudioDelete(studio));
                });

                this.addRow(i+1,studio_id,studio_name,studio_rows,studio_cols,
                        studio_seatCount,btDetail,btModify,btDelete);
            }
        }
    }
}