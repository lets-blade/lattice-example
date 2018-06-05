package io.github.biezhi.lattice.example.enums;

import lombok.Getter;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Getter
public enum MenuType {

    DIR(0), MENU(1), BUTTON(2);

    private int type;

    MenuType(int type) {
        this.type = type;
    }
}
