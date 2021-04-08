package com.example.hoover;

import com.example.hoover.request.HooverRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HooverEntityApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void test_Input_From_Example() throws Exception {
		HooverRequest hooverRequest = createHooverRequest(new Integer[]{5,5},new Integer[]{1,2},new Integer[][]{{1, 0},{2, 2},{2, 3}},"NNESEESWNWW");

		callService(hooverRequest,"{\"coords\":[1,3],\"patches\":1}");
	}

	@Test
	public void test_Patch_At_The_End_Of_Vacuum() throws Exception {
		HooverRequest hooverRequest = createHooverRequest(new Integer[]{3,4},new Integer[]{1,2},new Integer[][]{{1, 4},{2, 2}},"NEEEESWW");

		callService(hooverRequest,"{\"coords\":[1,2],\"patches\":1}");
	}

	@Test
	public void test_Cleaned_No_Patches() throws Exception {
		HooverRequest hooverRequest = createHooverRequest(new Integer[]{3,4},new Integer[]{1,2},new Integer[][]{{1, 4},{2, 2}},"NEEEESSWW");
		callService(hooverRequest,"{\"coords\":[1,1],\"patches\":0}");
	}

	@Test
	public void test_Cleaned_All_Patches() throws Exception {
		HooverRequest hooverRequest = createHooverRequest(new Integer[]{3,4},new Integer[]{1,2},new Integer[][]{{1, 4},{1,3},{2, 2}},"NEEEESWWNNNN");
		callService(hooverRequest,"{\"coords\":[1,4],\"patches\":3}");
	}
	@Test
	public void test_Cleaned_Two_Patches() throws Exception {
		HooverRequest hooverRequest = createHooverRequest(new Integer[]{3,4},new Integer[]{1,2},new Integer[][]{{1, 4},{1,3},{2, 2}},"NEEEESWWN");
		callService(hooverRequest,"{\"coords\":[1,3],\"patches\":2}");
	}

	@Test
	public void test_Error_Patches_Not_Within_Coordinates() throws Exception {
		HooverRequest hooverRequest = createHooverRequest(new Integer[]{3,4},new Integer[]{1,2},new Integer[][]{{1, 4},{1,5},{2, 2}},"NEEEESWWNNNN");
		callService(hooverRequest,"{\"errors\":[\"Value for spots coordinates are not within room coordinates\"]}");
	}
	@Test
	public void test_Error_Coordinates_Are_Not_Valid() throws Exception {
		HooverRequest hooverRequest = createHooverRequest(new Integer[]{3,4},new Integer[]{1,2},new Integer[][]{{1, 4},{1,5},{2, 2}},"ANEEEESWWNNNN");
		callService(hooverRequest,"{\"errors\":[\"Value for coordinates should be N/W/E/S\"]}");
	}

	@Test
	public void test_Error_Value_For_Vacuum_Coordinates_Not_Within_Room() throws Exception {
		HooverRequest hooverRequest = createHooverRequest(new Integer[]{3,4},new Integer[]{1,6},new Integer[][]{{1, 4},{1,3},{2, 2}},"NEEEESWWNNNN");
		callService(hooverRequest,"{\"errors\":[\"Value for vacuum coordinates are not within room coordinates\"]}");
	}

	private void callService(HooverRequest hooverRequest,String expectedContent) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson=ow.writeValueAsString(hooverRequest );

		mockMvc.perform(MockMvcRequestBuilders.get("/getPatchesCleanedNumber")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().string(expectedContent));

	}

	private HooverRequest createHooverRequest(Integer[] roomSize,Integer[] coords,Integer[][] patches,String instructions){
		HooverRequest hooverRequest = new HooverRequest();
		hooverRequest.setVacuumCoordinates(coords);
		hooverRequest.setInstructions(instructions);
		hooverRequest.setRoomSize(roomSize);
		hooverRequest.setSpotsCoordinates(patches);
		return hooverRequest;
	}
}
