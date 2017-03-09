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
    private File[] fileArray;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public File[] getFileArray() {
        return fileArray;
    }

    public void setFileArray(File[] fileArray) {
        this.fileArray = fileArray;
    }

    public DataFile(String mark, File[] fileArray) {
        this.mark = mark;
        this.fileArray = fileArray;
    }
}
