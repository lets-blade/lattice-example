package io.github.biezhi.lattice.example.controller.sys;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.ui.RestResponse;
import io.github.biezhi.anima.page.Page;
import io.github.biezhi.lattice.example.controller.BaseController;
import io.github.biezhi.lattice.example.enums.UserStatus;
import io.github.biezhi.lattice.example.model.SysUser;
import io.github.biezhi.lattice.example.params.UpdatePwdParam;
import io.github.biezhi.lattice.example.params.UserParam;
import io.github.biezhi.lattice.example.service.UserService;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Path(value = "sys/user", restful = true)
public class UserController extends BaseController {

    @Inject
    private UserService userService;

    @GetRoute("list")
    public Page<SysUser> list(UserParam userParam) {
        return userService.findUsers(userParam);
    }

    @GetRoute("info")
    public RestResponse info() {
        return RestResponse.ok(this.loginUser());
    }

    @GetRoute("menus")
    public RestResponse menus() {
        return RestResponse.ok(userService.findUserMenus(this.loginUser().getUserId()));
    }

    @GetRoute("perms")
    public RestResponse perms() {
        return RestResponse.ok(userService.findUserPerms(this.loginUser().getUserId()));
    }

    @PostRoute("updatePwd")
    public RestResponse updatePwd(UpdatePwdParam updatePwdParam) {
        updatePwdParam.setUserId(this.loginUser().getUserId());
        updatePwdParam.setUsername(this.loginUser().getUsername());

        userService.updatePwd(updatePwdParam);
        return RestResponse.ok();
    }

    @PostRoute("save")
    public RestResponse save(SysUser sysUser) {
        return RestResponse.ok();
    }

    @PostRoute("update")
    public RestResponse update(SysUser sysUser) {
        return RestResponse.ok();
    }

    @PostRoute("remove")
    public RestResponse remove(Long[] ids) {
        return RestResponse.ok(userService.deleteUsers(ids));
    }

    @PostRoute("disable")
    public RestResponse disable(Long[] ids) {
        return RestResponse.ok(userService.updateStatus(UserStatus.DISABLE, ids));
    }

    @PostRoute("enable")
    public RestResponse enable(Long[] ids) {
        return RestResponse.ok(userService.updateStatus(UserStatus.NORMAL, ids));
    }

}
