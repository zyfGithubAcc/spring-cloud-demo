package com.fufu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fufu.domain.base.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "customer")
public class User extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "code")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "adddate")
    private Date addDate=new Date();
    @Column(name = "updatedate")
    private Date updateDate=new Date();
    @Column(name = "lastlogindate")
    private Date lastLogindate;
    @Column(name = "customer_online_state")
    private Integer customerOnlineState=0;
    @Column(name = "multilogin")
    private Integer multiLogin=1;
    @Column(name = "curlogin")
    private Integer curLogin=0;
    @Column(name = "customer_login_times")
    private Integer customerLoginTimes=0;
    @Column(name = "bind_dev_count")
    private Integer bindDevCount=0;
    @Column(name = "customer_nickname")
    private String customerNickname;
    @Column(name = "customer_ico_url")
    private String customerIcoUrl="defaultimg/defaultuser.png";
    @Column(name = "delflag")
    private Integer delflag=0;
    @Column(name = "login_cgw")
    private String loginCgw;
    @Column(name = "login_domain")
    private String loginDomain;
    @Column(name = "control_level")
    private Integer controlLevel;//控制级别
    @Column(name = "id_card_no")
    private String idCardNo;//身份证号码
    @Column(name = "position_number")
    private Long number;//位置标签
    @Column(name = "e_id")
    private Long eId; //使用单位ID

    @JsonIgnore
    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @BatchSize(size = 20)
    private Set<Role> roles = new HashSet<>();

    @Transient
    private Set<GrantedAuthority> authorities = new HashSet<>();


    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : this.roles) {
            for (Authority authority : role.getAuthorities()) {
                authorities.add(new SimpleGrantedAuthority(authority.getValue()));
            }
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
