package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.*;

public class Restaurant {

	private TreeMap<String, Race> races = new TreeMap<String, Race>();
	private List<Party> parties = new ArrayList<>();
	private HashMap<Integer, Hall> halls = new HashMap<Integer, Hall>();

	public Restaurant() {
	}

	public Race defineRace(String name) throws MilliwaysException {
		if (races.keySet().contains(name)) {
			throw new MilliwaysException();
		}
		Race r = new Race(name);
		races.put(name, r);
		return r;
	}

	public Party createParty() {
		Party p = new Party();
		parties.add(p);
		return p;
	}

	public Hall defineHall(int id) throws MilliwaysException {
		if (halls.containsKey(id)) {
			throw new MilliwaysException();
		}
		Hall h = new Hall(id);
		halls.put(id, h);
		return h;
	}

	public List<Hall> getHallList() {
		return halls.values().stream().collect(toList());
	}

	public Hall seat(Party party, Hall hall) throws MilliwaysException {
		if (!hall.isSuitable(party)) {
			throw new MilliwaysException();
		}
		hall.addParty(party);
		return hall;
	}

	public Hall seat(Party party) throws MilliwaysException {
		Hall hall = halls.values().stream().filter(h -> h.isSuitable(party)).findFirst().orElse(null);
		if (hall == null) {
			throw new MilliwaysException();
		}
		hall.addParty(party);
		return hall;
	}

	public Map<Race, Integer> statComposition() {
		return halls.values().stream()
				.flatMap(h -> h.getCompanions().entrySet().stream())
				.collect(groupingBy(
						e -> e.getKey(),
						HashMap::new,
						summingInt(e -> e.getValue())));
	}

	public List<String> statFacility() {
		return null;
	}

	public Map<Integer, List<Integer>> statHalls() {
		return null;
	}

}
