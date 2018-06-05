package io.github.biezhi.lattice.example.controller.sys;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import io.github.biezhi.lattice.annotation.Users;
import io.github.biezhi.lattice.example.controller.BaseController;
import io.github.biezhi.lattice.example.model.SysLog;
import io.github.biezhi.lattice.example.params.LogParam;
import io.github.biezhi.lattice.example.service.LogService;

import java.util.List;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Users
@Path("sys/log")
public class LogController extends BaseController {

    @Inject
    private LogService logService;

    @GetRoute("list")
    @JSON
    public RestResponse list(LogParam logParam) {
        List<SysLog> sysLogs = logService.findLogs(logParam);
        return RestResponse.ok(sysLogs);
    }

    @PostRoute("remove/:id")
    @JSON
    public RestResponse remove(@PathParam Long id) {
        logService.deleteLog(id);
        return RestResponse.ok();
    }

    @PostRoute("clean")
    @JSON
    public RestResponse clean() {
        logService.cleanLog();
        return RestResponse.ok();
    }

}
