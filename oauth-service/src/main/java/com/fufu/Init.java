package com.fufu;

import com.google.common.collect.Sets;
import com.fufu.domain.Authority;
import com.fufu.domain.Role;
import com.fufu.domain.User;
import com.fufu.repository.AuthorityRepository;
import com.fufu.repository.RoleRepository;
import com.fufu.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Init implements CommandLineRunner {
    private UserService userService;
    private AuthorityRepository authorityRepository;
    private RoleRepository roleRepository;

    @Override
    public void run(String... strings) throws Exception {

        //权限
        Authority authority = new Authority();
        authority.setName("查询");
        authority.setValue("query");

        //角色
        Role admin = new Role();
        admin.setName("管理员");
        admin.setValue("ROLE_ADMIN");
        admin.setAuthorities(Sets.newHashSet(authority));
        Role hasAdmin = roleRepository.findByName(admin.getName());
        if(hasAdmin == null){
            authorityRepository.save(authority);
            roleRepository.save(admin);
        }

//        Role role = new Role();
//        role.setName("普通用户");
//        role.setValue("ROLE_USER");
//        roleRepository.save(role);


        //用户
        User user = new User();
        user.setUsername("admin");
        user.setPassword("21232f297a57a5a743894a0e4a801fc3");
        user.setRoles(Sets.newHashSet(admin));
        User hasUser = userService.findUserByUsername(user.getUsername());
        if(hasUser == null)
            userService.createUser(user);

//        User wl = new User();
//        wl.setUsername("wl");
//        wl.setPassword("wl");
//        wl.setRoles(Sets.newHashSet(roleRepository.findOne(2L)));
//        userService.createUser(wl);

    }
}
