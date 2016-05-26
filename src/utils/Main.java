package utils;
import java.util.HashMap;
import java.util.Map.Entry;

import vars.Project;
import vars.Scope;
import vars.Skill;
import vars.Task;
import vars.Worker;

/**
 * Created by Guilherme on 12/04/2016.
 */
public class Main {

	public static Project project;
	
	//Defining all the workers assigned to the project
	private Worker andre = new Worker("Andr� Lago");
	private Worker guilherme = new Worker("Guilherme Pinto");
	private Worker gustavo = new Worker("Gustavo Silva");
	private Worker pedro = new Worker("Pedro Castro");
	
	//Defining all the possible required scopes
	private Scope s1 = new Scope("Arquitetura de Bases de Dados");
	private Scope s2 = new Scope("Documenta��o");
	private Scope s3 = new Scope("Divulga��o");
	private Scope s4 = new Scope("Programa��o HTML/CSS");
	private Scope s5 = new Scope("Programa��o PHP");
	private Scope s6 = new Scope("Programa��o PostgreSQL");
	
	
	
	public Main() {
		setupProject();
		setupWorkers();
		
		try  {
			validateProject();
		}
		catch (ProjectSetupException e) {
			System.out.println(e);
			System.exit(1);
		}
		
		//numChromossomes,numGenerations,numSelections,cutsPerCrossover,mutationP
		//new GeneticAlgorithm(99, 10000, 5, 2, 0.3);
		
	}

	

	public void setupProject() {
		
		//Defining Tasks and respective precedences
		//User Requirements Specification
		Task a1 = new Task("Project Presentation", 1, s3);
		Task a2 = new Task("Actors and User Stories", 1, s2);
		a2.addPrecedence(a1);
		Task a3 = new Task("User Interfaces Prototypes", 2, s4);
		a3.addPrecedence(a2);
		Task a4 = new Task("Supplementary Requirements", 2, s2);
		a4.addPrecedence(a2);
		
		//Database Specification
		Task a5 = new Task("Conceptual Data Model", 1, s1);
		a5.addPrecedence(a4);
		Task a6 = new Task("Relational Schema, Validation and Schema Refinement", 1, s1);
		a6.addPrecedence(a5);
		Task a7 = new Task("Integrity Constraints, Indexes, Triggers and User Functions", 2, s6);
		a7.addPrecedence(a6);
		Task a8 = new Task("Database Populated with Data", 1, s3);
		a8.addPrecedence(a7);
		
		//Architecture Specification and Prototype
		Task a9 = new Task("High-level architecture. Privileges. Web resources specification", 1, s1);
		a9.addPrecedence(a8);
		Task a10 = new Task("Vertical Prototype", 2, s5);
		a10.addPrecedence(a8);
			

		//Project Creation
		project = new Project("LBAW - EduPoll", a1);
	}

	
	public void setupWorkers() {
		andre.addSkill(new Skill(s1, (float)0.9));
		andre.addSkill(new Skill(s2, (float)0.6));
		andre.addSkill(new Skill(s3, (float)0.7));
		//andre.addSkill(new Skill(s4, (float)0.7));
		//andre.addSkill(new Skill(s5, (float)0.7));
		andre.addSkill(new Skill(s6, (float)0.7));
		
		guilherme.addSkill(new Skill(s1, (float)0.8));
		guilherme.addSkill(new Skill(s2, (float)0.9));
		//guilherme.addSkill(new Skill(s3, (float)0.9));
		guilherme.addSkill(new Skill(s4, (float)0.9));
		guilherme.addSkill(new Skill(s5, (float)0.9));
		guilherme.addSkill(new Skill(s6, (float)0.8));
		
		gustavo.addSkill(new Skill(s1, (float)0.9));
		//gustavo.addSkill(new Skill(s2, (float)0.9));
		//gustavo.addSkill(new Skill(s3, (float)0.9));
		gustavo.addSkill(new Skill(s4, (float)0.6));
		gustavo.addSkill(new Skill(s5, (float)0.7));
		gustavo.addSkill(new Skill(s6, (float)0.9));
		
		pedro.addSkill(new Skill(s1, (float)0.7));
		pedro.addSkill(new Skill(s2, (float)1.0));
		pedro.addSkill(new Skill(s3, (float)0.5));
		//pedro.addSkill(new Skill(s4, (float)0.5));
		//pedro.addSkill(new Skill(s5, (float)0.5));
		//pedro.addSkill(new Skill(s6, (float)0.5));
		
	}
	
	
	/**
	 * Validates if there are any skills missing from the workers, to conclude the project (conclude every single task)
	 * @throws ProjectSetupException
	 */
	private void validateProject() throws ProjectSetupException {
		HashMap<Integer, Scope> scopes = new HashMap<Integer, Scope>();
		for (int i = 0; i < Worker.allWorkers.size(); i++) {
			HashMap<Integer,Skill> skills = Worker.allWorkers.get(i).getSkills();
			for (Entry<Integer, Skill> entries : skills.entrySet()) {
				scopes.put(entries.getKey(), entries.getValue().getScope());
			}
		}
		for (int i = 0; i < Task.allTasks.size(); i++) {
			Task task = Task.allTasks.get(i);
			if(scopes.get(task.getScope().getId()) == null)
				throw new ProjectSetupException(task.getScope());
		}
	}
}