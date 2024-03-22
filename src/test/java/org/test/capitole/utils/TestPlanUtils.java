package org.test.capitole.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.stream.Stream;

@Slf4j
public final class TestPlanUtils {

    ObjectMapper mapper = new ObjectMapper();
    private final Deque<StructPlan> successTestPlanDeque = new LinkedList<>();
    private final Deque<StructPlan> failsTestPlanDeque = new LinkedList<>();
    private final TestPlanHandler successTestPlanHandler = new TestPlanHandler(successTestPlanDeque);
    private final TestPlanHandler failsTestPlanHandler = new TestPlanHandler(failsTestPlanDeque);

    private TestPlanUtils() {
        throw new IllegalStateException("Must instantiate a class by constructor with filename parameter");
    }
    public TestPlanUtils(String jsonFileName) {
        try {
            ClassPathResource resource = new ClassPathResource(jsonFileName);
            JsonNode contentFile = mapper.readTree(resource.getInputStream());
            if(!contentFile.isEmpty()){
                loadTestPlan(contentFile);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTestPlan(JsonNode contentFile) {
        if(contentFile.has("success")){
            loadBlock().apply(contentFile.get("success"))
                    .flatMap(Arrays::stream)
                    .forEach(successTestPlanDeque::add);
        }
        if(contentFile.has("fails")) {
            loadBlock().apply(contentFile.get("fails"))
                    .flatMap(Arrays::stream)
                    .forEach(failsTestPlanDeque::add);
        }
    }

    public TestPlanHandler successTestPlan() {
        return successTestPlanHandler;
    }

    public TestPlanHandler failsTestPlan() {
        return failsTestPlanHandler;
    }

    private Function<JsonNode, Stream<StructPlan[]>> loadBlock() {
        return nodeBlock ->
            Stream.of(nodeBlock)
                    .map(JsonNode::toString)
                    .map(node -> {
                        try {
                            return mapper.readValue(node, StructPlan[].class);
                        } catch (JsonProcessingException e) {
                            log.error("Error parsing one block path of Json File", e);
                            throw new RuntimeException(e);
                        }
                    });
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class StructPlan {
        private String testName;
        private JsonNode request;
        private JsonNode response;
    }

    public static final class TestPlanHandler  {
        private final Deque<StructPlan> fifoQueue;
        private TestPlanHandler(Deque<StructPlan> fifoQueue) {
            this.fifoQueue = fifoQueue;
        }

        public StructPlan nextTest() {
            return fifoQueue.poll();
        }

        public boolean hasMoreTests() {
            return !fifoQueue.isEmpty();
        }
    }

}
