package io.github.biezhi.lattice.example.service;

import com.blade.ioc.annotation.Bean;
import com.blade.kit.StringKit;
import io.github.biezhi.anima.Anima;
import io.github.biezhi.anima.core.AnimaQuery;
import io.github.biezhi.anima.page.Page;
import io.github.biezhi.lattice.example.model.SysRole;
import io.github.biezhi.lattice.example.model.SysRoleMenu;
import io.github.biezhi.lattice.example.params.RoleParam;

import java.util.List;
import java.util.stream.Collectors;

import static io.github.biezhi.anima.Anima.delete;
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

    public void updateRoleOptAuthorization(SysRole sysRole) {
        Anima.atomic(() -> {
            Long roleId = sysRole.getRoleId();
            delete().from(SysRoleMenu.class).where(SysRoleMenu::getRoleId, roleId).execute();

            for (Long menuId : sysRole.getMenuIdList()) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(roleId);
                sysRoleMenu.save();
            }
        });
    }

    public SysRole findRole(Long roleId) {
        SysRole sysRole = select().from(SysRole.class).byId(roleId);
        if(null != sysRole){
            List<Long> menuIdList = select().from(SysRoleMenu.class)
                    .where(SysRoleMenu::getRoleId, roleId).map(SysRoleMenu::getMenuId).collect(Collectors.toList());
            sysRole.setMenuIdList(menuIdList);
        }
        return sysRole;
    }
}
