package labaratory.first;

class Argument {
    private Class argument;
    private String name;
    private String description;
    /**
     * @param argument type of parameter
     */
    Argument(Class argument, String name, String description){
        this.argument = argument;
        this.name = name;
        this.description = description;
    }

    public Class getArgument() {
        return argument;
    }

    public String getDescription() {
        return description;
    }
}
