package labaratory.first;

import lombok.Data;

@Data
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
}
