package adv.q11;

public class Output {
    void outerMethod() {
        System.out.println("Inside outerMethod");
// Inner class is local to outerMethod()
        class Inner {
            void innerMethod() {
                System.out.println("Inside innerMethod");
            }
        }
        Inner a = new Inner();
        a.innerMethod();
    }
}
class Test1 {
    public static void main(String[] args) {
        Output b = new Output();
        b.outerMethod();
    }
}

