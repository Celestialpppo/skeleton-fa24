//public class TestRun {
//    static class Animal {
//        void speak() {
//            System.out.println("Animal speaks");
//        }
//    }
//
//    static class Dog extends Animal {
//        void bark() {
//            System.out.println("Dog barks");
//        }
//    }
//
//    public static void main(String[] args) {
//        Animal a = new Dog();
//
//        // 下面这一行会编译错误，因为 Animal 没有 bark 方法
//         a.bark();
//
//        // 正确写法：强制类型转换
//        if (a instanceof Dog) {
//            ((Dog) a).bark(); // ✅ 输出 Dog barks
//        }
//    }
//}
