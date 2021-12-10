package link.yangxin.my.generate.template;

import freemarker.template.TemplateException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Map;

/**
 * @author yangxin
 * @date 2021/12/9
 */
public class FreemarkerUtils {

    /**
     * freemarker解析，返回解析后的文本
     *
     * @param path
     * @param param
     * @return
     */
    public static String freemarkerTemplateString(String path, Map<String, Object> param) {
        path = FreemarkerUtils.class.getClassLoader().getResource(path).getFile();
        try (Reader reader = new FileReader(path);
             StringWriter result = new StringWriter()) {
            freemarker.template.Template temp = new freemarker.template.Template(System.currentTimeMillis() + "", reader, null, null);
            temp.process(param, result);
            return result.toString();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
