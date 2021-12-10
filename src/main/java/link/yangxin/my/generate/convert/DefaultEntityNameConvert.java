package link.yangxin.my.generate.convert;

import cn.hutool.core.util.StrUtil;
import link.yangxin.my.generate.po.TableInfo;

/**
 * 默认的实体类名转换实现
 *
 * @author yangxin
 * @date 2021/12/9
 */
public class DefaultEntityNameConvert implements EntityNameConvert {

    private String prefix;

    private String suffix;

    public DefaultEntityNameConvert(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public DefaultEntityNameConvert() {
    }

    @Override
    public String convertEntityName(TableInfo tableInfo) {
        String s = StrUtil.toCamelCase(tableInfo.getTableName());
        if (prefix != null) {
            s = StrUtil.removePrefix(s, prefix);
        }
        if (suffix != null) {
            s = StrUtil.removeSuffix(s, suffix);
        }
        return StrUtil.upperFirst(s);
    }
}
