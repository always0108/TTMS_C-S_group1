package util;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.stream.FileImageInputStream;
import java.io.*;

public class ImageByte {

    public static String getFilePath(){
        Stage mainStage = null;
        FileChooser fileChooser = new FileChooser();//构建一个文件选择器实例
        fileChooser.setTitle("选择文件");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File selectedFile = fileChooser.showOpenDialog(mainStage);
        if(selectedFile == null){
            return null;
        }
        return selectedFile.getPath();
    }

    public static byte[] imageToBytes(String path){
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

    public static Image bytesToImage(byte[] data){
        if (data == null){
            System.out.println("null");
            return null;
        }
        try{
            ByteArrayInputStream input = new ByteArrayInputStream(data);
            Image image = new Image(input);
            input.close();
            return image;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
