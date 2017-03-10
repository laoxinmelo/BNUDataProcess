package master.operation.basic;

import java.io.File;

/**
 * Created by Administrator on 2017/3/9.
 */
public interface FileChooser {

    /**
     * 选取文件夹
     * @return
     */
    File[] chooseFile();
}
