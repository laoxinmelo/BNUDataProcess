package master.operation.basic.impl;

import master.operation.basic.PropertiesReader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/9.
 */
public class PropertiesReaderImpl implements PropertiesReader {

    //文件保存位置
    private static final String propertiesFilePath = "src/main/resources/record.properties";

    /**
     * 获取properties文件中的各个属性及其对应属性值
     * @return
     */
     public Set getKeySet() {
         Properties prop = new Properties();
         Set keyValue = null;

         try {
             InputStream in = new FileInputStream(propertiesFilePath);
             prop.load(in);
             keyValue = prop.keySet();
         }catch (Exception e) {
             e.printStackTrace();
         }finally {
             return keyValue;
         }
    }
}
