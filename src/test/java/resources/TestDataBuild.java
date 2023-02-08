package resources;

import googleAddApiPojoClasses.AddPlace;
import googleAddApiPojoClasses.Location;


import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {


    public AddPlace addPlaceData(String name, String language, String address) {
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("otopark");
        myList.add("shop");

        Location location = new Location();
        location.setLat(124013.4);
        location.setLng(-124592.4);

        AddPlace addPlace = new AddPlace();

        addPlace.setAccuracy(50);
        addPlace.setAddress(address);
        addPlace.setLanguage(language);
        addPlace.setPhone_number("(+90) 535 513 58 38");
        addPlace.setWebsite("http://guzelyemek.com");
        addPlace.setName(name);
        addPlace.setTypes(myList);
        addPlace.setLocation(location);

        return addPlace;
    }

    public String deletePlacePayLoad(String placeId) {
        return "{\r\n    \"place_id\":\"" + placeId + "\"\r\n}\r\n";
    }
}
