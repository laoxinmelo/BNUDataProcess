package master.operation.integrate.impl;

import master.dataobject.DataFile;
import master.operation.integrate.DataIntegrateTool;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/3/9.
 */
public class DataIntegrateToolImpl implements DataIntegrateTool {

    private static final String markIndex = "_";

    /**
     * 读取文件并保存
     * @param root
     */
    public void ReadFiles(File root) throws Exception {
        if(root == null) {
            throw new Exception("The file is null...");
        }

        File[] files = root.listFiles();
        String streamName = root.getName();

        for(File file : files) {
            getDataFile(streamName,file);
        }
    }

    /**
     * 将各仪器下所观测得到的各种类型的数据整合在一起
     *
     * @param file
     * @return
     * @throws Exception
     */
    private List<DataFile> getDataFile(String streamName,File file) throws Exception {
        if(file == null) {
            throw new Exception("The file is null...");
        }

        for(File subFile:file.listFiles()) {
            String mark = streamName + markIndex + file.getName() + markIndex + subFile.getName();
            System.out.println(mark);
        }

        return null;
    }

    public static void main(String[] args) throws Exception{

        File root = new File("E:\\Project\\JavaProject\\BNUDataProcess\\sample\\上游");
        new DataIntegrateToolImpl().ReadFiles(root);
    }

}
