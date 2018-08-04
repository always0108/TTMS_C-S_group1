package TTMS_Server.model;

public class ResultPagination {
    private int total;
    private Object rows;
    private Object content;

    public ResultPagination() {}

    public ResultPagination(int total, Object rows, Object content) {
        this.total = total;
        this.rows = rows;
        this.content = content;
    }

    public ResultPagination(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
