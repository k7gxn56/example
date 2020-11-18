package javassist;

import java.lang.reflect.Method;

/**
 * @author shenxiang
 * @date 2020/11/18 11:10
 * @description
 */
public class UpdatePersonService {

    private static void update() throws Exception{
        final ClassPool pool = ClassPool.getDefault();
        final CtClass cc = pool.get("javassist.PersonService");

        final CtMethod ctMethod = cc.getDeclaredMethod("personFly");
        ctMethod.insertBefore("System.out.println(\"起飞之前的动作\");");
        ctMethod.insertAfter("System.out.println(\"成功落地后的动作\");");

        // 新增一个方法
        final CtMethod newCtMethod = new CtMethod(CtClass.voidType, "joinFriend", new CtClass[]{}, cc);
        newCtMethod.setModifiers(Modifier.PUBLIC);
        newCtMethod.setBody("{System.out.println(\"I want to be your friend\");}");
        cc.addMethod(newCtMethod);

        final Object personService = cc.toClass().newInstance();
        // 调用personFly方法
        final Method flyMethod = personService.getClass().getMethod("personFly");
        flyMethod.invoke(personService);
        // 调用joinFriend方法
        final Method joinFriend = personService.getClass().getMethod("joinFriend");
        joinFriend.invoke(personService);
    }
    public static void main(String[] args) {
        try{
            update();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
