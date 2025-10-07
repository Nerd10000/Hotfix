package me.dragon.hotfix.core;

public class ParameterAccessor {
    private String functionName;
    private Class<?>[] parameterTypes;
    private Object[] args;


    public ParameterAccessor(String functionName, Class<?>[] parameterTypes, Object[] args) {
        this.functionName = functionName;
        this.parameterTypes = parameterTypes;
        this.args = args;
    }
    /* *
     * Constructor that takes a class and function name, and retrieves the parameter types of the function.
    */
    public ParameterAccessor(Class<?> clazz, String functionName){
        
        for(java.lang.reflect.Method method : clazz.getDeclaredMethods()){
            if(method.getName().equals(functionName)){
                this.functionName = functionName;
                this.parameterTypes = method.getParameterTypes();
                break;
            }
        }
    }

   public Class<?> getParameter(int index){
       if(index < 0 || index >= parameterTypes.length){
           throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + parameterTypes.length);
       }
       return parameterTypes[index];
   }
   public Class<?>[] getParameterTypes(){
       return parameterTypes;
   }
    public String getFunctionName(){
         return functionName;
    }

}
