package io.github.biezhi.lattice.example.controller.sys;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.BodyParam;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.ui.RestResponse;
import io.github.biezhi.anima.Anima;
import io.github.biezhi.anima.page.Page;
import io.github.biezhi.lattice.annotation.Users;
import io.github.biezhi.lattice.example.controller.BaseController;
import io.github.biezhi.lattice.example.model.SysLog;
import io.github.biezhi.lattice.example.params.LogParam;
import io.github.biezhi.lattice.example.service.LogService;

import java.util.List;

import static io.github.biezhi.anima.Anima.delete;

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

    @PostRoute("remove")
    public RestResponse remove(@BodyParam List<Long> ids) {
        return RestResponse.ok().peek(() -> Anima.deleteBatch(SysLog.class, ids));
    }

    @PostRoute("clean")
    public RestResponse clean() {
        return RestResponse.ok(delete().from(SysLog.class).execute());
    }

}
