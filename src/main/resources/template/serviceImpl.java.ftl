package ${serviceImplPackage};

import ${entityPackage}.${entityName};
import ${daoPackage}.${daoName};
import ${servicePackage}.${serviceName};
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kangyouyun.yunyuhealth.common.exception.BizException;
import com.kangyouyun.yunyuhealth.common.operator.Operator;
import com.kangyouyun.yunyuhealth.common.utils.BeanUtils;

<#assign voName = entityName + 'VO'>

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(${entityName}CreateRequest request, Operator operator) {
        boolean create = request.isCreate();
        ${entityName} record ;
        if (create) {
          record = new ${entityName}();
          record.create(operator);
        } else {
          record = baseMapper.selectById(request.getId());
          if (record == null) {
              throw new BizException("要修改的记录不存在");
          }
          record.update(operator);
        }
        BeanUtils.copyProperties(request, record);
        if (create) {
          baseMapper.insert(record);
        } else {
          baseMapper.updateById(record);
        }
        return record.getId();
    }

    @Override
    public Page<${voName}> queryPage(${entityName}QueryRequest request) {
     return null;
    }

     @Override
     @Transactional(rollbackFor = Exception.class)
     public void delete(Long id, Operator operator) {
        ${entityName} record = baseMapper.selectById(id);
        if (record == null) {
           throw new BizException("要删除的记录不存在");
        }
        record.delete(operator);
        baseMapper.updateById(record);
     }

}
