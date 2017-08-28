package br.com.zup.xyinc.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.xyinc.XyincApplicationTests;
import br.com.zup.xyinc.builder.POIBuilder;
import br.com.zup.xyinc.model.POI;
import br.com.zup.xyinc.repository.IPOIRepository;
import br.com.zup.xyinc.service.POIService;

public class POIControllerTest extends XyincApplicationTests {

	private MockMvc mockMvc;

	@Mock
	private IPOIRepository poiRepository;

	@Mock
	private POIService poiServiceMock;

	@InjectMocks
	private POIService poiService;

	@InjectMocks
	private POIController poiController;

	private final String BASE_PATH = "http://localhost:8081/poi";

	private ObjectMapper MAPPER = new ObjectMapper();

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(poiController).build();
	}

	@Test
	public void saveTest() {

		POIBuilder.buildAll().forEach(poi -> {
			try {
				this.mockMvc
						.perform(post(BASE_PATH).contentType(MediaType.APPLICATION_JSON_UTF8).content(
								MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(POIBuilder.build())))
						.andExpect(status().is2xxSuccessful());
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

	@Test
	public void findAllTest() throws Exception {
		Mockito.when(poiServiceMock.findAll()).thenReturn(POIBuilder.buildAll());

		mockMvc.perform(get(BASE_PATH)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$", hasSize(7)));
	}

	@Test
	public void findNearbyTest() throws Exception {
		Mockito.when(poiRepository.findAll()).thenReturn(POIBuilder.buildAll());
		List<POI> listPoi = poiService.findNearby(20, 10, 10);
		Mockito.when(poiServiceMock.findNearby(20, 10, 10)).thenReturn(listPoi);

		mockMvc.perform(get(BASE_PATH + "/nearby/20/10/10")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$", hasSize(4)));

	}

	@Test
	public void saveBadRequestTest() throws JsonProcessingException, Exception {
		POI poi = new POI("Negativo", 1, -1);
		this.mockMvc
				.perform(post(BASE_PATH).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(poi)))
				.andExpect(status().is4xxClientError());
	}
}