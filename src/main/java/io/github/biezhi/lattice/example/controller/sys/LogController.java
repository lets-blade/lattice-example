package io.github.biezhi.lattice.example.controller.sys;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PathParam;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.ui.RestResponse;
import io.github.biezhi.anima.page.Page;
import io.github.biezhi.lattice.annotation.Users;
import io.github.biezhi.lattice.example.controller.BaseController;
import io.github.biezhi.lattice.example.model.SysLog;
import io.github.biezhi.lattice.example.params.LogParam;
import io.github.biezhi.lattice.example.service.LogService;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Users
@Path(value = "sys/log", restful = true)
public class LogController extends BaseController {

    @Inject
    private LogService logService;

    @GetRoute("list")
    public Page<SysLog> list(LogParam logParam) {
        return logService.findLogs(logParam);
    }

    @PostRoute("remove/:id")
    public RestResponse remove(@PathParam Long id) {
        return RestResponse.ok(logService.deleteLog(id));
    }

    @PostRoute("clean")
    public RestResponse clean() {
        return RestResponse.ok(logService.cleanLog());
    }

}
