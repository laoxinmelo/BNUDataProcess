package master.operation.basic;

import java.util.Set;

/**
 * Created by Administrator on 2017/3/10.
 */
public interface PathChooser {

    /**
     * 获取各类path的值
     *
     * @return
     */
    Set getPathProperties() throws Exception;
}
