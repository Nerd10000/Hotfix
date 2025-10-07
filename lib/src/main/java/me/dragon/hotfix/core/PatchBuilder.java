package me.dragon.hotfix.core;

import me.dragon.hotfix.core.listeners.ReplacePatchStrategy;
import me.dragon.hotfix.core.listeners.UniversalPatchStrategy;
//File: PatchBuilder.java
public class PatchBuilder {
   
   /**
    * Wraps a ReplacePatchStrategy into a UniversalPatchStrategy.
    * @param strategy - The ReplacePatchStrategy to wrap.
    * @return A UniversalPatchStrategy that delegates to the wrapped strategy.
    */
    public static UniversalPatchStrategy wrapReplacePatchStrategy(ReplacePatchStrategy strategy) {
        return (patch, targetClass, byteBuddy, reloading) -> {
            return strategy.apply(patch, targetClass, byteBuddy, reloading);
        };
    }
}
