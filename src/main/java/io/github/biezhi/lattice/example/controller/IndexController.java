
package io.github.biezhi.lattice.example.controller;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;

@Path
public class IndexController {

	@GetRoute("/")
	public String index(){
		return "index.html";
	}

}
