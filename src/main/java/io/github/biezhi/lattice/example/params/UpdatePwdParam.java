package io.github.biezhi.lattice.example.params;

import lombok.Data;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Data
public class UpdatePwdParam {

    private String pwd;
    private String newPwd;

    private String username;
    private Long   userId;
}
