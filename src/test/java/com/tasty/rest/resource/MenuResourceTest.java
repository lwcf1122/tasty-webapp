package com.tasty.rest.resource;

import com.tasty.rest.dto.Menu;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

/**
 * Created by flao on 5/26/15.
 */
@Slf4j
public class MenuResourceTest {

    private static Client client;
    protected static String BASEPATH;

    @BeforeSuite(alwaysRun = true)
    protected void init() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(JacksonFeature.class);
        client = ClientBuilder.newClient(clientConfig);
        BASEPATH = "http://localhost:8080/tasty-webservice/rest/";
    }

    @Test
    public void testMenusGet() {
        try {
            Menu menu = new Menu();
            menu.setName("Satay Noodles");
            JSONObject response = get("menus", "1");
            JSONObject jsonResponse = getJsonObject(response, "menu");
            log.info("Menus Get response:" + jsonResponse.toString());
            Assert.assertEquals(jsonResponse.getString("name"), menu.getName());
        } catch (Exception e) {
            log.error("error in testMenusGet", e);
            Assert.assertFalse(true);
        }
    }

    protected Response doGet(String entityType, String id) throws Exception {
        String getUrl  = BASEPATH + entityType + "/" + id;
        Invocation.Builder request = this.buildRequest(getUrl);
        log.info("get URL " + getUrl);
        Response response = request.get();
        log.info("response:" + response.toString());
        return response;
    }

    protected Invocation.Builder buildRequest(String target) throws Exception {
        return builRequestWithParams(target, Collections.<Long,String>emptyMap());
    }

    private Invocation.Builder builRequestWithParams(String target, Map<Long, String> parents) throws Exception {
        WebTarget webTarget = client.target(target);
        if (!parents.isEmpty()) {
            for (Long id : parents.keySet()) {
                webTarget = webTarget.queryParam(parents.get(id), id);
            }
        }
        Invocation.Builder request = webTarget.request(MediaType.APPLICATION_JSON);
        request.header("Accept", MediaType.APPLICATION_JSON);
        log.info("request:" + request.toString());
        return request;

    }

    protected void basicGetValidation(Response response) throws Exception {
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getStatus());
        Assert.assertEquals(response.getStatus(), 200);
    }

    protected JSONObject get(String entityType, String id) throws Exception {
        Response response = doGet(entityType,id);
        basicGetValidation(response);
        String responseString = IOUtils.toString((InputStream) response.getEntity());
        return new JSONObject(responseString);
    }

    protected JSONObject getJsonObject(JSONObject response, String name) throws JSONException {
        return response.getJSONObject(name);
    }
}
