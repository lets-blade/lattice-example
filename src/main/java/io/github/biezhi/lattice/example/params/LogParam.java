package io.github.biezhi.lattice.example.params;

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
    private LocalDate startDate;
    private LocalDate endDate;

}
