package io.github.biezhi.lattice.example.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleParam extends PageParam {

    private String roleName;

}
