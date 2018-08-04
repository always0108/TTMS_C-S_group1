package TTMS_Server.model;

public class Page {
    //每页显示数量
    private int limit;
    //sql语句起始索引
    private int offset;
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public int getOffset() {
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }
}

