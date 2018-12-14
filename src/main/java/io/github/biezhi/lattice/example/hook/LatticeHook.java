package io.github.biezhi.lattice.example.hook;

import com.blade.ioc.annotation.Bean;
import com.blade.kit.JsonKit;
import com.blade.mvc.RouteContext;
import com.blade.mvc.WebContext;
import com.blade.mvc.hook.WebHook;
import io.github.biezhi.lattice.AuthInfo;
import io.github.biezhi.lattice.Constant;
import io.github.biezhi.lattice.example.annotation.SysLog;
import io.github.biezhi.lattice.example.model.SysUser;

import java.time.LocalDateTime;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Bean
public class LatticeHook implements WebHook {

    @Override
    public boolean before(RouteContext ctx) {
        return true;
    }

    @Override
    public boolean after(RouteContext ctx) {
        SysLog sysLog = ctx.routeAction().getAnnotation(SysLog.class);
        if (null != sysLog) {
            io.github.biezhi.lattice.example.model.SysLog log = new io.github.biezhi.lattice.example.model.SysLog();
            log.setCreatedTime(LocalDateTime.now());
            log.setIp(ctx.address());
            log.setOperation(sysLog.value());
            log.setMethod(ctx.routeAction().getName());
            if (ctx.contentType().contains("json")) {
                log.setParams(ctx.bodyToString());
            } else {
                log.setParams(JsonKit.toString(ctx.parameters()));
            }

            AuthInfo<SysUser> authInfo = WebContext.request().session().attribute(Constant.DEFAULT_SESSION_KEY);

            log.setUserId(authInfo.getUser().getUserId());
            log.setUsername(authInfo.getUser().getUsername());
            log.save();
        }
        return true;
    }

}
