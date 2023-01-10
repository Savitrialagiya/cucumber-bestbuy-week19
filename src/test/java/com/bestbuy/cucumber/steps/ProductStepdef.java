package com.bestbuy.cucumber.steps;

import com.bestbuy.steps.ProductSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

public class ProductStepdef {
    static String name = "Battery-" + TestUtils.getRandomValue();
    static String type = "Chargable";
    static int price = 10;
    static int shipping = 25;
    static String upc = "012345";
    static String description = "Compatible with select electronic devices";
    static String manufacture = "Duracell";
    static String model = "MN2400B4Z";
    static String url = "http://www.bestbuy.com";
    static String image = "http://img.bbystatic.com";
    static int productId;
    static ValidatableResponse response;

    @Steps
    ProductSteps productSteps;

    @When("^As a User I create a product$")
    public void asAUserICreateAProduct() {
        response = productSteps.addProduct(name, type, price, shipping, upc, description, manufacture, model, url, image);
        productId = (int) response.extract().path("id");
        System.out.println(productId);
    }

    @Then("^User must get back a valid response 201$")
    public void userMustGetBackAValidResponse() {
        response.statusCode(201);

    }

    @When("^I get product with productId$")
    public void iGetProductWithProductId() {
        response=productSteps.getProductById(productId);
    }

    @Then("^I must get back a valid response 200$")
    public void iMustGetBackAValidResponse() {
        response.statusCode(200);
    }

    @When("^I update product with productId$")
    public void iUpdateProductWithProductId() {
        type="added_"+type;
        manufacture=manufacture+"_added";
        response=productSteps.updateProduct(productId,name,type,price,shipping,upc,description,manufacture,model,url,image);
    }

    @Then("^I must get back a again valid response 200$")
    public void iMustGetBackAAgainValidResponse() {
        response.statusCode(200);
    }

    @When("^I delete product with productId$")
    public void iDeleteProductWithProductId() {
        response=productSteps.deleteProduct(productId).statusCode(200);
    }


    @Then("^I check product is deleted$")
    public void iCheckProductIsDeleted() {
        response=productSteps.getProductById(productId).statusCode(404);
    }
}
