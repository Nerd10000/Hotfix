package me.dragon.hotfix.core;

import me.dragon.hotfix.core.enums.ClassEnum;
import me.dragon.hotfix.core.enums.TargetType;
import me.dragon.hotfix.core.helpers.PatchBuilder;
import me.dragon.hotfix.core.listeners.ReplacePatchStrategy;
import me.dragon.hotfix.core.listeners.UniversalPatchStrategy;

/**
 * File: Patch.java
 * Represents a single patch that can be applied to a target class/method in Minecraft NMS.
 * <p>
 * Use this class with a fluent API to define patch metadata, the target, delegate class,
 * and the strategy to apply. Calling {@link #apply(ReplacePatchStrategy)} will automatically
 * wrap the strategy and register the patch in {@link Hotfix}.
 * </p>
 *
 * <pre>{@code
 * Hotfix.prepare();
 *
 * new Patch("Chunk Load Override")
 *     .author("DragonDev")
 *     .version("1.21")
 *     .description("Custom chunk reader")
 *     .target(ClassEnum.REGION_FILE_STORAGE)
 *     .method("read")
 *     .delegate(ChunkReadDelegate.class)
 *     .apply(new ReplaceChunkReadStrategy());
 *
 * Hotfix.applyAll();
 * }</pre>
 */
public class Patch {

    /** Internal patch description storing all metadata and strategy. */
    private final PatchDescription description;

    /**
     * Creates a new patch with a given name.
     *
     * @param name The unique name of the patch.
     */
    public Patch(String name){
        this.description = new PatchDescription(name, "unknown", "unknown", "No description", "0.0.1");
    }    

    /**
     * Sets the author of the patch.
     *
     * @param author The author name.
     * @return The current Patch instance for chaining.
     */
    public Patch author(String author) {
        this.description.author = author;
        return this;
    }

    /**
     * Sets the Minecraft/NMS version this patch is intended for.
     *
     * @param version Version string (e.g., "1.21").
     * @return The current Patch instance for chaining.
     */
    public Patch version(String version) {
        this.description.version = version;
        return this;
    }

    /**
     * Sets a human-readable description for the patch.
     *
     * @param desc The description text.
     * @return The current Patch instance for chaining.
     */
    public Patch description(String desc) {
        this.description.description = desc;
        return this;
    }

    /**
     * Sets the version of the patch itself.
     *
     * @param patchVersion Patch version string (e.g., "0.0.1").
     * @return The current Patch instance for chaining.
     */
    public Patch patchVersion(String patchVersion) {
        this.description.patchVersion = patchVersion;
        return this;
    }

    /**
     * Sets the target class for the patch.
     *
     * @param clazz The NMS class enum representing the target.
     * @return The current Patch instance for chaining.
     */
    public Patch target(Class<?> clazz) {
        this.description.targetClass = clazz;
        return this;
    }

    /**
     * Sets the method name that this patch will target.
     *
     * @param methodName The target method name.
     * @return The current Patch instance for chaining.
     */
    public Patch method(String methodName){
        this.description.targetFunctionName = methodName;
        this.description.targetType = TargetType.METHOD;
        return this;
    }

    /**
     * Sets the delegate class for method redirection or interception.
     *
     * @param delegateClass The delegate class to use.
     * @return The current Patch instance for chaining.
     */
    public Patch delegate(Class<?> delegateClass) {
        this.description.delegateClass = delegateClass;
        return this;
    }

    /**
     * Applies a {@link ReplacePatchStrategy} to this patch.
     * <p>
     * This method automatically wraps the strategy into a {@link UniversalPatchStrategy}
     * and registers the patch in {@link Hotfix}.
     * </p>
     *
     * @param strategy The ReplacePatchStrategy to apply.
     * @return The current Patch instance for chaining.
     */
    public Patch apply(ReplacePatchStrategy strategy) {
        this.description.strategy = PatchBuilder.wrapReplacePatchStrategy(strategy);
        Hotfix.registerPatch(this.description);
        return this;
    }


    /**
     * Returns the internal {@link PatchDescription}.
     *
     * @return The PatchDescription for this patch.
     */
    public PatchDescription getDescription() {
        return description;
    }
}