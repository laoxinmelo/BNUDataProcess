package master.operation.integrate;

import master.dataobject.DataFile;

import java.io.File;
import java.util.List;

/**
 * 数据整合工具
 * Created by Administrator on 2017/3/9.
 */
public interface DataIntegrateTool {

    /**
     * 读取文件并保存
     * @param root
     */
    void ReadFiles(File root) throws Exception;

}
