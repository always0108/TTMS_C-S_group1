package TTMS_Server.model;

public class ResponseResult {
    private boolean flag;
    private Object content;

    public ResponseResult(){}

    //true表示正确响应，false表示错误响应
    public ResponseResult(boolean flag,Object content){
        this.flag = flag;
        this.content = content;
    }

    public boolean isFlag() {
        return flag;
    }

    public Object getContent() {
        return content;
    }
}
