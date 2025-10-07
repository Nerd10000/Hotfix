package me.dragon.hotfix.core.test;

import me.dragon.hotfix.core.PatchDescription;
import me.dragon.hotfix.core.listeners.ReplacePatchStrategy;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

public class Test  implements ReplacePatchStrategy{

    @Override
    
    public boolean apply(PatchDescription patch, Class<?> targetClass, ByteBuddy byteBuddy,
            ClassReloadingStrategy reloading) throws Exception {
        // TODO Auto-generated method stub
        //DO Code here....
        return true;
    }
    
}
