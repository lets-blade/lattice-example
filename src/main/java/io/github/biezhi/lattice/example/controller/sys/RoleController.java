package io.github.biezhi.lattice.example.controller.sys;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import io.github.biezhi.anima.Anima;
import io.github.biezhi.anima.page.Page;
import io.github.biezhi.lattice.example.controller.BaseController;
import io.github.biezhi.lattice.example.model.SysRole;
import io.github.biezhi.lattice.example.params.RoleParam;
import io.github.biezhi.lattice.example.service.RoleService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author biezhi
 * @date 2018/6/5
 */
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
        sysRole.setCreatedId(this.userId());
        sysRole.setCreatedTime(LocalDateTime.now());
        sysRole.setModifiedTime(LocalDateTime.now());
        return RestResponse.ok(sysRole.save());
    }

    @PostRoute("update")
    public RestResponse update(SysRole sysRole) {
        sysRole.setModifiedTime(LocalDateTime.now());
        return RestResponse.ok(sysRole.update());
    }

    @PostRoute("list")
    public Page<SysRole> list(RoleParam roleParam) {
        return roleService.findRoles(roleParam);
    }

    @PostRoute("remove")
    public RestResponse remove(@BodyParam List<Long> ids) {
        return RestResponse.ok().peek(() -> Anima.deleteBatch(SysRole.class, ids));
    }

}
