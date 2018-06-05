package io.github.biezhi.lattice.example.controller.sys;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Param;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.ui.RestResponse;
import io.github.biezhi.lattice.annotation.Users;
import io.github.biezhi.lattice.example.controller.BaseController;
import io.github.biezhi.lattice.example.model.SysMenu;
import io.github.biezhi.lattice.example.service.MenuService;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Users
@Path(value = "sys/menu", restful = true)
public class MenuController extends BaseController {

    @Inject
    private MenuService menuService;

    @GetRoute("info")
    public RestResponse info(@Param String username) {
        return RestResponse.ok();
    }

    @PostRoute("save")
    public RestResponse save(SysMenu sysMenu) {
        return RestResponse.ok();
    }

    @PostRoute("update")
    public RestResponse update(SysMenu sysMenu) {

        return RestResponse.ok();
    }

    @GetRoute("list")
    public RestResponse list() {
        return RestResponse.ok(menuService.findMenus());
    }

    @PostRoute("remove")
    public RestResponse remove(Long[] ids) {
        return RestResponse.ok(menuService.deleteMenus(ids));
    }


}
