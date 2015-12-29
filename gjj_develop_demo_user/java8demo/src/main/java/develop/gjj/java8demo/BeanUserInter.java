package develop.gjj.java8demo;

/**
 * 作者：gjj on 2015/12/29 13:47
 * 邮箱：Gujj512@163.com
 */
public class BeanUserInter {
    /**
     * :: 表示方法引用
     * A::B 表示引用A类的B方法，B方法必须为静态方法
     *
     * 这里我们同样恶意传一个 Lambda 表达式，
     * 在这个表达式中提供 compare 方法的实现。
     * 但是在我们的类中，我们的 Employee 类已经有了一个自己的比较方法。
     * 只是他们的名字是不一样的，
     * 参数的类型、数量，返回值都是相同的，
     * 这里我们就可以创建一个方法引用，并将它传递给 sort 作为第二个参数
     */
    PersonFactory personFactory=new PersonFactory() {
        @Override
        public Person getPerson() {
            return new Person();
        }
    };
    PersonFactory personFactory1=()->new Person();
    /**
     * 后半部分相当于表示一个匿名内部类
     * a::b这个表示的是，后半部分方法里面的返回值和a中的b方法的返回值是一样的
     */
    PersonFactory factory=Person::new;
    Person person=factory.getPerson();
    interface PersonFactory{
        public Person getPerson();
//        public Person getPerson(String name,String pwd);
    }

    class Person{
        String name;
        String pwd;
        public Person(){

        }
        public Person(String name,String pwd){
            this.name=name;
            this.pwd=pwd;
        }
    }
}
