package model;

public class Data_dict {
    private Integer dict_id;

    private Integer dict_parent_id;

    private Integer dict_index;

    private String dict_name;

    private String dict_value;

    public Integer getDict_id() {
        return dict_id;
    }

    public void setDict_id(Integer dict_id) {
        this.dict_id = dict_id;
    }

    public Integer getDict_parent_id() {
        return dict_parent_id;
    }

    public void setDict_parent_id(Integer dict_parent_id) {
        this.dict_parent_id = dict_parent_id;
    }

    public Integer getDict_index() {
        return dict_index;
    }

    public void setDict_index(Integer dict_index) {
        this.dict_index = dict_index;
    }

    public String getDict_name() {
        return dict_name;
    }

    public void setDict_name(String dict_name) {
        this.dict_name = dict_name == null ? null : dict_name.trim();
    }

    public String getDict_value() {
        return dict_value;
    }

    public void setDict_value(String dict_value) {
        this.dict_value = dict_value == null ? null : dict_value.trim();
    }
}