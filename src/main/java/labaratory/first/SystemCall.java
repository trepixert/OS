package labaratory.first;

import java.util.List;

class SystemCall {
	private String name;
	private String description;
	private List<Argument> args;

	SystemCall(String name, String description, List<Argument> args){
		this.name = name;
		this.description = description;
		this.args = args;
	}

	public String getName() {
		return name;
	}

	public List<Argument> getArgs() {
		return args;
	}

	public String getDescription() {
		return description;
	}
}
