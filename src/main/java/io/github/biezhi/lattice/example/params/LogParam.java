package io.github.biezhi.lattice.example.params;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Data
public class LogParam {

    private String    username;
    private LocalDate startDate;
    private LocalDate endDate;

}
