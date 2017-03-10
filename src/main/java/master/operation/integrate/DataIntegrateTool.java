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
    List<DataFile> readFiles(File root) throws Exception;

    /**
     * 将所有结果进行整合并保存至save/temp文件夹中
     *
     * @param dataFile
     * @throws Exception
     */
    void integrateAndSave(DataFile dataFile) throws Exception;

}
