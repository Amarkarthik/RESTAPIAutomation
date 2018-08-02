import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.Iterator;

public class TestRestAPI {

    public static Response response;
    public static String jsonObjectData;

    public static void main(String args[]) {

        response = RestAssured.given().when().
                get("https://istheapplestoredown.com/api/v1/status/worldwide").
                then().
                contentType(ContentType.JSON).
                extract().response();

        jsonObjectData = response.asString();

        JSONObject jsonData = new JSONObject(jsonObjectData.toString());
        Iterator iterator = jsonData.keys();
        String key = null;
        while (iterator.hasNext()) {
            key = (String) iterator.next();
            JSONObject countryData = jsonData.getJSONObject(key);
            if (countryData.getString("status").equalsIgnoreCase("n")) {
                System.out.println(countryData.get("name"));
            }
        }

    }

}
