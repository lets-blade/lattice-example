package io.github.biezhi.lattice.example.config;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.blade.kit.EncryptKit;
import io.github.biezhi.lattice.AuthInfo;
import io.github.biezhi.lattice.LatticeRealm;
import io.github.biezhi.lattice.LoginToken;
import io.github.biezhi.lattice.example.enums.UserStatus;
import io.github.biezhi.lattice.example.model.SysRole;
import io.github.biezhi.lattice.example.model.SysUser;
import io.github.biezhi.lattice.example.service.UserService;
import io.github.biezhi.lattice.exception.AuthenticationException;
import io.github.biezhi.lattice.exception.DisabledAccountException;
import io.github.biezhi.lattice.exception.UnknownAccountException;

import java.util.Set;
import java.util.stream.Collectors;

import static io.github.biezhi.anima.Anima.select;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Bean
public class JdbcRealm implements LatticeRealm<SysUser> {

    @Inject
    private UserService userService;

    @Override
    public AuthInfo<SysUser> doAuthenticate(LoginToken loginToken) {
        AuthInfo<SysUser> authInfo = new AuthInfo<>();

        SysUser sysUser = select().from(SysUser.class).where(SysUser::getUsername, loginToken.getUsername()).one();
        if (null == sysUser) {
            throw new UnknownAccountException();
        }
        String pwd = EncryptKit.md5(loginToken.getUsername() + loginToken.getPassword());
        if (!sysUser.getPassword().equals(pwd)) {
            throw new AuthenticationException();
        }
        if (UserStatus.DISABLE.getStatus().equals(sysUser.getStatus())) {
            throw new DisabledAccountException();
        }
        authInfo.setUser(sysUser);
        authInfo.setUsername(sysUser.getUsername());
        return authInfo;
    }

    @Override
    public void doAuthorize(AuthInfo<SysUser> authInfo) {
        Long userId = authInfo.getUser().getUserId();

        Set<String> roleSet = userService.findUserRoles(userId).stream()
                .map(SysRole::getRoleSign).collect(Collectors.toSet());
        authInfo.setRoles(roleSet);

        Set<String> permissions = userService.findUserPerms(userId);
        authInfo.setPermissions(permissions);
    }

}
