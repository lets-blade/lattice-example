package io.github.biezhi.lattice.example.service;

import com.blade.exception.ValidatorException;
import com.blade.ioc.annotation.Bean;
import com.blade.kit.EncryptKit;
import com.blade.validator.Validators;
import io.github.biezhi.anima.page.Page;
import io.github.biezhi.lattice.example.enums.UserStatus;
import io.github.biezhi.lattice.example.model.*;
import io.github.biezhi.lattice.example.params.UpdatePwdParam;
import io.github.biezhi.lattice.example.params.UserParam;

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

    public void updatePwd(UpdatePwdParam updatePwdParam) {
        Validators.notEmpty().test(updatePwdParam.getPwd()).throwIfInvalid("旧密码");
        Validators.notEmpty().test(updatePwdParam.getNewPwd()).throwIfInvalid("新密码");
        Validators.lessThan(6).test(updatePwdParam.getNewPwd()).throwIfInvalid("新密码");

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

    public Page<SysUser> findUsers(UserParam userParam) {

        return null;
    }

    public int deleteUsers(Long[] ids) {
        return 0;
    }

    public int updateStatus(UserStatus userStatus, Long[] ids) {
        return 0;
    }

}
