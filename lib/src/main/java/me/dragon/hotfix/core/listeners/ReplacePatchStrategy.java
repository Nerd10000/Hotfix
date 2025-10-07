package me.dragon.hotfix.core.listeners;

import me.dragon.hotfix.core.PatchDescription;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

/**
 * A patch strategy that completely replaces a target method's body.
 * <p>
 * This strategy does not allow access to the original NMS implementation
 * (i.e., you cannot call the base method via {@code super(...)} or {@link net.bytebuddy.implementation.bind.annotation.SuperCall}).
 * <br>
 * If you need to extend the original behavior instead of replacing it,
 * use {@link InjectPatchStrategy}.
 */
@FunctionalInterface
public interface ReplacePatchStrategy {

    /**
     * Applies this patch to the given target class.
     * The Hotfix core provides a shared {@link ByteBuddy} instance and {@link ClassReloadingStrategy}.
     *
     * @param patch        metadata describing this patch
     * @param targetClass  the resolved NMS class being patched use base classes or the Enum that provides it (ClassEnum).
     * @param byteBuddy    shared {@link ByteBuddy} instance
     * @param reloading    shared {@link ClassReloadingStrategy} instance
     * @return {@code true} if the patch was applied successfully
     * @throws Exception if patching fails for any reason
     */
    @RuntimeType
    boolean apply(
        PatchDescription patch,
        Class<?> targetClass,
        ByteBuddy byteBuddy,
        ClassReloadingStrategy reloading
    ) throws Exception;
}