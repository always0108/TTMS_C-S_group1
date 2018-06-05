package UI;


import UI.Seat.SeatTable;
import UI.Studio.StudioAdd;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Seat;
import node.FileChoose;
import org.apache.http.impl.client.HttpClients;
import util.Httpclient;

import javax.imageio.stream.FileImageInputStream;
import java.io.*;
import java.util.List;

public class TestUI {

    public static Pane getTestUI(){
//        List<Seat> seats = null;
//        String url = "/seat/getStudioSeats?id=3";
//        String res = Httpclient.get(url);
//      JSONObject jsonObject = JSON.parseObject(res);
//        if(jsonObject.get("flag").equals(true)) {
//            seats = JSONArray.parseArray(jsonObject.getString("content"), Seat.class);
//        }else{
//            System.out.println("网络连接失败");
//        }
//        return new SeatTable(seats);
        HBox hBox = new HBox();
        hBox.setSpacing(30);
        hBox.setPadding(new Insets(20,20,20,20));
        hBox.setAlignment(Pos.CENTER);
        Button butest = new Button("选择封面");
        TextField textField = new TextField();
        hBox.getChildren().addAll(textField,butest);
        butest.setOnAction(e->{

//            try {
                String path = FileChoose.getFilePath();
                if (path != null) {
//                    File file = new File(path);
//                    InputStream inputStream = new FileInputStream(file);
//                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                    byte[] buf = new byte[1024];
//                    int numBytesRead = 0;
//                    while ((numBytesRead = inputStream.read(buf)) != -1) {
//                        outputStream.write(buf, 0, numBytesRead);
//                    }
//                    byte[] data = outputStream.toByteArray();
//                    outputStream.close();
//                    inputStream.close();
//
//                    ByteArrayInputStream input = new ByteArrayInputStream(data);
//                    ImageView imageView = new ImageView();
//                    Image image = new Image(input);
//                    imageView.setImage(image);
//                    hBox.getChildren().add(imageView);
//                    input.close();
                    textField.setText(path);
                }

//            }catch (FileNotFoundException ex){
//                    ex.printStackTrace();
//            }catch (IOException ex){
//                ex.printStackTrace();
//            }

//                FileImageInputStream input = new FileImageInputStream(file);
//                ByteArrayOutputStream output = new ByteArrayOutputStream();
//                byte[] buf = new byte[1024];
//                int numBytesRead = 0;
//                while ((numBytesRead = input.read(buf)) != -1) {
//                    output.write(buf, 0, numBytesRead);
//                }
//                System.out.println(output.toByteArray());
//                output.close();
//                input.close();

        });
        return hBox;
    }

}
