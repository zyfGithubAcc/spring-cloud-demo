package com.fufu.domain.base;

import lombok.Data;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
}
