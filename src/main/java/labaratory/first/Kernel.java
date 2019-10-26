package labaratory.first;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

class Kernel {
    private SystemCall[] systemCalls;
    private Stack<Class> argumentStack;

    /**
     * @param argumentStack stack of inputted args by user
     */
    Kernel(Stack<Class> argumentStack) {
        this.argumentStack = argumentStack;
        try {
            systemCalls = new SystemCall[]{
                    new SystemCall("read", "using for read some file",
                            Arrays.asList(
                                    new Argument(Class.forName("java.io.File"), "file", "File to read to"),
                                    new Argument(Class.forName("java.util.Scanner"), "scanner", "Class to read file")
                            )),
                    new SystemCall("open", "using for open file for some operations",
                            Arrays.asList(
                                    new Argument(Class.forName("java.io.File"), "file", "File to open to"),
                                    new Argument(Class.forName("java.lang.String"), "mod", "What type access should be")
                            )),
                    new SystemCall("write", "using for write something to file",
                            Arrays.asList(
                                    new Argument(Class.forName("java.io.File"), "file", "File to write to"),
                                    new Argument(Class.forName("java.nio.Buffer"), "buffer", "Type of buffer"),
                                    new Argument(Class.forName("java.lang.Byte"), "byte", "Count of byte to write")
                            )),
                    new SystemCall("close", "using for close file after some operations",
                            Collections.singletonList(
                                    new Argument(Class.forName("java.io.File"), "file", "File to close")
                            )),
                    new SystemCall("mkdir", "using for create directory",
                            Arrays.asList(
                                    new Argument(Class.forName("java.lang.String"), "nameOfDirectory", "Name for created Directory"),
                                    new Argument(Class.forName("java.lang.String"), "mode", "set access rights")
                            ))
            };
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * running defined system call by id
     *
     * @param id of system call
     * @throws Exception if it's have some issues
     */
    public void runSysCall(int id) throws Exception {
        if (argumentStack.size() != systemCalls[id].getArgs().size()) {
            throw new Exception("Количество аргументов не совпадает!");
        }
        if (!(checkToCorrectArgsTypes(systemCalls[id].getArgs()))) {
            throw new Exception("Не совпал тип одного из аргументов!");
        }
        System.out.println("The system call: (" + systemCalls[id].getName() + ") is done!");
    }

    /**
     * checking to correct of args of system call and inputted by user
     *
     * @param args list of defined system call args
     * @return the result of checking
     */
    private boolean checkToCorrectArgsTypes(List<Argument> args) {
        return IntStream.iterate(args.size() - 1, i -> i >= 0, i -> i - 1)
                .allMatch(i -> argumentStack.pop().equals(args.get(i).getArgument()));
    }

    /**
     * output all system calls
     */
    public void printAllSysCalls() {
        IntStream.range(0, systemCalls.length)
                .mapToObj(i -> String.format(
                "Идентификатор: %d\n" +
                        "Имя метода: %s\n" +
                        "Аргументы: %s",
                i, systemCalls[i].getName(), getParams(systemCalls[i].getArgs())))
                .forEach(System.out::println);
    }

    /**
     * creating a string which contains all types system call arguments
     *
     * @param params massive of system call arguments
     * @return string with all types of parameters of system call
     */
    private String getParams(List<Argument> params) {
        StringBuilder sb = new StringBuilder();
        params.forEach(arg -> sb.append(arg.getArgument().getName()).append(" "));
        return new String(sb);
    }
}
