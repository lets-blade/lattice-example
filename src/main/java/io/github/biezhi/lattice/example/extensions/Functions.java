package io.github.biezhi.lattice.example.extensions;

import com.blade.kit.StringKit;
import com.blade.mvc.WebContext;
import io.github.biezhi.lattice.AuthInfo;
import io.github.biezhi.lattice.Constant;

/**
 * 函数扩展
 *
 * @author biezhi
 * @date 2018/6/6
 */
public class Functions {

    /**
     * 判断是否有某个权限
     *
     * @param permission
     * @return
     */
    public static boolean hasPermission(String permission) {
        AuthInfo authInfo = WebContext.request().session().attribute(Constant.DEFAULT_SESSION_KEY);
        if (StringKit.isNotEmpty(permission) && null != authInfo) {
            return authInfo.hasPermission(permission);
        }
        return false;
    }

    /**
     * 判断是否有某个角色
     * @param role
     * @return
     */
    public static boolean hasRole(String role) {
        AuthInfo authInfo = WebContext.request().session().attribute(Constant.DEFAULT_SESSION_KEY);
        if (StringKit.isNotEmpty(role) && null != authInfo) {
            return authInfo.hasRole(role);
        }
        return false;
    }

}
