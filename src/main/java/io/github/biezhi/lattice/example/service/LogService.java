package io.github.biezhi.lattice.example.service;

import com.blade.ioc.annotation.Bean;
import com.blade.kit.StringKit;
import io.github.biezhi.anima.core.AnimaQuery;
import io.github.biezhi.anima.page.Page;
import io.github.biezhi.lattice.example.model.SysLog;
import io.github.biezhi.lattice.example.params.LogParam;

import static io.github.biezhi.anima.Anima.select;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Bean
public class LogService {

    public Page<SysLog> findLogs(LogParam logParam) {
        AnimaQuery<SysLog> query = select().from(SysLog.class);
        if (StringKit.isNotEmpty(logParam.getUsername())) {
            query.and(SysLog::getUsername, logParam.getUsername());
        }
        if (null != logParam.getStartDate()) {
            query.and(SysLog::getCreatedTime).gte(logParam.getStartDate());
        }
        if (null != logParam.getEndDate()) {
            query.and(SysLog::getCreatedTime).lte(logParam.getEndDate());
        }
        return query.page(logParam.getPageNumber(), logParam.getPageSize());
    }

}
