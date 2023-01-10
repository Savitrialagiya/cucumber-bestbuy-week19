package com.bestbuy.crudtests;

import com.bestbuy.steps.StoreSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.PropertyReader;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class StoreCRUDTest extends TestBase {

    static String name = "NV";
    static String type = "SmallBox";
    static String address = "253 st";
    static String address2 = "spenc road";
    static String city = "Mumbai";
    static String state = "Maharastra";
    static String zip = "097";
    static int lat = 156;
    static int lng = 12;
    static String hours = "1";
    static HashMap<String, String> services;
    static int storeId;

    @Steps
    StoreSteps storeSteps;

    @Title("This will create new  store")
    @Test
    public void test001() {
        //HashMap<?,?> service=new HashMap<>();
        // services.put(" ","str");
        ValidatableResponse response = storeSteps.addStore(name, type, address, address2, city, state, zip, lat, lng, hours);
        response.log().all().statusCode(201);
        storeId=response.extract().path("id");
        System.out.println(storeId);
    }

    @Title("Getting the store information with Name:")
    @Test
    public void test002() {
        ValidatableResponse response=storeSteps.getStoreById(storeId).statusCode(200);
//        HashMap<String, Object> storeMap = storeSteps.getStoreByName(name);
//        Assert.assertThat(storeMap, hasValue(name));

    }

    @Title("Updating store information and verify the updated information")
    @Test
    public void test003() {
        name = name + ("_added");
        storeSteps.updateStoreByName(storeId, name, type, address, address2, city, state, zip, lat, lng, hours).log().all().statusCode(200);

//        HashMap<String, Object> storeMap = storeSteps.getStoreByName(name);
//        Assert.assertThat(storeMap, hasValue(name));
    }

    @Title("Deleting store with id and verify that user is deleted")
    @Test
    public void test004() {
        storeSteps.deleteStore(storeId).statusCode(200);
        storeSteps.getStoreById(storeId).statusCode(404);

    }
}
