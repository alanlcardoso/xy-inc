package br.com.zup.xyinc.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.zup.xyinc.XyincApplicationTests;
import br.com.zup.xyinc.builder.POIBuilder;
import br.com.zup.xyinc.model.POI;
import br.com.zup.xyinc.repository.IPOIRepository;

public class POIServiceTest extends XyincApplicationTests {

	@Mock
	private IPOIRepository repository;

	@InjectMocks
	private POIService service;

	@Test
	public void saveTest() {
		POI poi = POIBuilder.build();
		Mockito.when(service.save(poi)).thenReturn(poi);
		service.save(poi);
		Assert.assertNotNull(poi);
	}

	@Test
	public void findAllTest() {
		List<POI> pois = POIBuilder.buildAll();
		Mockito.when(service.findAll()).thenReturn(pois);
		Assert.assertNotNull(pois);
	}

	@Test
	public void findNearbyTest() {

		List<POI> pois = POIBuilder.buildAll();
		Mockito.when(service.findAll()).thenReturn(pois);

		List<POI> poisNearby = service.findNearby(20, 10, 10);

		Assert.assertTrue(poisNearby.size() == 4);
		Assert.assertEquals(poisNearby.get(0), pois.get(0));
		Assert.assertEquals(poisNearby.get(1), pois.get(2));
		Assert.assertEquals(poisNearby.get(2), pois.get(4));
		Assert.assertEquals(poisNearby.get(3), pois.get(5));
	}
}