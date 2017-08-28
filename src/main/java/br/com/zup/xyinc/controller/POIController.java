package br.com.zup.xyinc.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.xyinc.model.POI;
import br.com.zup.xyinc.service.POIService;

@RestController
@RequestMapping("/poi")
public class POIController {

	@Autowired
	private POIService poiService;

	@GetMapping()
	public List<POI> list() {
		return poiService.findAll();
	}

	@PostMapping
	public ResponseEntity<POI> save(@Valid @RequestBody POI poi, HttpServletResponse response) {
		poiService.save(poi);
		return ResponseEntity.status(HttpStatus.CREATED).body(poi);
	}

	@GetMapping("/nearby/{coordX}/{coordY}/{maxDistance}")
	public ResponseEntity<List<POI>> findNearby(@PathVariable Integer coordX, @PathVariable Integer coordY, @PathVariable Integer maxDistance) {
		List<POI> pois = poiService.findNearby(coordX, coordY, maxDistance);
		return pois != null ? ResponseEntity.ok(pois) : ResponseEntity.notFound().build();
	}
}