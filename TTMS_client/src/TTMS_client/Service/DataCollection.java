package Service;

import java.util.Map;

public class DataCollection {

    private DataDictSrv dataDictSrv = new DataDictSrv();
    public static Map<String,Integer> playTypeComboBox;
    public static Map<String,Integer> playLangComboBox;
    public static Map<Integer,String> playTypeTable;
    public static Map<Integer,String> playlangTable;

    public DataCollection(){
        playTypeComboBox = dataDictSrv.getDataDictByParentName("影片类型");
        playLangComboBox = dataDictSrv.getDataDictByParentName("语言类型");
    }
}
