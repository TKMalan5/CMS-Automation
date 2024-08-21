package com.qa.testrailmanager;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;
import org.openqa.selenium.remote.Browser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestRailManager {

    public static String TEST_RUN_ID = "5";
    public static String TEST_RAIL_USERNAME = "taniamalan81@gmail.com";
    public static String TEST_RAIL_PASSWORD = "H#!3d!UP4DcicsJ";

    public static String TEST_RAIL_ENGINE_URL = "https://taniamalan.testrail.io/";

    public static int TEST_CASE_PASS_STATUS = 1;
    public static int TEST_CASE_FAIL_STATUS = 5;

    public static void addResultsForTestCase(String testCaseId, int status, String error) {

        String testRunID = TEST_RUN_ID;
        APIClient client = new APIClient(TEST_RAIL_ENGINE_URL);
        client.setUser(TEST_RAIL_USERNAME);
        client.setPassword(TEST_RAIL_PASSWORD);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("status_id", status);
        data.put("comment", "This test is executed via selenium automation" + error);


        try {
            // Log the request details
            System.out.println("Sending API request to: add_result_for_case/" + testRunID + "/" + testCaseId);
            System.out.println("Request data: " + data);

            // Send the request
            client.sendPost("add_result_for_case/" + testRunID + "/" + testCaseId, data);

            System.out.println("Result sent successfully.");
        } catch (IOException | APIException e) {
            // Log the error details
            System.err.println("Failed to send result to TestRail.");
            System.err.println("Error message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}




