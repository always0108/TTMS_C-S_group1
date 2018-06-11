package Service;

import java.util.HashMap;
import java.util.Map;

public class DataCollection {

    private static DataDictSrv dataDictSrv = new DataDictSrv();

    public static Map<String,Integer> playTypeComboBox;
    public static Map<String,Integer> playLangComboBox;
    public static Map<String,Integer> dataDictComboBox;
    public static Map<Integer,String> playTypeTable = new HashMap<>();
    public static Map<Integer,String> playlangTable = new HashMap<>();;

    public DataCollection(){
        updateDataDict();
    }

    public static void updateDataDict(){
        playTypeComboBox = dataDictSrv.getDataDictByParentName("影片类型");
        playLangComboBox = dataDictSrv.getDataDictByParentName("语言类型");
        dataDictComboBox = dataDictSrv.getDataDictByParentName("根");

        for(Map.Entry<String,Integer> entry:playLangComboBox.entrySet()){
            playlangTable.put(entry.getValue(),entry.getKey());
        }

        for(Map.Entry<String,Integer> entry:playTypeComboBox.entrySet()){
            playTypeTable.put(entry.getValue(),entry.getKey());
        }
    }
}
