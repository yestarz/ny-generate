package ${serviceImplPackage};

import ${entityPackage}.${entityName};
import ${daoPackage}.${daoName};
import ${servicePackage}.${serviceName};
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * ${comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${serviceImplName} extends ServiceImpl<${daoName}, ${entityName}> implements ${serviceName} {

}
