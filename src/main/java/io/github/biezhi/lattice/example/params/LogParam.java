package io.github.biezhi.lattice.example.params;

import com.blade.kit.json.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Data
@ToString(callSuper = true)
public class LogParam extends PageParam {

    private String    username;

    @JsonFormat("yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat("yyyy-MM-dd")
    private LocalDate endDate;

}
