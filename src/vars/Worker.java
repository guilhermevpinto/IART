package vars;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Guilherme on 11/04/2016.
 */
public class Worker {
	public static ArrayList<Worker> allWorkers = new ArrayList<Worker> ();
    protected static int idRef = 0;
    private int id;
    private String name;
    private HashMap<Integer, Skill> skills;  // id of respective Scope - Skill_info

    public Worker(String name) {
        this.id = Worker.idRef++;
        this.name = name;
        this.skills = new HashMap<Integer, Skill>();
        Worker.allWorkers.add(this);
    }
    
    public int getId(){
    	return this.id;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public void addSkill(Skill skill) {
    	this.skills.put(skill.getScope().getId(), skill);
    }
    
    public Skill getSkillFromScopeId(int scope) {
    	return this.skills.get(scope);
    }
    
    public HashMap<Integer,Skill> getSkills() {
    	return this.skills;
    }
    
}
