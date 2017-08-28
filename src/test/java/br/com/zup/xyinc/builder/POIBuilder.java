package br.com.zup.xyinc.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.zup.xyinc.model.POI;

public final class POIBuilder {

	public static POI build() {
		List<POI> pois = buildAll();
		return pois.get(new Random().nextInt(pois.size()));
	}

	public static List<POI> buildAll() {
		List<POI> pois = new ArrayList<>();
		pois.add(new POI("Lanchonete", 27, 12));
		pois.add(new POI("Posto", 31, 18));
		pois.add(new POI("Joalheria", 15, 12));
		pois.add(new POI("Floricultura", 19, 21));
		pois.add(new POI("Pub", 12, 8));
		pois.add(new POI("Supermercado", 23, 6));
		pois.add(new POI("Churrascaria", 28, 2));
		return pois;
	}
}