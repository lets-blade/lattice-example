package io.github.biezhi.lattice.example.controller.sys;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import io.github.biezhi.lattice.annotation.Users;
import io.github.biezhi.lattice.example.controller.BaseController;
import io.github.biezhi.lattice.example.enums.UserStatus;
import io.github.biezhi.lattice.example.model.SysMenu;
import io.github.biezhi.lattice.example.model.SysUser;
import io.github.biezhi.lattice.example.params.UpdatePwdParam;
import io.github.biezhi.lattice.example.service.UserService;

import java.util.List;
import java.util.Set;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Users
@Path("sys/user")
public class UserController extends BaseController {

    @Inject
    private UserService userService;

    @GetRoute("list")
    @JSON
    public RestResponse list(@Param String username) {
        List<SysUser> sysUsers = userService.findUsers(username);
        return RestResponse.ok(sysUsers);
    }

    @GetRoute("info")
    @JSON
    public RestResponse info() {
        return RestResponse.ok(this.loginUser());
    }

    @GetRoute("menus")
    @JSON
    public RestResponse menus() {
        Long          userId   = this.loginUser().getUserId();
        List<SysMenu> sysMenus = userService.findUserMenus(userId);
        return RestResponse.ok(sysMenus);
    }

    @GetRoute("perms")
    @JSON
    public RestResponse perms() {
        Long        userId   = this.loginUser().getUserId();
        Set<String> sysMenus = userService.findUserPerms(userId);
        return RestResponse.ok(sysMenus);
    }

    @PostRoute("updatePwd")
    @JSON
    public RestResponse updatePwd(UpdatePwdParam updatePwdParam) {
        updatePwdParam.setUserId(this.loginUser().getUserId());
        updatePwdParam.setUsername(this.loginUser().getUsername());

        userService.updatePwd(updatePwdParam);
        return RestResponse.ok();
    }

    @PostRoute("save")
    @JSON
    public RestResponse save(SysUser sysUser) {
        return RestResponse.ok();
    }

    @PostRoute("update")
    @JSON
    public RestResponse update(SysUser sysUser) {
        return RestResponse.ok();
    }

    @PostRoute("remove")
    @JSON
    public RestResponse remove(Long[] ids) {
        userService.deleteUsers(ids);
        return RestResponse.ok();
    }

    @PostRoute("disable")
    @JSON
    public RestResponse disable(Long[] ids) {
        userService.updateStatus(UserStatus.DISABLE, ids);
        return RestResponse.ok();
    }

    @PostRoute("enable")
    @JSON
    public RestResponse enable(Long[] ids) {
        userService.updateStatus(UserStatus.NORMAL, ids);
        return RestResponse.ok();
    }

}
