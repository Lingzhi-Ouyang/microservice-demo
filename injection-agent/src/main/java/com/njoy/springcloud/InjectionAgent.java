package com.njoy.springcloud;

import javassist.*;

import javax.management.remote.rmi._RMIConnection_Stub;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @author ouyanglingzhi
 */
public class InjectionAgent {
    private static final InjectionAgent instance;
    static {
        instance = new InjectionAgent();
    }
    public static void premain(String arg, Instrumentation instrumentation) {
        System.out.println("init");
        final String config = arg;
        final ClassPool classPool = new ClassPool();
        classPool.appendSystemPath();
        instrumentation.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                if(null == className || !className.replaceAll("/", ".").startsWith(config)){
                    return null;
                }
                System.out.println(className);
//                if(!"com/njoy/springcloud/controller/PaymentController".equals(className)){
//                    return null;
//                }
                try{
                    className = className.replaceAll("/", ".");
                    CtClass ctClass = classPool.get(className);
                    System.out.println(className);
                    for(CtMethod ctMethod: ctClass.getDeclaredMethods()){
                        newMethod(ctMethod);
                    }
//                    CtMethod ctMethod = ctClass.getDeclaredMethod("queryById");
//                    CtMethod copy = CtNewMethod.copy(ctMethod, ctClass, null);
//                    ctClass.addMethod(copy);
//                    ctMethod.insertBefore("System.out.println(\"before\");");
                    return ctClass.toBytecode();
                } catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    private static CtMethod newMethod(CtMethod originalMethod) throws CannotCompileException, NotFoundException {
        CtMethod copy = CtNewMethod.copy(originalMethod, originalMethod.getDeclaringClass(), null);
        copy.setName(originalMethod.getName() + "$agent");
        originalMethod.getDeclaringClass().addMethod(copy);
        if (originalMethod.getReturnType().equals(CtClass.voidType)){
            originalMethod.setBody(String.format(voidSource, originalMethod.getName()));
        } else {
            originalMethod.setBody(String.format(source, originalMethod.getName()));
        }
        return copy;
    }


    final static String source = "        {\n" +
            "            long begin = System.currentTimeMillis();\n" +
            "            Object result;\n" +
            "            {\n" +
            "                try {\n" +
            "                    result = ($w)%s$agent($$);\n" +
            "                } finally {\n" +
            "                    long end = System.currentTimeMillis();\n" +
            "                    log.info(\"span time: {}\", end - begin);\n" +
            "                }\n" +
            "                return ($r) result;\n" +
            "            }\n" +
            "        }";

    final static String voidSource = "        {\n" +
            "            long begin = System.currentTimeMillis();\n" +
            "            Object result;\n" +
            "            {\n" +
            "                try {\n" +
            "                    %s$agent($$);\n" +
            "                } finally {\n" +
            "                    long end = System.currentTimeMillis();\n" +
            "                    log.info(\"span time: {}\", end - begin);\n" +
            "                }\n" +
            "            }\n" +
            "        }";


}
