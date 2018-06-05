package io.github.biezhi.lattice.example.enums;

import lombok.Getter;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Getter
public enum UserStatus {

    DISABLE(0), NORMAL(1);

    private Integer status;

    UserStatus(int status) {
        this.status = status;
    }
}
