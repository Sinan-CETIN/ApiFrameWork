package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    protected static RequestSpecification requestSpecification;

    public RequestSpecification requestSpecification() throws IOException {


        if (requestSpecification == null) {
            String logFilePath = "logging.txt";
            String logResponseFilePath = "responseLog.txt";

            PrintStream logStream = new PrintStream(new File(logFilePath));
            PrintStream responseLogStream = new PrintStream(new File(logResponseFilePath));

            requestSpecification = new RequestSpecBuilder().
                    setBaseUri(getGlobalValue("baseUrl")).
                    addQueryParam("key", "qaclick123").
                    addFilter(RequestLoggingFilter.logRequestTo(logStream)).
                    addFilter(ResponseLoggingFilter.logResponseTo(responseLogStream)).
                    setContentType(ContentType.JSON).build();

            return requestSpecification;
        }

        return requestSpecification;

    }

    public static String getGlobalValue(String key) throws IOException {

        String globalPropertiesFilePath = "/Users/Sinan/workspace/ApiFrameWork/src/test/java/resources/global.properties";
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(globalPropertiesFilePath);
        properties.load(fileInputStream);
        String value = properties.getProperty(key);

        return value;
    }

    public String getJsonPath(Response response, String key) {

        String responseAsString = response.asString();
        JsonPath js = new JsonPath(responseAsString);
        return js.get(key).toString();


    }


}
