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
public class SysLog extends Model {

    private Long          id;
    private Long          userId;
    private String        username;
    private String        operation;
    private String        method;
    private String        params;
    private String        ip;

    @JsonFormat("yyyy/MM/dd HH:mm:ss")
    private LocalDateTime createdTime;
}
