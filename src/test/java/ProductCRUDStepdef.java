import com.bestbuy.steps.ProductSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

public class ProductCRUDStepdef {
    @Steps
    ProductSteps productSteps;
    static ValidatableResponse response;

    static int productId;
    static String name1="Durecell_"+ TestUtils.getRandomValue();
    @When("^I create product with name \"([^\"]*)\" type \"([^\"]*)\" price\"([^\"]*)\" shipping\"([^\"]*)\" upc\"([^\"]*)\" description\"([^\"]*)\" manufacture\"([^\"]*)\" model\"([^\"]*)\" url\"([^\"]*)\" image\"([^\"]*)\"$")
    public void iCreateProductWithNameTypePriceShippingUpcDescriptionManufactureModelUrlImage(String name, String type, int price, int shipping , String upc, String description , String manufacture, String model, String url, String image) {
        response=productSteps.addProduct(name1,type,price,shipping,upc,description,manufacture,model,url,image);
        productId=(int) response.extract().path("id");
    }

    @And("^I verify user created$")
    public void iVerifyUserCreated() {
        response.statusCode(201);
    }

    @And("^I update product with name \"([^\"]*)\" type \"([^\"]*)\" price\"([^\"]*)\" shipping\"([^\"]*)\" upc\"([^\"]*)\" description\"([^\"]*)\" manufacture\"([^\"]*)\" model\"([^\"]*)\" url\"([^\"]*)\" image\"([^\"]*)\"$")
    public void iUpdateProductWithNameTypePriceShippingUpcDescriptionManufactureModelUrlImage(String name, String type, int price, int shipping , String upc, String description , String manufacture, String model, String url, String image) {
        name1=name1+"_add";
        price=3;
        response=productSteps.updateProduct(productId,name1,type,price,shipping,upc,description,manufacture,model,url,image);
    }

    @And("^I check product updated successfully$")
    public void iCheckProductUpdatedSuccessfully() {
        response.statusCode(200);
    }

    @And("^I delete product$")
    public void iDeleteProduct() {
        response=productSteps.deleteProduct(productId).statusCode(200);
    }

    @Then("^I verify that product deleted successfully$")
    public void iVerifyThatProductDeletedSuccessfully() {
        response=productSteps.getProductById(productId);
    }
}
