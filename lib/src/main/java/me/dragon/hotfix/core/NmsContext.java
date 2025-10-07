package me.dragon.hotfix.core;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.apache.commons.math3.util.Pair;

public class NmsContext {
    private Class<?> clazz;
    private Parameter[] parameters;
    
    public NmsContext(Class<?> nmsClass){
        this.clazz = nmsClass;
    
    }

    public Class<?> getNmsClass(){
        return clazz;
    }

    public Parameter[] getParameters(String methodName){
        collectParametersByMethodName(methodName);
        return parameters;
    }

    public Class<?> getReturnType(String methodName) {
        Method methods[] = clazz.getDeclaredMethods();
        for (Method m : methods) {
            if (m.getName().equals(methodName)) {
                return m.getReturnType();
                
            }
        }
        return null;
    }

    
    public String getNmsClassName(){
        return clazz.getName();
    }


    private void collectParametersByMethodName(String methodName){
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName() == methodName) {
                this.parameters = method.getParameters();
                break;                
            }
        }
    }
    
}