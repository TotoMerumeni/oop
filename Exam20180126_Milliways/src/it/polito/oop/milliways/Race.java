package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.List; 
import java.util.stream.Collectors;

public class Race {
    
	private String name;
	private List<String> requirements;
	
	public Race(String name) {
		this.name = name;
		requirements = new ArrayList<String>();
	}
	
	public void addRequirement(String requirement) throws MilliwaysException {
		if (requirements.contains(requirement)) {
			throw new MilliwaysException();
		}
		requirements.add(requirement);
	}
	
	public List<String> getRequirements() {
        return requirements.stream().sorted().collect(Collectors.toList());
	}
	
	public String getName() {
        return name;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
}
