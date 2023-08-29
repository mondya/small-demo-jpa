package com.xhh.smalldemojpa.service;

import com.xhh.smalldemojpa.dao.BaseRepository;
import com.xhh.smalldemojpa.domain.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 总结：
 *      在使用@Service注解并且不把类改成抽象类情况下报错，因为会被Spring容器扫描并注册为bean，然而泛型R注入时会找到多个bean，报错
 *      加上抽象abstract后相当于@Service注解失效了，不会被实例化成bean
 *      
 *      使用@Resource注解也会报错，默认按照名称注入，猜测是找到两个名称都叫 ? 的bean, 按照类型（原始类型）去匹配找到两个类型都是 R 的bean，注入报错
 *      而使用@Autowired注解，它有根据泛型参数匹配的逻辑，会依次去匹配（在spring4.0新特性支持使用@Autowired注入泛型）
 *     
 * @param <R>
 * @param <T>
 */
@Slf4j
public abstract class BaseServiceImpl<R extends BaseRepository<T>, T extends BaseEntity> implements BaseService<T>{
    
    @Autowired 
    private R repository;
    
    @Override
    public T findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<T> findByIdInList(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            log.info("[BaseServiceImpl] idList is null");
            return new ArrayList<T>();
        }
        return repository.findByIdList(idList);
    }

    @Override
    public void save(T entity) {
        repository.save(entity);
    }

    @Override
    public void update(T entity) {
        repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    public List<T> findAll() {
        return repository.findAll();
    }
    
    @Override
    public void saveAll(List<T> entityList) {
        repository.saveAll(entityList);
    }
}
