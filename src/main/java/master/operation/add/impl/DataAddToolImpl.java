package master.operation.add.impl;

import master.dataobject.DataTime;
import master.operation.add.DataAddTool;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/9.
 */
public class DataAddToolImpl implements DataAddTool {

    private static final String recordIndex = "\t";  //record数据的间隔符

    private static final String dateIndex = " "; //日期与时间之间的间隔符

    private static final int basicYear = 1900;

    private static final String resultSavePath = "./save/result/";

    private static final String tempSavePath = "./save/temp/";

    private static final String formatIndex = ".txt";

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm"); //所设置时间格式

    /**
     * 对数据进行插补
     *
     * @param mark
     * @throws Exception
     */
    public void addData(String mark) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(tempSavePath + mark + formatIndex))));
        String temp = br.readLine();
        String nextTemp = br.readLine();

        BufferedWriter bw = new BufferedWriter(new FileWriter(resultSavePath + mark + formatIndex));

        while(nextTemp != null) {

            DataTime dataTime = transferStr(temp);
            DataTime nextDataTime = transferStr(nextTemp);

//            System.out.println(temp);
            bw.write(temp + "\r\n");
            List<String> addDateList = addDataBetweenTwoDate(dataTime,nextDataTime);
            for(String addDate:addDateList) {
//                System.out.println(addDate);
                bw.write(addDate + "\r\n");
            }

            temp = nextTemp;
            nextTemp = br.readLine();
        }

//        System.out.println(temp);
        bw.write(temp + "\r\n");

        bw.flush();  bw.close();
    }


    /**
     * 在两个时间值之间进行插补
     * @param dataTime
     * @param nextDataTime
     * @return
     */
    private List<String> addDataBetweenTwoDate(DataTime dataTime,DataTime nextDataTime) {

        Date date = dataTime.getDate();
        Date nextDate = nextDataTime.getDate();

        List<String> addDateList = new ArrayList<String>();
        int count = new Long((nextDate.getTime() - date.getTime())/(60*1000)).intValue();
        for(int i=1;i<count;i++) {
            long tempTime = date.getTime() + i*60*1000;
            Date tempDate = new Date(tempTime);

            String addDateFormat = format.format(tempDate);
            addDateList.add(addDateFormat);
        }

        return addDateList;
    }

    /**
     * 将String转换为DataTime格式
     *
     * @param temp
     * @return
     * @throws Exception
     */
    private DataTime transferStr(String temp) throws Exception {

        String dateStr = temp.substring(0,temp.indexOf(recordIndex));
        String recordStr = temp.substring(temp.indexOf(recordIndex)+1);

        String[] dateArray = dateStr.replaceAll("[^0-9]",dateIndex).split(dateIndex);
        if(dateArray.length >= 5) {
            DataTime dataTime = new DataTime(Integer.valueOf(dateArray[0])-basicYear,Integer.valueOf(dateArray[1]),Integer.valueOf(dateArray[2]),Integer.valueOf(dateArray[3]),
                    Integer.valueOf(dateArray[4]),recordStr);
            return dataTime;

        }else {
            return null;
        }
    }

//    public static void main(String[] args) throws Exception{
//
//        DataAddTool dataAddTool = new DataAddToolImpl();
//
//        File file = new File("E:\\Project\\JavaProject\\BNUDataProcess\\save\\temp\\上游_BLS450_NQ_mnd.txt");
//
//        dataAddTool.addData(file);
//
//    }
}
