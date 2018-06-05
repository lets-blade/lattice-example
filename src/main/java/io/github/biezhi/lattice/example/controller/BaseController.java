package io.github.biezhi.lattice.example.controller;

import com.blade.mvc.WebContext;
import io.github.biezhi.lattice.AuthInfo;
import io.github.biezhi.lattice.example.model.SysUser;

import java.util.Optional;

/**
 * @author biezhi
 * @date 2018/6/5
 */
public class BaseController {

    public SysUser loginUser() {
        AuthInfo authInfo = WebContext.request().session().attribute("LATTICE");
        return (SysUser) Optional.ofNullable(authInfo).map(AuthInfo::getUser).orElse(null);
    }

}
