package TTMS_Server.model;

public class Mylog {
    private Integer log_id;

    private String log_time;

    private String log_content;

    public Integer getLog_id() {
        return log_id;
    }

    public void setLog_id(Integer log_id) {
        this.log_id = log_id;
    }

    public String getLog_time() {
        return log_time;
    }

    public void setLog_time(String log_time) {
        this.log_time = log_time == null ? null : log_time.trim();
    }

    public String getLog_content() {
        return log_content;
    }

    public void setLog_content(String log_content) {
        this.log_content = log_content == null ? null : log_content.trim();
    }
}