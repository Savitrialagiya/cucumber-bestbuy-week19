package com.bestbuy.cucumber.steps;

import com.bestbuy.steps.StoreSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

public class StoreStepdef {
    static String name = "MV"+ TestUtils.getRandomValue();
    static String type = "SmallBox";
    static String address = "12 Str";
    static String address2 = "road";
    static String city = "Mumbai";
    static String state = "Maharashtra";
    static String zip = "0097";
    static int lat = 189;
    static int lng = 85;
    static String hours = "3";
    static int storeId;

    ValidatableResponse response;
    @Steps
    StoreSteps storeSteps;
    @When("^I create a store$")
    public void iCreateAStore() {
        response=storeSteps.addStore(name,type,address,address2,city,state,zip,lat,lng,hours);
        storeId=(int) response.extract().path("id");
    }

    @Then("^I must get a valid response 201$")
    public void iMustGetAValidResponse() {
        response.statusCode(201);
    }

    @When("^I get store with storeId$")
    public void iGetStoreWithStoreId() {
        response=storeSteps.getStoreById(storeId);
    }

    @Then("^I must get valid response 200$")
    public void iMustGetValidResponse() {
        response.statusCode(200);
    }

    @When("^I update store with storeId$")
    public void iUpdateStoreWithStoreId() {
        address="spons road";
        response=storeSteps.updateStoreByName(storeId,name,type,address,address2,city,state,zip,lat,lng,hours);
    }

    @When("^I delete store with storeId$")
    public void iDeleteStoreWithStoreId() {
        response=storeSteps.deleteStore(storeId).statusCode(200);
    }

    @Then("^I check store is deleted$")
    public void iCheckStoreIsDeleted() {
        response=storeSteps.getStoreById(storeId).statusCode(404);
    }
}
