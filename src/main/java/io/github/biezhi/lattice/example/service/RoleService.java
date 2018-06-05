package io.github.biezhi.lattice.example.service;

import com.blade.ioc.annotation.Bean;
import com.blade.kit.StringKit;
import io.github.biezhi.lattice.example.model.SysRole;

import java.util.List;

import static io.github.biezhi.anima.Anima.select;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Bean
public class RoleService {

    public List<SysRole> findRoles(String roleName) {
        if (StringKit.isNotBlank(roleName)) {
            return select().from(SysRole.class).where(SysRole::getRoleName, roleName).all();
        }
        return select().from(SysRole.class).all();
    }

    public void deleteRoles(Long[] ids) {

    }

}
