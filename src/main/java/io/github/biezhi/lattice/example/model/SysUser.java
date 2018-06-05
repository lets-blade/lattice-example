package io.github.biezhi.lattice.example.model;

import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.EnumMapping;
import io.github.biezhi.anima.annotation.Table;
import io.github.biezhi.lattice.example.enums.UserStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Data
@Table(pk = "user_id")
public class SysUser extends Model {

    private Long          userId;
    private String        username;
    private String        password;
    private String        email;
    private String        mobile;
    @EnumMapping(EnumMapping.ORDINAL)
    private UserStatus    status;
    private String        remark;
    private Long          createdId;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

}
