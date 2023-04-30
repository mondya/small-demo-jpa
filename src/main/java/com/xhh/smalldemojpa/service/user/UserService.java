package com.xhh.smalldemojpa.service.user;

import java.util.Map;

public interface UserService {
    
    Map<String, Object> fetchAllUserByLimit(String searchValue, int p, int s);
}
