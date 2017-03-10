package master.operation.basic.impl;

import javax.swing.*;
import java.io.File;

/**
 * Created by Administrator on 2017/3/10.
 */
public class FileChooserImpl {

    /**
     * 选取文件夹
     * @return
     */
    public File[] chooseFile() {
        JFileChooser jfc = new JFileChooser();

        jfc.setMultiSelectionEnabled(true); //读取多文件
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  //只读取文件夹

        jfc.showOpenDialog(null);
        File[] files = jfc.getSelectedFiles();

        return files;
    }


    public static void main(String[] args) {
        new FileChooserImpl().chooseFile();
    }
}
