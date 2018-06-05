package io.github.biezhi.lattice.example.service;

import com.blade.ioc.annotation.Bean;
import com.blade.kit.StringKit;
import io.github.biezhi.anima.core.AnimaQuery;
import io.github.biezhi.anima.page.Page;
import io.github.biezhi.lattice.example.model.SysRole;
import io.github.biezhi.lattice.example.params.RoleParam;

import static io.github.biezhi.anima.Anima.select;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Bean
public class RoleService {

    public Page<SysRole> findRoles(RoleParam roleParam) {

        AnimaQuery<SysRole> query = select().from(SysRole.class);
        if (StringKit.isNotEmpty(roleParam.getRoleName())) {
            query.and(SysRole::getRoleName, roleParam.getRoleName());
        }
        return query.page(roleParam.getPageNumber(), roleParam.getPageSize());
    }

}
