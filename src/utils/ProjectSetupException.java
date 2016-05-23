package utils;

import vars.Scope;

public class ProjectSetupException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private Scope scope;
	
	public ProjectSetupException(Scope scope) {
		this.scope = scope;
	}
	
	public String toString() {
		return "Missing " + this.scope.getDescription() + " skill on workers to execute the project!";
	}
}
