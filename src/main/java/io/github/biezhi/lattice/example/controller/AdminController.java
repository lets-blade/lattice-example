package io.github.biezhi.lattice.example.controller;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PathParam;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Path(value = "admin")
public class AdminController {

    @GetRoute({"/", "/index"})
    public String index() {
        return "main.html";
    }

    @GetRoute("/:module/:page.html")
    public String pages(@PathParam String module, @PathParam String page) {
        return "sys/" + module + "/" + page;
    }

}