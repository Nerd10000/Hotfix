package me.dragon.hotfix.core.test;

import me.dragon.hotfix.core.Hotfix;
import me.dragon.hotfix.core.Patch;

public class Main {
    public static void main(String[] args) {
        Hotfix.prepare();
        System.out.println("Hello World!");

        new Patch("Ts")
        .author("Dragon")
        .version("1.21")
        .description("Test patch")
        .patchVersion("1.0")
        .apply(new Test());

        Hotfix.applyAll();
    }
}
