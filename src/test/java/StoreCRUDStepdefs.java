import com.bestbuy.steps.StoreSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

public class StoreCRUDStepdefs {
    @Steps
    StoreSteps storeSteps;
    ValidatableResponse response;
    static int storeId;

    static String name1="Duracel_"+ TestUtils.getRandomValue();

    @When("^I create store with name \"([^\"]*)\" type\"([^\"]*)\" address\"([^\"]*)\" address2\"([^\"]*)\" city\"([^\"]*)\" state\"([^\"]*)\" zip\"([^\"]*)\" lat\"([^\"]*)\" lng\"([^\"]*)\" hours\"([^\"]*)\"$")
    public void iCreateStoreWithNameTypeAddressAddressCityStateZipLatLngHours(String name, String type, String address, String address2 , String city, String state , String zip, int lat, int lng, String hours)  {
        response=  storeSteps.addStore(name1,type,address,address2,city,state,zip,lat,lng,hours);
        storeId=(int) response.extract().path("id");
    }

    @And("^I verify store created$")
    public void iVerifyStoreCreated() {
        response.statusCode(201);
    }

    @And("^I update store with name \"([^\"]*)\" type\"([^\"]*)\" address\"([^\"]*)\" address2\"([^\"]*)\" city\"([^\"]*)\" state\"([^\"]*)\" zip\"([^\"]*)\" lat\"([^\"]*)\" lng\"([^\"]*)\" hours\"([^\"]*)\"$")
    public void iUpdateStoreWithNameTypeAddressAddressCityStateZipLatLngHours(String name, String type, String address, String address2 , String city, String state , String zip, int lat, int lng, String hours)  {
        state="Guj";

        response=storeSteps.updateStoreByName(storeId,name1,type,address,address2,city,state,zip,lat,lng,hours);
    }

    @And("^I check store is updated successfully$")
    public void iCheckStoreIsUpdatedSuccessfully() {
        response.statusCode(200);
    }

    @And("^I delete store$")
    public void iDeleteStore() {
        response=storeSteps.deleteStore(storeId).statusCode(200);
    }

    @Then("^I verify that store is deleted successfully$")
    public void iVerifyThatStoreIsDeletedSuccessfully() {
        response=storeSteps.deleteStore(storeId).statusCode(404);
    }
}
