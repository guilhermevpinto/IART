package vars;

public class Skill {

	private Scope scope;
	private float performance;
	
	public Skill(Scope scope, float performance) {
		this.scope = scope;
		this.performance = performance;
	}
	
	public float getPerformance(){
		return this.performance;
	}
	
	public Scope getScope() {
		return this.scope;
	}
}
