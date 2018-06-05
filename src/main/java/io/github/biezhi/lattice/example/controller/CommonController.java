package io.github.biezhi.lattice.example.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.ui.RestResponse;
import io.github.biezhi.lattice.Lattice;
import io.github.biezhi.lattice.LoginToken;
import io.github.biezhi.lattice.exception.AuthenticationException;
import io.github.biezhi.lattice.exception.DisabledAccountException;
import io.github.biezhi.lattice.exception.UnknownAccountException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Path
@Slf4j
public class CommonController {

    @Inject
    private Lattice lattice;

    @GetRoute("login")
    public String login() {
        return "login.html";
    }

    @PostRoute("login")
    public RestResponse doLogin(LoginToken loginToken) {
        try {
            lattice.login(loginToken);
            return RestResponse.ok();
        } catch (UnknownAccountException e) {
            return RestResponse.fail("找不到该用户");
        } catch (AuthenticationException e) {
            return RestResponse.fail("用户名或密码错误");
        } catch (DisabledAccountException e) {
            return RestResponse.fail("该用户已经被禁用");
        } catch (Exception e) {
            log.error("登录出现异常", e);
            return RestResponse.fail("登录发生错误");
        }
    }

    @GetRoute("unauthorized")
    public String unauthorized() {
        return "error/unauthorized.html";
    }

}
