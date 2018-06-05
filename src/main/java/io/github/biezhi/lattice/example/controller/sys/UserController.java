package io.github.biezhi.lattice.example.controller.sys;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.ui.RestResponse;
import io.github.biezhi.lattice.annotation.Users;
import io.github.biezhi.lattice.example.controller.BaseController;
import io.github.biezhi.lattice.example.model.SysMenu;
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

}
