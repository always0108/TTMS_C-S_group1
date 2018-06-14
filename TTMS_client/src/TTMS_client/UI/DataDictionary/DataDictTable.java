package UI.DataDictionary;


import UI.Layout.HomeUI;
import UI.Play.PlayDelete;
import UI.Play.PlayDetail;
import UI.Play.PlayModify;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.Data_dict;
import model.Play;
import node.TableButton;

import java.util.List;

public class DataDictTable extends GridPane {

    public DataDictTable(){}

    public DataDictTable(List<Data_dict> data_dicts){
        this.setPadding(new Insets(20,20,20,20));
        this.setHgap(30);
        this.setVgap(20);
        this.setAlignment(Pos.CENTER);

        if(data_dicts == null || data_dicts.size() == 0){
            Label note = new Label("没有符合条件的结果");
            note.setStyle("-fx-font-size: 20px");
            this.add(note,0,0);
        }else {
            Label id = new Label("ID");
            Label parent = new Label("父ID");
            Label name = new Label("名称");
            this.addRow(0,id,parent,name);

            for (int i = 0; i < data_dicts.size(); i++) {
                Data_dict data = data_dicts.get(i);
                Label data_id = new Label(data.getDict_id().toString());
                Label data_parentId = new Label(data.getDict_parent_id().toString());
                Label data_name = new Label(data.getDict_name());

                TableButton btModify = new TableButton("修改");
                TableButton btDelete = new TableButton("删除");

                btModify.setOnAction(e->{
                    HomeUI.setCenter(new DataDictModify(data));
                });

                btDelete.setOnAction(e->{
                    HomeUI.setCenter(new DataDictDelete(data));
                });
                this.addRow(i+1,data_id,data_parentId,data_name,btModify,btDelete);
            }
        }
    }
}
