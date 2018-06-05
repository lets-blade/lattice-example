package io.github.biezhi.lattice.example.service;

import com.blade.ioc.annotation.Bean;
import io.github.biezhi.lattice.example.model.SysLog;
import io.github.biezhi.lattice.example.params.LogParam;

import java.util.List;

import static io.github.biezhi.anima.Anima.delete;

/**
 * @author biezhi
 * @date 2018/6/5
 */
@Bean
public class LogService {

    public List<SysLog> findLogs(LogParam logParam) {
        return null;
    }

    public void deleteLog(Long id) {
        delete().from(SysLog.class).where(SysLog::getId, id).execute();
    }

    public void cleanLog() {
        delete().from(SysLog.class).execute();
    }

}
