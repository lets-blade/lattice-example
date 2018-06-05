package io.github.biezhi.lattice.example.model;

import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.EnumMapping;
import io.github.biezhi.anima.annotation.Table;
import io.github.biezhi.lattice.example.enums.MenuType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Data
@Table(pk = "menu_id")
public class SysMenu extends Model {

    private Long          menuId;
    private Long          parentId;
    private String        name;
    private String        url;
    private String        perms;
    private Integer       type;
    private String        icon;
    private Integer       orderNum;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    // sub menus
    private List<SysMenu> list;
}
