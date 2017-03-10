package master.operation.basic.impl;

import master.operation.basic.PathChooser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/10.
 */
public class PathChooserImpl implements PathChooser {

    private static final String propertiesPath = "src\\main\\resources\\path.properties";

    /**
     * 获取各类path的值
     *
     * @return
     */
    public Set getPathProperties() throws Exception {
        Properties properties = new Properties();
        Set pathSet = null;

        InputStream is = new BufferedInputStream(new FileInputStream(propertiesPath));
        properties.load(is);
        pathSet = properties.keySet();
        for(Object key:pathSet) {
            System.out.println(key);
        }

        return pathSet;

    }

//    public static void main(String[] args) throws Exception{
//        new PathChooserImpl().getPathProperties();
//    }
}
