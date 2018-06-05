package io.github.biezhi.lattice.example.params;

import lombok.Data;
import lombok.ToString;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Data
@ToString(callSuper = true)
public class UserParam extends PageParam {

    private String username;

}
