package io.github.biezhi.lattice.example.controller;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import io.github.biezhi.lattice.annotation.Users;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Users
@Path(value = "admin")
public class AdminController {

    @GetRoute({"/", "/index"})
    public String index() {
        return "index.html";
    }

    @GetRoute({"/main.html"})
    public String main() {
        return "main.html";
    }

}