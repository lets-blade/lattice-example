package io.github.biezhi.lattice.example.model;

import io.github.biezhi.anima.Model;
import lombok.Data;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Data
public class SysUserRole extends Model {

    private Long id;
    private Long userId;
    private Long roleId;

}
