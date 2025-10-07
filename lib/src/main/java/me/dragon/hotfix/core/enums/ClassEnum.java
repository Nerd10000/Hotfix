package me.dragon.hotfix.core.enums;

import org.bukkit.Bukkit;
/**
 * Enum representing specific NMS classes with their required server versions.
 */
public enum ClassEnum {
    REGION_FILE_STORAGE("1.21", "net.minecraft.server.v1_20_R1.RegionFileStorage");

    private final String nmsVersion;
    private final String classPath;

    ClassEnum(String nmsVersion, String classPath) {
        this.nmsVersion = nmsVersion;
        this.classPath = classPath;
    }

    public String getNmsVersion() {
        return nmsVersion;
    }

    public String getClassPath() {
        return classPath;
    }

    /**
     * Resolves the enum to the actual Class object.
     * @return the Class<?> corresponding to this NMS class
     * @throws ClassNotFoundException if the class cannot be found
     * @throws IllegalStateException if the server version is incompatible
     */
    public Class<?> resolve() throws ClassNotFoundException {
        String version = Bukkit.getMinecraftVersion();
        if (!version.startsWith(nmsVersion)) {
            throw new IllegalStateException(
                "Incompatible NMS version! Required: " + nmsVersion + ", but found: " + version
            );
        }
        return Class.forName(classPath);
    }
}