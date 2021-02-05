package javassist;

import java.lang.reflect.InvocationTargetException;

/**
 * @author shenxiang
 * @date 2020/11/17 15:06
 * @description
 */
public class CreatePerson {

    public static void createPerson() throws Exception{
        final ClassPool pool = ClassPool.getDefault();
        // 1、创建一个空类
        final CtClass cc = pool.makeClass("javassist.Person");
        // 2、新增一个字段 private String name
        final CtField param = new CtField(pool.get("java.lang.String"), "name", cc);
        // 访问级别为private
        param.setModifiers(Modifier.PRIVATE);
        // 初始值是
        cc.addField(param,CtField.Initializer.constant("lvmama"));
        // 生成getter、setter
        cc.addMethod(CtNewMethod.setter("setName",param));
        cc.addMethod(CtNewMethod.getter("getName",param));

        // 4、添加无参的构造函数
        CtConstructor cons = new CtConstructor(new CtClass[]{},cc);
        cons.setBody("{name = \"joyu\";}");
        cc.addConstructor(cons);

        // 5、添加有参的构造函数
        cons = new CtConstructor(new CtClass[]{pool.get("java.lang.String")},cc);
        cons.setBody("{$0.name = $1;}");
        cc.addConstructor(cons);

        // 6、创建一个名为printName方法
        CtMethod ctMethod = new CtMethod(CtClass.voidType,"printName",new CtClass[]{},cc);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody("{System.out.println(name);}");
        cc.addMethod(ctMethod);
        // 生成.class文件
        //cc.writeFile("E:/java/example");
        // 直接实例化
        /*
        final Object person = cc.toClass().newInstance();
        final Method setName = person.getClass().getMethod("setName", String.class);
        setName.invoke(person,"qichuang");
        final Method execute = person.getClass().getMethod("printName");
        execute.invoke(person);
         */
    }

    public static void main(String[] args) throws Exception {
        //createPerson();
        //refInstance();
        interfaceInstance();
    }

    private static void interfaceInstance() throws Exception{
        final ClassPool pool = ClassPool.getDefault();
        pool.appendClassPath("E:/java/example");
        // 获取接口
        final CtClass ctClassI = pool.get("javassist.PersonI");
        // 获取生成的类
        final CtClass ctClass = pool.get("javassist.Person");
        // 使用代码生成的类,实现PersionI接口
        ctClass.setInterfaces(new CtClass[]{ctClassI});

        PersonI person = (PersonI)ctClass.toClass().newInstance();
        System.out.println(person.getName());
        person.setName("juzi");
        person.printName();

    }

    private static void refInstance() throws NotFoundException, NoSuchMethodException, CannotCompileException, IllegalAccessException, InstantiationException, InvocationTargetException {
        final ClassPool pool = ClassPool.getDefault();
        pool.appendClassPath("E:/java/example");
        final CtClass ctClass = pool.get("javassist.Person");
        final Object person = ctClass.toClass().newInstance();
        person.getClass().getMethod("printName").invoke(person);
        person.getClass().getMethod("setName",String.class).invoke(person,"xxx");
        person.getClass().getMethod("printName").invoke(person);
    }
}
