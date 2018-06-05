package io.github.biezhi.lattice.example.model;

import com.blade.kit.json.JsonFormat;
import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Data
@Table(pk = "role_id")
public class SysRole extends Model {

    private Long          roleId;
    private String        roleName;
    private String        roleSign;
    private String        remark;
    private Long          createdId;

    @JsonFormat("yyyy/MM/dd HH:mm:ss")
    private LocalDateTime createdTime;

    @JsonFormat("yyyy/MM/dd HH:mm:ss")
    private LocalDateTime modifiedTime;
}
