package me.dragon.hotfix.core.listeners;

import me.dragon.hotfix.core.PatchDescription;
import me.dragon.hotfix.core.enums.InjectLocationEnum;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

public abstract class InjectPatchStrategy  {
    private InjectLocationEnum location;
    private int lineNumber = -1; //-1 means not specified
    
    /**
     * This is used to specify where to inject the code (HEAD, TAIL, BEFORE, AFTER).
     * @param location where to inject the code (HEAD, TAIL, BEFORE, AFTER).
     */
    public InjectPatchStrategy(InjectLocationEnum location) {
        this.location = location;
    }
    /**
     * This is used to specify where to inject the code (HEAD, TAIL, BEFORE, AFTER).
     * And in more specific locations, the line number.
     * @param location where to inject the code (HEAD, TAIL, BEFORE, AFTER).
     * @param lineNumber line number to inject at (if applicable)
     */
    public InjectPatchStrategy(InjectLocationEnum location, int lineNumber) {
        this.location = location;
        this.lineNumber = lineNumber;
    }

    /**
     * Applies this patch to the given target class.
     * The Hotfix core provides a shared {@link ByteBuddy} instance and {@link ClassReloadingStrategy}.
     * @param patch
     * @param targetClass
     * @param byteBuddy
     * @param reloading
     * @return
     * @throws Exception
     */
    @RuntimeType
    public abstract boolean apply(
        PatchDescription patch,
        Class<?> targetClass,
        ByteBuddy byteBuddy,
        ClassReloadingStrategy reloading
    ) throws Exception;

    public InjectLocationEnum getLocation() {
        return location;
    }
    public int getLineNumber() {
        return lineNumber;
    }
}
