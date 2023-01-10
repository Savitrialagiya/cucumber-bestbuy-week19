package com.bestbuy.crudtests;


import com.bestbuy.steps.ProductSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.PropertyReader;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;


@Concurrent(threads = "8X")
@UseTestDataFrom("src/test/java/resources/testdata/product.csv")
@RunWith(SerenityParameterizedRunner.class)
public class DataDrivenTest extends TestBase {
    private String name;
    private String type;
    private int price;
    private int shIpping;
    private String upc;
    private String description;
    private String manufacturer;
    private String model;
    private String url;
    private String image;

    @Steps
    ProductSteps productSteps;

    @Title("Creating multiple products with Data driven")
    @Test
    public void createMultipleProduct() {
        productSteps.addProduct(name, type, price, shIpping, upc, description, manufacturer, model, url, image).statusCode(201);
    }

}
