package labaratory.first;

import lombok.Data;

import java.util.List;

@Data
class SystemCall {
	private String name;
	private String description;
	private List<Argument> args;

	SystemCall(String name, String description, List<Argument> args){
		this.name = name;
		this.description = description;
		this.args = args;
	}
}
