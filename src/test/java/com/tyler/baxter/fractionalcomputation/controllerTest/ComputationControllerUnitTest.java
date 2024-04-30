package com.tyler.baxter.fractionalcomputation.controllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.tyler.baxter.fractionalcomputation.controller.ComputationController;
import com.tyler.baxter.fractionalcomputation.service.ComputationService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ComputationController.class)
public class ComputationControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ComputationService computationService;

	@Test
  public void testAdd_200() throws Exception {
    when(computationService.add(5.0, 3.0)).thenReturn(ResponseEntity.ok(8.0));

    mockMvc
        .perform(MockMvcRequestBuilders.get("/calculator/add?x=5.0&y=3.0"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(content().string("8.0"));
  }

	@Test
  public void testSubtract_200() throws Exception {
    when(computationService.subtract(5.0, 3.0)).thenReturn(ResponseEntity.ok(2.0));

    mockMvc
        .perform(MockMvcRequestBuilders.get("/calculator/subtract?x=5.0&y=3.0"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(content().string("2.0"));
  }

	@Test
  public void testMultiply_200() throws Exception {
    when(computationService.multiply(5.0, 3.0)).thenReturn(ResponseEntity.ok(15.0));

    mockMvc
        .perform(MockMvcRequestBuilders.get("/calculator/multiply?x=5.0&y=3.0"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(content().string("15.0"));
  }

	@Test
  public void testDivide_200() throws Exception {
    when(computationService.divide(6.0, 3.0)).thenReturn(ResponseEntity.ok(2.0));

    mockMvc
        .perform(MockMvcRequestBuilders.get("/calculator/divide?x=6.0&y=3.0"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(content().string("2.0"));
  }

	@Test
  public void testPower_200() throws Exception {
    when(computationService.power(2.0, 2.0)).thenReturn(ResponseEntity.ok(4.0));

    mockMvc
        .perform(MockMvcRequestBuilders.get("/calculator/power?x=2.0&y=2.0"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(content().string("4.0"));
  }

	@Test
  public void testSqrt_200() throws Exception {
    when(computationService.sqrt(4.0)).thenReturn(ResponseEntity.ok(2.0));

    mockMvc
        .perform(MockMvcRequestBuilders.get("/calculator/sqrt?x=4.0"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(content().string("2.0"));
  }
}
