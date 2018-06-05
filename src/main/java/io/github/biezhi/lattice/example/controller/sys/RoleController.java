package io.github.biezhi.lattice.example.controller.sys;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Param;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.ui.RestResponse;
import io.github.biezhi.anima.page.Page;
import io.github.biezhi.lattice.annotation.Users;
import io.github.biezhi.lattice.example.controller.BaseController;
import io.github.biezhi.lattice.example.model.SysRole;
import io.github.biezhi.lattice.example.params.RoleParam;
import io.github.biezhi.lattice.example.service.RoleService;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Users
@Path(value = "sys/role", restful = true)
public class RoleController extends BaseController {

    @Inject
    private RoleService roleService;

    @GetRoute("select")
    public RestResponse select(@Param String username) {
        return RestResponse.ok();
    }

    @GetRoute("info")
    public RestResponse info(@Param String username) {
        return RestResponse.ok();
    }

    @PostRoute("save")
    public RestResponse save(SysRole sysRole) {
        return RestResponse.ok();
    }

    @PostRoute("update")
    public RestResponse update(SysRole sysRole) {
        return RestResponse.ok();
    }

    @GetRoute("list")
    public Page<SysRole> list(RoleParam roleParam) {
        return roleService.findRoles(roleParam);
    }

    @PostRoute("remove")
    public RestResponse remove(Long[] ids) {
        return RestResponse.ok(roleService.deleteRoles(ids));
    }

}
