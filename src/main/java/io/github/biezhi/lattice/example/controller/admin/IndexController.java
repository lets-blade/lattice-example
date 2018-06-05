package io.github.biezhi.lattice.example.controller.admin;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Path("admin")
public class IndexController {

    @GetRoute({"/", "/index"})
    public String index() {
        return "admin/main.html";
    }

}
