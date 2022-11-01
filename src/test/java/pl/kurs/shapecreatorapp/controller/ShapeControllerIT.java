package pl.kurs.shapecreatorapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.kurs.shapecreatorapp.exception.BadQuantityOfParametersException;
import pl.kurs.shapecreatorapp.factory.creators.ShapeFactory;
import pl.kurs.shapecreatorapp.model.Shape;
import pl.kurs.shapecreatorapp.model.Square;
import pl.kurs.shapecreatorapp.repository.ShapeRepository;
import pl.kurs.shapecreatorapp.service.ShapeServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.profiles.active=test")
class ShapeControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ShapeServiceImpl shapeServiceImpl;
    @Autowired
    ShapeFactory shapeFactory;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ShapeRepository mockShapeRepository;

    @BeforeEach
    public void init() {
        mockShapeRepository.deleteAll();
    }

    @WithMockUser(username = "creator", roles = {"CREATOR"})
    @Test
    void shouldAddShape() throws Exception {

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("type", "SQUARE");
        jsonObj.put("parameters", List.of(2.0));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/shapes")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObj.toString()))
                .andExpect(status().isCreated());

        Shape newShapeToAdd = new Square("SQUARE", 2.0);
        newShapeToAdd.setId(1);
        newShapeToAdd.setVersion(0);
        newShapeToAdd.setCreatedBy("creator");
        newShapeToAdd.setCreatedAt(LocalDateTime.of(2022, 10, 10, 10, 10, 10));
        newShapeToAdd.setLastModifiedAt(LocalDateTime.of(2022, 10, 10, 10, 10, 10));
        newShapeToAdd.setLastModifiedBy("creator");
        newShapeToAdd.calculateArea();
        newShapeToAdd.calculatePerimeter();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/shapes/1"))
                .andExpect(jsonPath("$.width").value(2.0))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @WithMockUser(username = "creator", roles = {"CREATOR"})
    @Test
    void shouldReturnTYPE_IS_NOT_VALID() throws Exception {

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("type", "SQUAgRE");
        jsonObj.put("parameters", List.of(2.0));

        String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObj.toString()))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(response.contains("\"code\":\"TYPE_IS_NOT_VALID\",\"field\":\"type\""));
        assertEquals(shapeServiceImpl.findAll().size(), 0);
    }

    @WithMockUser(username = "creator", roles = {"CREATOR"})
    @Test
    void shouldReturnTYPE_IS_EMPTYAndTYPE_IS_NOT_VALID() throws Exception {

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("type", "");
        jsonObj.put("parameters", List.of(2.0));

        String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObj.toString()))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(response.contains("\"code\":\"TYPE_IS_EMPTY\",\"field\":\"type\""));
        assertTrue(response.contains("\"code\":\"TYPE_IS_NOT_VALID\",\"field\":\"type\""));
        assertEquals(shapeServiceImpl.findAll().size(), 0);
    }

    @WithMockUser(username = "creator", roles = {"CREATOR"})
    @Test
    void shouldReturnBadRequestWhenShapeParametersAreEmptyOrNull() throws Exception {

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("type", "CIRCLE");
        jsonObj.put("parameters", null);

        String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObj.toString()))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(response.contains("\"code\":\"PARAMETERS_NOT_EMPTY\",\"field\":\"parameters\""));
        assertEquals(shapeServiceImpl.findAll().size(), 0);
    }

    @WithMockUser(username = "creator", roles = {"CREATOR"})
    @Test
    void shouldReturnBadRequestWhenShapeParametersAreNegative() throws Exception {

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("type", "CIRCLE");
        jsonObj.put("parameters", List.of(-2.0));

        String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObj.toString()))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(response.contains("\"code\":\"PARAMETERS_NOT_POSITIVE\",\"field\":\"parameters\""));
        assertEquals(shapeServiceImpl.findAll().size(), 0);
    }

    @WithMockUser(username = "creator", roles = {"CREATOR"})
    @Test
    void shouldReturnBadRequestWhenShapeHasBadQuantityOdParameters() throws Exception {

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("type", "CIRCLE");
        jsonObj.put("parameters", List.of(2.0, 4.0));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObj.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadQuantityOfParametersException))
                .andExpect(result -> assertEquals("BAD_QUANTITY_OF_PARAMETERS", result.getResolvedException().getMessage()));

        assertEquals(shapeServiceImpl.findAll().size(), 0);
    }

}