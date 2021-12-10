package link.yangxin.my.generate.convert;

import link.yangxin.my.generate.po.TableInfo;

/**
 * 实体类名字转换
 *
 * @author yangxin
 * @date 2021/12/9
 */
public interface EntityNameConvert {

    /**
     * 转换实体类的名字
     *
     * @param tableInfo
     * @return
     */
    String convertEntityName(TableInfo tableInfo);

}
