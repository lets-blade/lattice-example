package io.github.biezhi.lattice.example.controller.sys;

import com.blade.mvc.annotation.BodyParam;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.ui.RestResponse;
import io.github.biezhi.anima.Anima;
import io.github.biezhi.lattice.annotation.Permissions;
import io.github.biezhi.lattice.annotation.Users;
import io.github.biezhi.lattice.example.annotation.SysLog;
import io.github.biezhi.lattice.example.controller.BaseController;
import io.github.biezhi.lattice.example.model.SysMenu;

import java.time.LocalDateTime;
import java.util.List;

import static io.github.biezhi.anima.Anima.select;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Users
@Path(value = "sys/menu", restful = true)
public class MenuController extends BaseController {

    @GetRoute("info")
    public RestResponse info() {
        return RestResponse.ok();
    }

    @SysLog("新增菜单")
    @Permissions("sys:menu:save")
    @PostRoute("save")
    public RestResponse save(SysMenu sysMenu) {
        sysMenu.setCreatedTime(LocalDateTime.now());
        sysMenu.setModifiedTime(LocalDateTime.now());
        return RestResponse.ok(sysMenu.save());
    }

    @SysLog("修改菜单")
    @Permissions("sys:menu:edit")
    @PostRoute("update")
    public RestResponse update(SysMenu sysMenu) {
        sysMenu.setModifiedTime(LocalDateTime.now());
        return RestResponse.ok(sysMenu.update());
    }

    @Permissions("sys:menu:list")
    @GetRoute("list")
    public List<SysMenu> list() {
        return select().from(SysMenu.class).all();
    }

    @SysLog("删除菜单")
    @Permissions("sys:menu:remove")
    @PostRoute("remove")
    public RestResponse remove(@BodyParam List<Long> ids) {
        return RestResponse.ok().peek(() -> Anima.deleteBatch(SysMenu.class, ids));
    }

}
