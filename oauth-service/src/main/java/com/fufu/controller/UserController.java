package com.fufu.controller;

import com.fufu.domain.User;
import com.fufu.repository.AuthorityRepository;
import com.fufu.repository.RoleRepository;
import com.fufu.service.UserService;
import com.fufu.util.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
public class UserController {

    @Autowired private UserService userService;
    @Autowired private RoleRepository roleRepository;
    @Autowired private AuthorityRepository authorityRepository;

    @ApiOperation(value="获取当前用户信息", notes="获取当前用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "access_token", required = true, dataType = "string", paramType = "query")
    })
    @GetMapping("/getCurrentCustomer")
    public Result getCurrentCustomer(Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        return Result.ok("获取当前用户信息成功").put("data",user);
    }

    @ApiOperation(value="添加用户", notes="添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "access_token", required = true, dataType = "string", paramType = "query")
    })
    @PostMapping("/addCustomer")
    public Result addCustomer(String username, String password,Long eId) {
        if(username == null)
            return Result.error("用户名不能为空");
        if(password == null)
            return Result.error("用户密码不能为空");
        if(eId == null)
            return Result.error("使用单位ID不能为空");

//        //权限
//        Authority authority = new Authority();
//        authority.setName("查询");
//        authority.setValue("query");
//        authorityRepository.save(authority);
//
//        Role admin = new Role();
//        admin.setName("管理员");
//        admin.setValue("ROLE_ADMIN");
//        admin.setAuthorities(Sets.newHashSet(authority));
//        admin = roleRepository.save(admin);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEId(eId);
//        user.setRoles(Sets.newHashSet(admin));
        User hasUser = userService.findUserByUsername(user.getUsername());
        if(hasUser == null){
            userService.createUser(user);
            return Result.ok("添加用戶成功").put("data",user);
        }else {
            return Result.error("用户已经存在");
        }
    }

    @ApiOperation(value="重置用户密码", notes="重置用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "access_token", required = true, dataType = "string", paramType = "query")
    })
    @PostMapping("/resetCustomerPassword")
    public Result resetCustomerPassword(String username) {
        if(username == null)
            return Result.error("用户名不能为空");

        User hasUser = userService.findUserByUsername(username);
        if(hasUser != null){
            hasUser.setPassword("123456");
            userService.saveUser(hasUser);
            return Result.ok("重置用户密码成功");
        }else {
            return Result.error("重置用户密码失败");
        }
    }

    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }
}
