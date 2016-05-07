package utils;
import java.util.HashMap;
import java.util.Map.Entry;

import algorithms.GA.GeneticAlgorithm;
import vars.Project;
import vars.Scope;
import vars.Skill;
import vars.Task;
import vars.Worker;

/**
 * Created by Guilherme on 12/04/2016.
 */
public class Main {
	
	//Defining all the possible required scopes
	private static Scope s1 = new Scope("Arquitetura de Bases de Dados");
	private static Scope s2 = new Scope("Documentação");
	private static Scope s3 = new Scope("Divulgação");
	private static Scope s4 = new Scope("Programação HTML/CSS");
	private static Scope s5 = new Scope("Programação PHP");
	private static Scope s6 = new Scope("Programação PostgreSQL");
	
	private static Worker andre = new Worker("André Lago");
	private static Worker guilherme = new Worker("Guilherme Pinto");
	private static Worker gustavo = new Worker("Gustavo Silva");
	private static Worker pedro = new Worker("Pedro Castro");
	
	public static Project project;
	
	public static void main(String[] args) {
		setupProject();
		setupWorkers();
		
		try  {
			validateProject();
		}
		catch (ProjectSetupException e) {
			System.out.println(e);
			System.exit(1);
		}
		finally {
			System.out.println("Project setup successfull!");
		}
		
		new GeneticAlgorithm(10, 10);
	}

	private static void setupWorkers() {
		andre.addSkill(new Skill(s1, 0.9));
		andre.addSkill(new Skill(s3, 0.7));
		guilherme.addSkill(new Skill(s5, 0.8));
		gustavo.addSkill(new Skill(s4, 0.6));
		gustavo.addSkill(new Skill(s5, 0.7));
		gustavo.addSkill(new Skill(s6, 0.9));
		pedro.addSkill(new Skill(s2, 1.0));
			
		
		project.addWorker(andre);
		project.addWorker(guilherme);
		project.addWorker(gustavo);
		project.addWorker(pedro);
		
	}

	private static void setupProject() {
		//Project Creation
		project = new Project("LBAW - EduPoll");
		
		//Defining Tasks and respective precedences
		//User Requirements Specification
		Task a1 = new Task("Project Presentation", 1, s3);
		Task a2 = new Task("Actors and User Stories", 1, s2);
		Task a3 = new Task("User Interfaces Prototypes", 2, s4);
		Task a4 = new Task("Supplementary Requirements", 2, s2);
		a4.addPrecedence(a2);
		
		//Database Specification
		Task a5 = new Task("Conceptual Data Model", 1, s1);
		Task a6 = new Task("Relational Schema, Validation and Schema Refinement", 1, s1);
		a6.addPrecedence(a5);
		Task a7 = new Task("Integrity Constraints, Indexes, Triggers and User Functions", 2, s6);
		a7.addPrecedence(a6);
		Task a8 = new Task("Database Populated with Data", 1, s3);
		a8.addPrecedence(a7);
		
		//Architecture Specification and Prototype
		Task a9 = new Task("High-level architecture. Privileges. Web resources specification", 1, s1);
		Task a10 = new Task("Vertical Prototype", 2, s5);
			
		
		project.addTask(a1);
		project.addTask(a2);
		project.addTask(a3);
		project.addTask(a4);
		project.addTask(a5);
		project.addTask(a6);
		project.addTask(a7);
		project.addTask(a8);
		project.addTask(a9);
		project.addTask(a10);
		
	}

	private static void validateProject() throws ProjectSetupException {
		
		HashMap<Integer, Scope> scopes = new HashMap<Integer, Scope>();
		for (int i = 0; i < project.getWorkers().size(); i++) {
			HashMap<Integer,Skill> skills = project.getWorkers().get(i).getSkills();
			for (Entry<Integer, Skill> entries : skills.entrySet()) {
				scopes.put(entries.getKey(), entries.getValue().getScope());
			}
		}
		for (int i = 0; i < project.getTasks().size(); i++) {
			Task task = project.getTasks().get(i);
			if(scopes.get(task.getScope().getId()) == null)
				throw new ProjectSetupException(task.getScope());
		}
		
		
	}
}