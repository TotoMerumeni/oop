package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.*;

public class Hall {

	private int id;
	private List<String> facilities = new ArrayList<>();
	private List<Party> parties = new ArrayList<>();

	public Hall(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void addFacility(String facility) throws MilliwaysException {
		if (facilities.contains(facility)) {
			throw new MilliwaysException();
		}
		facilities.add(facility);
	}

	public List<String> getFacilities() {
		return facilities;
	}

	int getNumFacilities() {
		return facilities.size();
	}

	public boolean isSuitable(Party party) {
		for (String string : party.getRequirements()) {
			if (!facilities.contains(string)) {
				return false;
			}
		}
		return true;
	}

	public void addParty(Party p) {
		parties.add(p);
	}

	public Map<Race, Integer> getCompanions() {
		Map<Race, Integer> tmp = parties.stream().flatMap(p -> p.getCompanions().entrySet().stream()).collect(toMap(Entry::getKey, Entry::getValue));
		return tmp;
	}
}
