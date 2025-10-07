package me.dragon.hotfix.core;

import org.bukkit.Bukkit;

import me.dragon.hotfix.core.enums.ClassEnum;
import me.dragon.hotfix.core.enums.DescriptorType;
import me.dragon.hotfix.core.enums.TargetType;
import me.dragon.hotfix.core.listeners.UniversalPatchStrategy;

/**
 * File: PatchDescription.java
 * Stores all metadata and targeting information for a single patch.
 * <p>
 * This class is used internally by {@link Patch} and {@link Hotfix} to apply patches
 * in a uniform way using {@link UniversalPatchStrategy}.
 * </p>
 */
public final class PatchDescription {

    /** Patch unique name */
    public String name;

    /** Patch author */
    public String author;

    /** Target Minecraft/NMS version */
    public String version;

    /** Human-readable description */
    public String description;

    /** Version of the patch itself */
    public String patchVersion;

    /** The strategy to execute the patch (wrapped into UniversalPatchStrategy) */
    public UniversalPatchStrategy strategy;

    /** The resolved target class */
    public Class<?> targetClass;

    /** The target function name */
    public String targetFunctionName;

    /** The type of target (method, field, constructor) */
    public TargetType targetType;

    /** JVM descriptor of the target method/field (optional) */
    public String targetJVMDescriptor;

    /** Descriptor type */
    public DescriptorType descriptorType;

    public Class<?> delegateClass; 

    /**
     * Constructs a new PatchDescription with default placeholder values.
     *
     * @param name The unique patch name.
     * @param author The author of the patch.
     * @param version Target Minecraft/NMS version.
     * @param description Human-readable description.
     * @param patchVersion The patch version.
     * @throws IllegalArgumentException if the server version is incompatible.
     */
    public PatchDescription(String name, String author, String version, String description, String patchVersion) {
        this.name = name;
        this.author = author;
        this.version = version;
        this.description = description;
        if (validateNMSVersion(version)) {
            this.patchVersion = patchVersion;
        } else {
            throw new IllegalArgumentException("Invalid NMS version for this server! From patch: '"
                    + author + "::" + name + "::v" + patchVersion + "' but server version is: "
                    + Bukkit.getVersion());
        }
    }

    /**
     * Checks if the provided NMS version matches the server version.
     *
     * @param versionString The NMS version string to check.
     * @return true if the version matches, false otherwise.
     */
    public boolean validateNMSVersion(String versionString) {
        return Bukkit.getVersion().contains(versionString);
    }

    /**
     * Sets the patch strategy.
     *
     * @param strategy The {@link UniversalPatchStrategy} to execute when applying the patch.
     * @return this PatchDescription for chaining.
     */
    public PatchDescription setStrategy(UniversalPatchStrategy strategy) {
        this.strategy = strategy;
        return this;
    }

    /**
     * Defines the target of the patch.
     *
     * @param classEnum The class enum representing the target.
     * @param functionName The target method/field/constructor name.
     * @param targetType The type of the target (method, field, constructor).
     * @param JVMdescriptor Optional JVM descriptor (see {@link DescriptorType} for reference).
     * @return this PatchDescription for chaining.
     */
    public PatchDescription setTarget(Class<?> classEnum, String functionName, TargetType targetType, String JVMdescriptor) {
        this.targetClass = classEnum;
        this.targetFunctionName = functionName;
        this.targetType = targetType;
        this.targetJVMDescriptor = JVMdescriptor;
        return this;
    }

    /**
     * Returns the assigned strategy for this patch.
     *
     * @return The {@link UniversalPatchStrategy} or null if not set.
     */
    public UniversalPatchStrategy getStrategy() {
        return this.strategy;
    }
}