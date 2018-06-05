package io.github.biezhi.lattice.example.service;

import com.blade.ioc.annotation.Bean;
import io.github.biezhi.lattice.example.model.SysMenu;

import java.util.List;

import static io.github.biezhi.anima.Anima.select;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Bean
public class MenuService {

    public List<SysMenu> findMenus() {
        return select().from(SysMenu.class).all();
    }

    public void deleteMenus(Long[] ids) {

    }

}
