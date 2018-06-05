package io.github.biezhi.lattice.example.controller.sys;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import io.github.biezhi.lattice.annotation.Users;
import io.github.biezhi.lattice.example.controller.BaseController;
import io.github.biezhi.lattice.example.model.SysRole;
import io.github.biezhi.lattice.example.service.RoleService;

import java.util.List;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Users
@Path("sys/role")
public class RoleController extends BaseController {

    @Inject
    private RoleService roleService;

    @GetRoute("select")
    @JSON
    public RestResponse select(@Param String username) {
        return RestResponse.ok();
    }

    @GetRoute("info")
    @JSON
    public RestResponse info(@Param String username) {
        return RestResponse.ok();
    }

    @PostRoute("save")
    @JSON
    public RestResponse save(SysRole sysRole) {

        return RestResponse.ok();
    }

    @PostRoute("update")
    @JSON
    public RestResponse update(SysRole sysRole) {

        return RestResponse.ok();
    }

    @GetRoute("list")
    @JSON
    public RestResponse list(@Param String roleName) {
        List<SysRole> sysRoles = roleService.findRoles(roleName);
        return RestResponse.ok(sysRoles);
    }

    @PostRoute("remove")
    @JSON
    public RestResponse remove(Long[] ids) {
        roleService.deleteRoles(ids);
        return RestResponse.ok();
    }


}
