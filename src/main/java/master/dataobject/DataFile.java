package master.dataobject;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * 对于某种文件的类型进行了定义
 * 同时构造了该类型文件的对应列表
 */
public class DataFile {

    //文件所对应的后缀名称
    private String mark;

    //所有与该后缀名所对应的文件列表
    private List<File> fList;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public List<File> getfList() {
        return fList;
    }

    public void setfList(List<File> fList) {
        this.fList = fList;
    }

    public DataFile(String mark, List<File> fList) {
        this.mark = mark;
        this.fList = fList;
    }
}
