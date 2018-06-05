package io.github.biezhi.lattice.example;

import com.blade.Blade;
import io.github.biezhi.lattice.LatticeMiddleware;

public class Application {

    public static void main(String[] args) {
        Blade.me().use(new LatticeMiddleware()).start(Application.class, args);
    }

}