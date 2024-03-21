package org.test.capitole.executions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.test.capitole.MsProductManagementApplication;
import org.test.capitole.infrastructure.adapter.in.controller.PriceController;
import org.test.capitole.utils.TestPlanUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = MsProductManagementApplication.class)
@AutoConfigureMockMvc
class TestExecution {

    @Autowired
    private PriceController priceController;
    private final ObjectMapper mapper = new ObjectMapper();
    private final TestPlanUtils testPlanUtils = new TestPlanUtils("plan-test.json");

    @Test
    void testGetPriceToApply() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        // Get queue of test cases plan
        while(testPlanUtils.successTestPlan().hasMoreTests()){
            var structPlan = testPlanUtils.successTestPlan().nextTest();
            log.info("\n>>>>>>>>>>>>>>>>>>>>\n>>>>> TEST CASE NAME \n>>>>> {}\n>>>>>>>>>>>>>>>>>>>>", structPlan.getTestName());

            // Arrange
            Integer brandId = structPlan.getRequest().get("brandId").asInt();
            Long productId = structPlan.getRequest().get("productId").asLong();
            LocalDateTime effectiveDate = LocalDateTime.parse(structPlan.getRequest().get("effectiveDate").asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            var expectedResponse = structPlan.getResponse().toString();

            // Act and Assert
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get("/api/v1/products/{productId}/brand/{brandId}/price-to-apply", productId, brandId)
                    .param("effectiveDate", effectiveDate.toString());
            var result = MockMvcBuilders.standaloneSetup(priceController)
                    .build()
                    .perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(MockMvcResultMatchers.content().string(expectedResponse));
        }
    }
}
