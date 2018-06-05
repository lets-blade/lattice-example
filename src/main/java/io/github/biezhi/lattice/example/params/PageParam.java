package io.github.biezhi.lattice.example.params;

import lombok.Data;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Data
public class PageParam {

    private Integer pageNumber = 1;
    private Integer pageSize   = 10;

}
