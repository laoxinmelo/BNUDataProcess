package entire;

import master.dataobject.DataFile;
import master.operation.add.DataAddTool;
import master.operation.add.impl.DataAddToolImpl;
import master.operation.choose.impl.FileChooserImpl;
import master.operation.choose.FileChooser;
import master.operation.integrate.DataIntegrateTool;
import master.operation.integrate.impl.DataIntegrateToolImpl;

import java.io.File;
import java.util.List;


/**
 * Created by Administrator on 2017/3/10.
 */
public class IntegrateAndInsert {

    private static final FileChooser fileChooser = new FileChooserImpl(); //文件选择器

    private static final DataAddTool dataAddTool = new DataAddToolImpl(); //用于数据插补

    private static final DataIntegrateTool dataIntegrateTool = new DataIntegrateToolImpl(); //用于数据整合


    public static void main(String[] args) throws Exception{

        File[] files = fileChooser.chooseFile();

        System.out.println("######################################");
        System.out.println("Start!");
        System.out.println("######################################");

        for(File file:files) {

            List<DataFile> dataFileList = dataIntegrateTool.readFiles(file); //读取该文件夹下的所有文件
            for(DataFile dataFile:dataFileList) {

                dataIntegrateTool.integrateAndSave(dataFile); //将文件进行整合
                System.out.println("integrate data : " + dataFile.getMark() + "...");

                dataAddTool.addData(dataFile.getMark());  //插补
                System.out.println("insert data : " + dataFile.getMark() + "...");

                System.out.println("______________________________");


            }

        }

        System.out.println("######################################");
        System.out.println("End!");
        System.out.println("######################################");


    }

}
