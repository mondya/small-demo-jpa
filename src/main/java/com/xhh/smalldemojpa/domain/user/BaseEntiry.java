package com.xhh.smalldemojpa.domain.user;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseEntiry implements Serializable {
    
    @Column
    private LocalDateTime dataCreated;
    @Column
    private LocalDateTime lastUpdated;
    
    
}