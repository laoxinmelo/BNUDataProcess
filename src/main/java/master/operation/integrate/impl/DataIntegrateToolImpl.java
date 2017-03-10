package master.operation.integrate.impl;

import master.dataobject.DataFile;
import master.operation.integrate.DataIntegrateTool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/3/9.
 */
public class DataIntegrateToolImpl implements DataIntegrateTool {

    private static final String markIndex = "_";  //mark命名的间隔符

    private static final String recordIndex = "\t";  //record数据的间隔符

    private static final String dateIndex = " ";  //日期与时间之间的间隔符

    private static final String tempSavePath = "./save/temp/";

    private static final String formatIndex = ".txt";

    /**
     * 读取文件并保存
     * @param root
     */
    public List<DataFile> readFiles(File root) throws Exception {
        if(root == null) {
            throw new Exception("The file is null...");
        }

        File[] files = root.listFiles();
        String streamName = root.getName();

        List<DataFile> dataFileList = new ArrayList<DataFile>();

        for(File file : files) {
            dataFileList.addAll(getDataFile(streamName,file));
        }

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

    /**
     * 将所有结果进行整合并保存至save/temp文件夹中
     * @param dataFile
     */
    public void integrateAndSave(DataFile dataFile) throws Exception{

        String mark = dataFile.getMark();
        File[] fileArray = dataFile.getFileArray();

        BufferedWriter bw = new BufferedWriter(new FileWriter(tempSavePath + mark + formatIndex));

        for(File file:fileArray) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String temp = br.readLine();
            while(temp != null) {
                if(isRecord(temp)) {
                    temp = makeDateStart(temp);
                    if(temp != null) {
                        bw.write(temp + "\r\n");
                    }
                }

                temp = br.readLine();
            }
        }

        bw.flush(); bw.close();

    }

    /**
     * 判断是否属于一条record
     *
     * @param temp
     * @return
     */
    private boolean isRecord(String temp) {
        return temp.contains(recordIndex);
    }

    /**
     * 判断record是否为日期开头
     * 如不是，则需进行相应处理
     *
     * @param temp
     * @return
     */
    private String makeDateStart(String temp) {
        String dateReg = "[0-9]{4}[-][0-9]{1,2}[-][0-9]{1,2}";
        String timeReg = "[0-9]{1,2}[:][0-9]{1,2}[:][0-9]{1,2}";

        Pattern datePattern = Pattern.compile(dateReg);
        Pattern timePattern = Pattern.compile(timeReg);

        String dateStr = temp.substring(0,temp.indexOf(recordIndex));
        String recordStr = temp.substring(temp.indexOf(recordIndex));

        Matcher dateMatcher = datePattern.matcher(dateStr);
        Matcher timeMatcher = timePattern.matcher(dateStr);

        if(dateMatcher.find() && timeMatcher.find()) {
            dateStr = dateMatcher.group(0) + dateIndex + timeMatcher.group(0);
            return dateStr + recordStr;
        }else  {
            return null;
        }
    }

    public static void main(String[] args) throws Exception{

        DataIntegrateTool dataIntegrateTool = new DataIntegrateToolImpl();

        File root = new File("E:\\Project\\JavaProject\\BNUDataProcess\\sample\\上游");
        List<DataFile> dataFileList = dataIntegrateTool.readFiles(root);
        for(DataFile dataFile:dataFileList) {
            dataIntegrateTool.integrateAndSave(dataFile);
        }

    }

}
