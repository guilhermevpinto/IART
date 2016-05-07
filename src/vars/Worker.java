package vars;

import java.util.HashMap;

/**
 * Created by Guilherme on 11/04/2016.
 */
public class Worker {
    protected static int idRef = 0;
    private int id;
    private String name;
    private HashMap<Integer, Skill> skills;  // id of respective Scope - Skill_info

    public Worker(String name) {
        this.id = Worker.idRef++;
        this.name = name;
        this.skills = new HashMap<Integer, Skill>();
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
    
    public Skill getSkillFromScope(Scope scope) {
    	return this.skills.get(scope.getId());
    }
    
    public HashMap<Integer,Skill> getSkills() {
    	return this.skills;
    }
    
}
