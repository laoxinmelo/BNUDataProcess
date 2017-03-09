package master.operation.integrate.impl;

import master.dataobject.DataFile;
import master.operation.integrate.DataIntegrateTool;

import java.io.File;
import java.util.ArrayList;
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
    public List<DataFile> ReadFiles(File root) throws Exception {
        if(root == null) {
            throw new Exception("The file is null...");
        }

        File[] files = root.listFiles();
        String streamName = root.getName();

        List<DataFile> dataFileList = new ArrayList<DataFile>();

        for(File file : files) {
            dataFileList.addAll(getDataFile(streamName,file));
        }

        System.out.println(dataFileList.size());
        return dataFileList;
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

        List<DataFile> dataFileList = new ArrayList<DataFile>();
        for(File subFile:file.listFiles()) {
            String mark = streamName + markIndex + file.getName() + markIndex + subFile.getName();
            File[] markFileArray = subFile.listFiles();

            DataFile dataFile = new DataFile(mark,markFileArray);
            dataFileList.add(dataFile);
        }

        return dataFileList;
    }

    public static void main(String[] args) throws Exception{

        File root = new File("E:\\Project\\JavaProject\\BNUDataProcess\\sample\\上游");
        new DataIntegrateToolImpl().ReadFiles(root);
    }

}
