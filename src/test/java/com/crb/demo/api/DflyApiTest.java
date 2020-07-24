package com.crb.demo.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;

public class DflyApiTest {

    Logger log = LoggerFactory.getLogger(DflyApiTest.class);

    /*@Test
    public void TaskNotificationTest() {
        String message = "{\"taskExecution\":{\"taskDefI\n" +
                "d\":21,\"context\":\"{\\\"entranceId\\\":\\\"fdaae40d-81df-47e8-81aa-01cbfd37fa36\\\",\\\"resourcePath\\\":\\\"/project/SYSTEM/task/crb_entrance_01\\\",\\\"resourceId\\\":\\\"9aba205e-77fb-4849-9339-4d4a249\n" +
                "e6470\\\",\\\"deployId\\\":\\\"DEPLOY-20200320-1435-11\\\",\\\"archived\\\":false,\\\"triggerEvent\\\":\\\"ADHOC://DFLY\\\",\\\"label\\\":\\\"crb_entrance_01\\\",\\\"creator\\\":{\\\"id\\\":\\\"chenruibin\\\",\\\"name\\\":\\\"\n" +
                "瑞斌\\\"},\\\"createdDate\\\":\\\"Mar 20, 2020 2:35:23 PM\\\",\\\"lastModifiedDate\\\":\\\"Mar 20, 2020 2:35:23 PM\\\",\\\"id\\\":\\\"621676a3-4157-4335-9eca-3907ee26dbdc\\\"}\",\"taskTrigger\":\"MANUAL\"},\"t\n" +
                "askInvokeStatus\":\"ACCEPTED\",\"taskNode\":\"d3\",\"taskInstanceId\":\"303ea1ff-e815-47e7-8882-3833704d5c4d\",\"taskStatus\":\"PENDING\"}";

        Gson gson = new Gson();
        TaskNotification taskNotification = gson.fromJson(message, TaskNotification.class);
        Map<String,String> context = gson.fromJson(taskNotification.getTaskExecution().getContext(), Map.class);
        log.info("taskNotification -> {}", taskNotification);
        String taskNode = taskNotification.getTaskNode();
        String taskInstanceId = taskNotification.getTaskInstanceId();
        TaskStatus taskStatus = taskNotification.getTaskStatus();
        String deployId = context.get("deployId");

        log.info("{}, {}, {}, {}", taskNode, taskInstanceId, taskStatus, deployId);
    }*/

}
