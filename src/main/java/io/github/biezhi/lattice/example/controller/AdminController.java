package io.github.biezhi.lattice.example.controller;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Path(value = "admin", suffix = ".html")
public class AdminController {

    @GetRoute({"/", "/index"})
    public String index() {
        return "main.html";
    }

}