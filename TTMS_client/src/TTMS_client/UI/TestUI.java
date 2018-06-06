package UI;


import UI.Seat.SeatTable;
import UI.Studio.StudioAdd;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import util.ImageByte;


public class TestUI {

    public static Pane getTestUI(){

        HBox hBox = new HBox();
        hBox.setSpacing(30);
        hBox.setPadding(new Insets(20,20,20,20));
        hBox.setAlignment(Pos.CENTER);

        String path = ImageByte.getFilePath();
        byte[] data = ImageByte.imageToBytes(path);
        System.out.println(data);

        ImageView imageView = new ImageView(ImageByte.bytesToImage(data));
        hBox.getChildren().add(imageView);

        return hBox;
    }

}
