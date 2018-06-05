package io.github.biezhi.lattice.example.bootstrap;

import com.blade.exception.ValidatorException;
import com.blade.ioc.annotation.Bean;
import com.blade.mvc.WebContext;
import com.blade.mvc.handler.DefaultExceptionHandler;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.mvc.ui.RestResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Bean
@Slf4j
public class ExceptionHandler extends DefaultExceptionHandler {

    @Override
    public void handle(Exception e) {
        if (ValidatorException.class.isInstance(e)) {
            Response response = WebContext.response();
            response.json(RestResponse.fail(e.getMessage()));
        } else {
            super.handle(e);
        }
    }

}
