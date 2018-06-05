package io.github.biezhi.lattice.example.controller.sys;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import io.github.biezhi.lattice.annotation.Users;
import io.github.biezhi.lattice.example.controller.BaseController;
import io.github.biezhi.lattice.example.model.SysMenu;
import io.github.biezhi.lattice.example.service.MenuService;

import java.util.List;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Users
@Path("sys/menu")
public class MenuController extends BaseController {

    @Inject
    private MenuService menuService;

    @GetRoute("info")
    @JSON
    public RestResponse info(@Param String username) {
        return RestResponse.ok();
    }

    @PostRoute("save")
    @JSON
    public RestResponse save(SysMenu sysMenu) {

        return RestResponse.ok();
    }

    @PostRoute("update")
    @JSON
    public RestResponse update(SysMenu sysMenu) {

        return RestResponse.ok();
    }

    @GetRoute("list")
    @JSON
    public RestResponse list() {
        List<SysMenu> sysMenus = menuService.findMenus();
        return RestResponse.ok(sysMenus);
    }

    @PostRoute("remove")
    @JSON
    public RestResponse remove(Long[] ids) {
        menuService.deleteMenus(ids);
        return RestResponse.ok();
    }


}
