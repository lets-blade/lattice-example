package io.github.biezhi.lattice.example.controller.sys;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import io.github.biezhi.anima.Anima;
import io.github.biezhi.anima.enums.OrderBy;
import io.github.biezhi.anima.page.Page;
import io.github.biezhi.lattice.annotation.Permissions;
import io.github.biezhi.lattice.example.annotation.SysLog;
import io.github.biezhi.lattice.example.controller.BaseController;
import io.github.biezhi.lattice.example.model.SysRole;
import io.github.biezhi.lattice.example.params.RoleParam;
import io.github.biezhi.lattice.example.service.RoleService;

import java.time.LocalDateTime;
import java.util.List;

import static io.github.biezhi.anima.Anima.select;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Path(value = "sys/role", restful = true)
public class RoleController extends BaseController {

    @Inject
    private RoleService roleService;

    @GetRoute("select")
    public RestResponse selectList() {
        return RestResponse.ok(select().from(SysRole.class).order(SysRole::getRoleId, OrderBy.DESC).all());
    }

    @GetRoute("info/:roleId")
    public RestResponse info(@PathParam Long roleId) {
        return RestResponse.ok(select().from(SysRole.class).byId(roleId));
    }

    @SysLog("新增角色")
    @Permissions("sys:role:save")
    @PostRoute("save")
    public RestResponse save(SysRole sysRole) {
        sysRole.setCreatedId(this.userId());
        sysRole.setCreatedTime(LocalDateTime.now());
        sysRole.setModifiedTime(LocalDateTime.now());
        return RestResponse.ok(sysRole.save());
    }

    @SysLog("修改角色")
    @Permissions("sys:role:edit")
    @PostRoute("update")
    public RestResponse update(SysRole sysRole) {
        sysRole.setModifiedTime(LocalDateTime.now());
        return RestResponse.ok(sysRole.update());
    }

    @Permissions("sys:role:list")
    @PostRoute("list")
    public Page<SysRole> list(RoleParam roleParam) {
        return roleService.findRoles(roleParam);
    }

    @SysLog("删除角色")
    @Permissions("sys:role:remove")
    @PostRoute("remove")
    public RestResponse remove(@BodyParam List<Long> ids) {
        return RestResponse.ok().peek(() -> Anima.deleteBatch(SysRole.class, ids));
    }

}
