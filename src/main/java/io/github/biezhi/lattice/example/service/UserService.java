package io.github.biezhi.lattice.example.service;

import com.blade.ioc.annotation.Bean;
import io.github.biezhi.lattice.example.model.SysMenu;
import io.github.biezhi.lattice.example.model.SysRole;
import io.github.biezhi.lattice.example.model.SysRoleMenu;
import io.github.biezhi.lattice.example.model.SysUserRole;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.github.biezhi.anima.Anima.select;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Bean
public class UserService {

    public List<SysRole> findUserRoles(Long userId) {
        List<Long> roleIds = select(SysUserRole::getRoleId)
                .from(SysUserRole.class)
                .where(SysUserRole::getUserId, userId)
                .map(SysUserRole::getRoleId)
                .collect(Collectors.toList());

        if (null != roleIds && roleIds.size() > 0) {
            return select().from(SysRole.class)
                    .where(SysRole::getRoleId).in(roleIds)
                    .all();
        }
        return null;
    }

    public List<SysMenu> findUserMenus(Long userId) {
        List<Long> roleIds = findUserRoles(userId).stream().map(SysRole::getRoleId)
                .collect(Collectors.toList());

        if (null != roleIds && roleIds.size() > 0) {
            List<Long> menuIds = select(SysRoleMenu::getMenuId).from(SysRoleMenu.class)
                    .where(SysRoleMenu::getRoleId).in(roleIds)
                    .map(SysRoleMenu::getMenuId).collect(Collectors.toList());

            return select().from(SysMenu.class).where(SysMenu::getMenuId).in(menuIds).all();
        }

        return null;
    }

    public Set<String> findUserPerms(Long userId) {
        return findUserMenus(userId).stream()
                .map(SysMenu::getPerms)
                .map(perms -> perms.split(","))
                .flatMap(Arrays::stream)
                .collect(Collectors.toSet());
    }

}
