import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class SearchTestUtils {
    public String getMovieNameByImdbId(String ımdbId){

        String getResponseByIdOrTitle=given().log().all().queryParam("apikey","2061676b")
                .queryParam("i",ımdbId).when().get()
                .then().assertThat().log().all().statusCode(200).extract().response().asString();
        JsonPath jsonPath3=ReUsableMethods.rawToJson(getResponseByIdOrTitle);
        String title=jsonPath3.getString("Title");
        String year=jsonPath3.getString("Year");
        String released=jsonPath3.getString("Released");
        Assert.assertTrue(title,true);
        Assert.assertTrue(year,true);
        Assert.assertTrue(released,true);
        JsonPath jsonPath1=ReUsableMethods.rawToJson(getResponseByIdOrTitle);
        String ourTitle=jsonPath1.getString("Title");
        return ourTitle;
    }

    public String getImdbIdByMovieName(String movieName){

        RestAssured.baseURI= EnvUtils.baseURL;
        String getResponseBySearch=given().log().all().queryParam("apikey","2061676b")
                .queryParam("type","movie")
                .queryParam("s",movieName).when().get()
                .then().assertThat().log().all().statusCode(200).extract().response().getBody().asString();
        System.out.println("RESPONSE: "+getResponseBySearch);
        JsonPath jsonPath=ReUsableMethods.rawToJson(getResponseBySearch);
        String ımdbID=jsonPath.getString("Search[1].imdbID");
        System.out.println("Our IDMBID: "+ımdbID);
        return ımdbID;
    }
}
