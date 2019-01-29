package com.fufu.domain;

import com.fufu.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Authority extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String value;
}
