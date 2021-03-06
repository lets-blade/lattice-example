package io.github.biezhi.lattice.example.controller.sys;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.BodyParam;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.ui.RestResponse;
import io.github.biezhi.anima.Anima;
import io.github.biezhi.anima.page.Page;
import io.github.biezhi.lattice.annotation.Permissions;
import io.github.biezhi.lattice.example.annotation.SysLog;
import io.github.biezhi.lattice.example.controller.BaseController;
import io.github.biezhi.lattice.example.enums.UserStatus;
import io.github.biezhi.lattice.example.model.SysUser;
import io.github.biezhi.lattice.example.params.UpdatePwdParam;
import io.github.biezhi.lattice.example.params.UserParam;
import io.github.biezhi.lattice.example.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Path(value = "sys/user", restful = true)
public class UserController extends BaseController {

    @Inject
    private UserService userService;

    @Permissions("sys:user:list")
    @PostRoute("list")
    public Page<SysUser> list(UserParam userParam) {
        return userService.findUsers(userParam);
    }

    @GetRoute("info")
    public RestResponse info() {
        return RestResponse.ok(this.loginUser());
    }

    @GetRoute("menus")
    public RestResponse menus() {
        return RestResponse.ok(userService.listMenus(this.userId()));
    }

    @GetRoute("perms")
    public RestResponse perms() {
        return RestResponse.ok(userService.findUserPerms(this.userId()));
    }

    @SysLog("重置密码")
    @Permissions("sys:user:resetPassword")
    @PostRoute("updatePwd")
    public RestResponse updatePwd(UpdatePwdParam updatePwdParam) {
        updatePwdParam.setUserId(this.loginUser().getUserId());
        updatePwdParam.setUsername(this.loginUser().getUsername());

        userService.updatePwd(updatePwdParam);
        return RestResponse.ok();
    }

    @SysLog("新增用户")
    @Permissions("sys:user:save")
    @PostRoute("save")
    public RestResponse save(@BodyParam SysUser sysUser) {
        sysUser.setCreatedId(this.userId());
        sysUser.setCreatedTime(LocalDateTime.now());
        sysUser.setModifiedTime(LocalDateTime.now());
        return RestResponse.ok(sysUser.save());
    }

    @SysLog("修改用户")
    @Permissions("sys:user:edit")
    @PostRoute("update")
    public RestResponse update(@BodyParam SysUser sysUser) {
        sysUser.setModifiedTime(LocalDateTime.now());
        return RestResponse.ok(sysUser.update());
    }

    @SysLog("删除用户")
    @Permissions("sys:user:remove")
    @PostRoute("remove")
    public RestResponse remove(@BodyParam List<Long> ids) {
        return RestResponse.ok().peek(() -> Anima.deleteBatch(SysUser.class, ids));
    }

    @SysLog("禁用用户")
    @Permissions("sys:user:disable")
    @PostRoute("disable")
    public RestResponse disable(@BodyParam List<Long> ids) {
        return RestResponse.ok().peek(() -> userService.updateStatus(UserStatus.DISABLE, ids));
    }

    @SysLog("启用用户")
    @Permissions("sys:user:enable")
    @PostRoute("enable")
    public RestResponse enable(@BodyParam List<Long> ids) {
        return RestResponse.ok().peek(() -> userService.updateStatus(UserStatus.NORMAL, ids));
    }

}
