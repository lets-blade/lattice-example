package io.github.biezhi.lattice.example.bootstrap;

import com.alibaba.druid.pool.DruidDataSource;
import com.blade.Blade;
import com.blade.ioc.annotation.Bean;
import com.blade.loader.BladeLoader;
import com.blade.mvc.view.template.JetbrickTemplateEngine;
import com.blade.validator.Validators;
import io.github.biezhi.anima.Anima;
import io.github.biezhi.lattice.Lattice;
import io.github.biezhi.lattice.example.extensions.Functions;

@Bean
public class Bootstrap implements BladeLoader {

    @Override
    public void preLoad(Blade blade) {
        Lattice lattice = new Lattice();
        lattice.loginUrl("/login").unauthorizedUrl("/unauthorized");

        blade.register(lattice);
    }

    @Override
    public void load(Blade blade) {

        Validators.useChinese();

        JetbrickTemplateEngine templateEngine = new JetbrickTemplateEngine();
        templateEngine.getGlobalResolver().registerFunctions(Functions.class);

        blade.templateEngine(templateEngine);

        // JDBC
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(blade.environment().getOrNull("jdbc.url"));
        dataSource.setUsername(blade.environment().getOrNull("jdbc.username"));
        dataSource.setPassword(blade.environment().getOrNull("jdbc.password"));

        Anima.open(dataSource);
    }

}