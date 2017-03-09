package master.operation.basic;

import java.util.Set;

/**
 * Created by Administrator on 2017/3/9.
 */
public interface PropertiesReader {

    /**
     * 获取properties文件中的各个属性及其对应属性值
     * @return
     */
    Set getKeySet();
}
