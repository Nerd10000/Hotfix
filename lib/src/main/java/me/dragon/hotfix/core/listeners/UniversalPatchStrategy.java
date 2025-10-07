package me.dragon.hotfix.core.listeners;

import me.dragon.hotfix.core.PatchDescription;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

/**
 * A patch strategy that can represent any patching approach.
 * <p> 
 * This is a more general interface that can be used to implement various patching strategies,
 * including both replacement and injection strategies.
 * </p>
 * <br> This is primarily used to allow wrapping of existing strategies
 * (e.g., {@link ReplacePatchStrategy}) into a more universal form.
 * @see ReplacePatchStrategy
 * @see InjectPatchStrategy
 */
public interface UniversalPatchStrategy {
    @RuntimeType
    public boolean apply(
        PatchDescription patch,
        Class<?> targetClass,
        ByteBuddy byteBuddy,
        ClassReloadingStrategy reloading
    ) throws Exception;
}
