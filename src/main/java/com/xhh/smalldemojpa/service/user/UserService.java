package com.xhh.smalldemojpa.service.user;

import com.xhh.smalldemojpa.domain.user.User;

import java.util.Map;

public interface UserService {
    
    Map<String, Object> fetchAllUserByLimit(String searchValue, int p, int s);
    
    User fetchById(Long id);
}
