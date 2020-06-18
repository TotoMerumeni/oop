package it.polito.oop.milliways;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Party {

	private Map<Race, Integer> companions;

	public Party() {
		this.companions = new HashMap<Race, Integer>();
	}

	public void addCompanions(Race race, int num) {
		if (companions.containsKey(race)) {
			companions.put(race, companions.get(race) + num);
		} else {
			companions.put(race, num);
		}
	}

	public int getNum() {
		return companions.values().stream().mapToInt(Integer::intValue).sum();
	}

	public int getNum(Race race) {
		return companions.get(race);
	}

	public List<String> getRequirements() {
		return companions.keySet().stream().flatMap(r -> r.getRequirements().stream()).distinct()
				.collect(Collectors.toList());
	}

	public Map<String, Integer> getDescription() {
		return companions.entrySet().stream().collect(Collectors.toMap(e -> e.getKey().getName(), e -> e.getValue()));
	}
	
	public  Map<Race, Integer> getCompanions() {
		return companions.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
	}
}
