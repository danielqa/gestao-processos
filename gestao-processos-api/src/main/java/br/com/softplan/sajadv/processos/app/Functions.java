package br.com.softplan.sajadv.processos.app;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

public class Functions {

    public static Object executeMethodByClassNameAndMethodName(String classFullName, String methodName) {
        try {
            Class<?> clazz = Class.forName(classFullName);
            Method method = ReflectionUtils.findMethod(clazz, methodName);
            return ReflectionUtils.invokeMethod(method, clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
