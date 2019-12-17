package com.test.memoryModel;


/**
 * class文件
 * magic      u4   魔数
 * minor_version  u2 副版本号
 * major_version  u2 主版本号
 * constant_pool_count  u2 常量池计数器
 * constant_pool[constant_pool_count-1] cp_info 常量池
 * access_flags  u2 访问标识
 * this_class  u2 类索引
 * super_class   u2 父类索引
 * interfaces_count  u2 接口计数器
 * intetfaces[interfaces_count]  u2 接口表
 * fields_count  u2 字段计数器
 * fields[fields_count]  field_info 字段表
 * methods_count  u2 方法计数器
 * methods[methods_count]  method_info 方法表
 * attributes_count  u2  属性计数器
 * attributes[attributes_count]  attribute_info 属性表
 *
 * 常量池的理解
 * 常量池中项的格式
 * cp_info{
 *     tag
 *     info[]
 * }
 *
 * 根据jvm 规范可知
 * 运行时常量区
 * 存储了一些非常简单数据类型的信息
 * 他保存的数据结构形式
 * CONSTANT_Class
 * CONSTANT_Fieldref
 * CONSTANT_Methodref
 * CONSTANT_Interfaceref
 * CONSTANT_String
 * CONSTANT_Integer
 * CONSTANT_Float
 * CONSTANT_Long
 * CONSTANT_Double
 * CONSTANT_NameAndType
 * CONSTANT_Utf8
 * CONSTANT_MethodHandle
 * CONSTANT_MethodType
 * CONSTANT_InvokeDynamic
 */
public class Test1 {

    private static String b = "c";
    private String a = "dd";
    /**
     *  下面类我调用了System类的方法，在常量池中的表示
     *  常量池中会存储
     *  把System.out看成一个属性引用
     *  CONSTANT_Fieldref{
     *      tag
     *      class_index   CONSTANT_Class
     *      name_and_type_index  CONSTANT_NameAndType
     *  }
     *
     *  class_index代表的是
     * CONSTANT_Class{
     *     tag
     *     name_index CONSTANT_Utf8
     * }
     *
     * CONSTANT_Utf8{
     *     tag
     *     length
     *     bytes[length]  这里面存储的是类的字面量常量 "java/lang/System"
     * }
     *
     * -----
     * CONSTANT_NameAndType{
     *     tag
     *     name_index CONSTANT_Utf8
     *     descriptor_index CONSTANT_Utf8
     * }
     *
     * CONSTANT_Utf8{
     *     tag
     *     length
     *     bytes[length]  这里面存储的是类的字面量常量 "out"
     * }
     * CONSTANT_Utf8{
     *     tag
     *     length
     *     bytes[length]  这里面存储的是类的字面量常量 "Ljava/io/PrintStream;"
     * }
     *
     * 通过getstatic操作码获取静态字段的值 #+数字 代表了静态字段在常量池中的索引，通过索引找到相对应的静态字段信息
     *
     * 其实通过上面的表示只能表示一个类的静态的信息
     * 就是一个类的基本信息和方法的静态信息
     * 但是当代码执行的时候
     * 在class字节码的中很多符号引用会被转化成直接引用
     *
     * 符号引用和直接引用
     * 符号引用
     *      符号引用：以一组符号来描述所引用的目标，符号可以是任何形式的字面量，只要使用时能够无歧义的定位到目标即可。
     *    例如是上面指定的数据结构，符号引用和虚拟机的内存布局无关
     * 直接引用
     *      直接引用：直接指向目标的指针（比如，指向“类型”【Class对象】、类变量、类方法的直接引用可能是指向方法区的指针）
     *    相对偏移量（比如，指向实例变量、实例方法的直接引用都是偏移量）、相对偏移量（比如，指向实例变量、实例方法的直接引
     *    用都是偏移量）直接引用是和虚拟机的布局相关的
     * 类在解析阶段会把类中的符号引用转化成直接引用
     *
     * 在class问件被解析后
     * 符号引用转换成直接引用的过程叫做解析
     * 同时也是class文件常量池关联上运行时内存中的常量池数据结构的过程
     * 一些对其它类的符号引用会变成直接引用
     * 比如下面方法我们调用了System.out参数的调用就会转化成对实例化对象在堆中PrintStream内存的直接引用
     *
     * class对象会通过jvm底层的C++对象instanceKlass对象描述
     * 实例化对象通过oop描述
     *
     * 我们所写的可读性较高的类，只是我们的一种表达方式，通过javac编译器编译后的Class文件也是一种静态的类表达形式，
     * 这个类文件是jvm类加载，解析，验证等工具的可读的，对他们来说可读性更高
     *
     * 类文件在虚拟机中会经历加载，链接，初始化
     * 链接又包含验证、准备、解析几个过程
     * 加载的含义：从class文件字节流中提取类型信息
     *
     * 这个过程比较关键的是：读取class文件中常量池定义创建运行时常量池，并返回一个运行时常量池句柄，并作为下面this类和super类解析
     * 做准备
     *
     *
     * 对于当前类的读取：并按照从运行时常量池中获取类的全限定名
     * 读取父类的索引 ： 并按照索引在运行时常量池中找到父类的全限定名和父类的句柄
     *
     * 。。。过程
     * 最后通过解析字段填充instanceKlass,完成解析
     * 最后创建java镜像类并初始化静态域
     * 更改类加载状态
     *
     *
     * 理解通过类文件的常量池创建运行时常量池的过程中有一些过程并没有解析完全对于某些虚拟机比如Hotspot，因为它的解析属于晚解析
     * 使用的时候在解析，比如当获取父类的句柄时，可触发对于父类的加载，并产生父类句柄赋值到运行时常量池中，通过那些信息找到的父类那
     * 通过符号引用可以找到类的全限定名。其中包含类的地址信息，通过信息就可以找到类的class文件地址，读取文件字节流并采用同样的形式
     * 对class文件加载
     *
     * 上面理解有误
     * 其实在加载阶段并没有实现解析的过程
     * 所以在对父类进行处理时。获取的是父类句柄，句柄是代表句柄指向了一个内存区，内存区中的存的地址是指针指向的是直接内存，所以句柄
     * 指向的内存值是可以发生变化的，只是在运行时常量区为父类生成了一个占位符，可能发生改变，
     * 只有链接的时候才会把直接引用替换掉符号引用
     *
     */

    public void testMethod1(){
//        System.out.println("方法1" + Thread.currentThread());
        System.out.println("方法1");
    }

    /**
     * 比如下面这种情况符号引用会转化成对Test2类类型在方法区中内存的直接引用
     */
    public void testMethod2(){
        Test2 test2 = new Test2();
        System.out.println("方法2" + Thread.currentThread());
    }
}
