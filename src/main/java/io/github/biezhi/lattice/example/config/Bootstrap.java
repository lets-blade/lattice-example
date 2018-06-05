package io.github.biezhi.lattice.example.config;

import com.blade.Blade;
import com.blade.Environment;
import com.blade.event.BeanProcessor;
import com.blade.ioc.annotation.Bean;
import com.blade.mvc.view.template.JetbrickTemplateEngine;
import io.github.biezhi.anima.Anima;
import com.alibaba.druid.pool.DruidDataSource;
import io.github.biezhi.lattice.Lattice;

@Bean
public class Bootstrap implements BeanProcessor {

    @Override
    public void preHandle(Blade blade) {
        Lattice lattice = new Lattice();
        lattice.loginUrl("/login").unauthorizedUrl("/unauthorized");

        lattice.realm(new JdbcRealm());

        blade.register(lattice);
    }

    @Override
    public void processor(Blade blade) {

        blade.templateEngine(new JetbrickTemplateEngine());

        // JDBC
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(blade.environment().getOrNull("jdbc.url"));
        dataSource.setUsername(blade.environment().getOrNull("jdbc.username"));
        dataSource.setPassword(blade.environment().getOrNull("jdbc.password"));

        Anima.open(dataSource);
    }

}