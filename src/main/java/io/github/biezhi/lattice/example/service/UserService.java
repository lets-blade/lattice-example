package io.github.biezhi.lattice.example.service;

import com.blade.exception.ValidatorException;
import com.blade.ioc.annotation.Bean;
import com.blade.kit.EncryptKit;
import com.blade.kit.StringKit;
import com.blade.validator.Validators;
import io.github.biezhi.anima.core.AnimaQuery;
import io.github.biezhi.anima.enums.OrderBy;
import io.github.biezhi.anima.page.Page;
import io.github.biezhi.lattice.example.enums.MenuType;
import io.github.biezhi.lattice.example.enums.UserStatus;
import io.github.biezhi.lattice.example.model.*;
import io.github.biezhi.lattice.example.params.UpdatePwdParam;
import io.github.biezhi.lattice.example.params.UserParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.github.biezhi.anima.Anima.select;
import static io.github.biezhi.anima.Anima.update;

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
        return new ArrayList<>();
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

        return new ArrayList<>();
    }

    public Set<String> findUserPerms(Long userId) {
        return findUserMenus(userId).stream()
                .map(SysMenu::getPerms)
                .filter(StringKit::isNotEmpty)
                .map(perms -> perms.split(","))
                .flatMap(Arrays::stream)
                .collect(Collectors.toSet());
    }

    public void updatePwd(UpdatePwdParam updatePwdParam) {
        Validators.notEmpty().test(updatePwdParam.getPwd()).throwIfInvalid("旧密码");
        Validators.notEmpty().test(updatePwdParam.getNewPwd()).throwIfInvalid("新密码");

        Validators.moreThan(6).test(updatePwdParam.getNewPwd()).throwIfInvalid("新密码");

        if (updatePwdParam.getPwd().equals(updatePwdParam.getNewPwd())) {
            throw new ValidatorException("新密码和旧密码相同");
        }

        String  oldPwd  = EncryptKit.md5(updatePwdParam.getUsername() + updatePwdParam.getPwd());
        SysUser sysUser = select().from(SysUser.class).byId(updatePwdParam.getUserId());
        if (!sysUser.getPassword().equals(oldPwd)) {
            throw new ValidatorException("旧密码错误");
        }

        String newPwd = EncryptKit.md5(updatePwdParam.getUsername() + updatePwdParam.getNewPwd());
        update().from(SysUser.class).set(SysUser::getPassword, newPwd).where(SysUser::getUserId, sysUser.getUserId()).execute();
    }

    public Page<SysUser> findUsers(UserParam param) {
        AnimaQuery<SysUser> query = select().from(SysUser.class);
        if (StringKit.isNotEmpty(param.getUsername())) {
            query.and(SysUser::getUsername, param.getUsername());
        }
        return query.page(param.getPageNumber(), param.getPageSize());
    }

    public void updateStatus(UserStatus userStatus, List<Long> ids) {
        for (Long id : ids) {
            update().from(SysUser.class).set(SysUser::getStatus, userStatus.getStatus()).where(SysUser::getUserId, id);
        }
    }

    public List<SysMenu> listMenus(Long userId) {
        List<Long> menuIdList = findUserMenus(userId).stream().map(SysMenu::getMenuId).collect(Collectors.toList());
        return getAllMenuList(menuIdList);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenu> getAllMenuList(List<Long> menuIdList) {
        //查询根菜单列表
        List<SysMenu> menuList = listParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIdList) {
        List<SysMenu> subMenuList = new ArrayList<SysMenu>();

        for (SysMenu entity : menuList) {
            if (entity.getType() == MenuType.CATALOG.getType()) {//目录
                entity.setList(getMenuTreeList(listParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }
        return subMenuList;
    }

    public List<SysMenu> listParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenu> menuList = select().from(SysMenu.class)
                .where(SysMenu::getParentId, parentId)
                .order(SysMenu::getOrderNum, OrderBy.DESC).all();

        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenu> userMenuList = new ArrayList<>();
        for (SysMenu menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

}
