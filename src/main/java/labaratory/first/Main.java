package labaratory.first;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        // write your code here
        try (Scanner keyboard = new Scanner(System.in)) {
            Stack<Class> argumentStack = new Stack<>();
            Kernel kernel = new Kernel(argumentStack);
            kernel.printAllSysCalls();
            System.out.println("\nType id of system call");
            int id = keyboard.nextInt();
            switch (id) {
                case 0:
                case 2:
                    argumentStack.push(Class.forName("java.io.File"));
                    argumentStack.push(Class.forName("java.util.Scanner"));
                    break;
                case 1:
                    argumentStack.push(Class.forName("java.io.File"));
                    argumentStack.push(Class.forName("java.lang.String"));
                    break;
                case 3:
                    argumentStack.push(Class.forName("java.util.Scanner"));
                    break;
                default:
                    throw new Exception("Такого идентификатора не существует");
            }
            kernel.runSysCall(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
