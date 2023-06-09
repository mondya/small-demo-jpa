package com.xhh.smalldemojpa.service.user;

import com.xhh.smalldemojpa.dao.UserRepository;
import com.xhh.smalldemojpa.domain.user.User;
import com.xhh.smalldemojpa.service.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("user")
public class UserServiceImpl extends BaseServiceImpl<UserRepository, User> implements UserService{
    
    @Resource
    private UserRepository userRepository;
    
    @Override
    public Map<String, Object> fetchAllUserByLimit(String searchValue, int p, int s) {
        if (StringUtils.isNotBlank(searchValue)) {
            searchValue = "%\\" + searchValue + "%";
        }
        Page<User> users = userRepository.fetchAllUserBySearch(searchValue, PageRequest.of((p - 1) * s, s, Sort.Direction.DESC, "id"));
        List<User> collect = users.get().collect(Collectors.toList());
        long totalElements = users.getTotalElements();
        
        Map<String, Object> map = new HashMap<>();
        map.put("list", collect);
        map.put("total", totalElements);
        
        return map;
        
    }

    @Override
    public User fetchById(Long id) {
        return findById(id);
    }


}
