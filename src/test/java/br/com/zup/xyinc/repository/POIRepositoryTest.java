package br.com.zup.xyinc.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.zup.xyinc.XyincApplicationTests;
import br.com.zup.xyinc.builder.POIBuilder;
import br.com.zup.xyinc.model.POI;

public class POIRepositoryTest extends XyincApplicationTests {

	@Autowired
	private IPOIRepository poiRepository;

	private POI poi;

	@Before
	public void init() {
		poi = POIBuilder.build();
		poiRepository.save(poi);
	}

	@After
	public void finish() {
		poiRepository.deleteAll();
	}

	@Test
	public void saveTest() {		
		Assert.assertNotNull(poi.getId());
	}

	@Test
	public void findAllTest() {
		Assert.assertTrue(!poiRepository.findAll().isEmpty());
	}
}