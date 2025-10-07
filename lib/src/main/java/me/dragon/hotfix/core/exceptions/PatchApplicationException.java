package me.dragon.hotfix.core.exceptions;


/**
 * Exception thrown when a patch application fails.
 */
public class PatchApplicationException extends Exception {
    /** Just a constructor for 'PatchApplicationException'
     * @param message - The message to display.
     * @throws PatchApplicationException
     */
    public PatchApplicationException(String message) {
        super(message);
    }
    
}
