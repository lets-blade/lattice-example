package io.github.biezhi.lattice.example.controller.sys;

import com.blade.mvc.annotation.BodyParam;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.ui.RestResponse;
import io.github.biezhi.anima.Anima;
import io.github.biezhi.lattice.annotation.Users;
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

    @PostRoute("save")
    public RestResponse save(SysMenu sysMenu) {
        sysMenu.setCreatedTime(LocalDateTime.now());
        sysMenu.setModifiedTime(LocalDateTime.now());
        return RestResponse.ok(sysMenu.save());
    }

    @PostRoute("update")
    public RestResponse update(SysMenu sysMenu) {
        sysMenu.setModifiedTime(LocalDateTime.now());
        return RestResponse.ok(sysMenu.update());
    }

    @GetRoute("list")
    public RestResponse list() {
        return RestResponse.ok(select().from(SysMenu.class).all());
    }

    @PostRoute("remove")
    public RestResponse remove(@BodyParam List<Long> ids) {
        return RestResponse.ok().peek(() -> Anima.deleteBatch(SysMenu.class, ids));
    }

}
