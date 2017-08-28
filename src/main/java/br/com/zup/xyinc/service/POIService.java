package br.com.zup.xyinc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.xyinc.model.POI;
import br.com.zup.xyinc.repository.IPOIRepository;

@Service
public class POIService {

	@Autowired
	private IPOIRepository repository;

	public List<POI> findNearby(int coordX, int coordY, int maxDistance) {
		return findAll().stream().filter(item -> 
			Math.sqrt(
				Math.pow((item.getCoordX() - coordX), 2) + Math.pow((item.getCoordY() - coordY), 2)) <= maxDistance)
				.collect(Collectors.toList());
	}

	public List<POI> findAll() {
		return repository.findAll();
	}

	public POI save(POI poi) {
		return repository.save(poi);
	}
}