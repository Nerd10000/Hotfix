package me.dragon.hotfix.core.types;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A object that quarantines another object.
 * This is used to prevent memory leaks and other issues.
 * @exception Exception
 */

public class QuarantinedObject <T> {
    private T object;


    public QuarantinedObject(T object) {
        try {
            this.object = object;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Gets the quarantined object.
     * @return The quarantined object.
     */
    public T get() {
        try {
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Sets the quarantined object.
     * @param object - The object to
     * 
     */
    public void set(T object) {
        try {
            this.object = object;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Runs the given consumer with the quarantined object.
     * @param consumer - The consumer to run with the quarantined object.
     */
    public void run(Consumer<T> consumer) {
        try {
            consumer.accept(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Calls the given function with the quarantined object and returns the result.
     * @param function - The function to call with the quarantined object.
     * @return The result of the function call.
     */
    public  <R> R callAndReturn(Function<T, R> function) {
        try {
            return function.apply(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Releases the quarantined object.
     * This is used to prevent memory leaks and other issues.
     */
    public void release() {
        this.object = null;
    }



}
