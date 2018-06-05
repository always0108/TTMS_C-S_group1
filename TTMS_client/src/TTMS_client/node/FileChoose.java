package node;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileChoose {

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
}
