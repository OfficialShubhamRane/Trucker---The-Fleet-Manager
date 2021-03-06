package com.example.trucker_demo.service;

import com.example.trucker_demo.model.Constants;
import com.example.trucker_demo.model.VehicleLocation_Model;
import com.example.trucker_demo.model.VehicleReading_Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

@Service
public class RevGeoLocationService {

    @Autowired
    VehicleReadingService vehicleReadingsService;

    private static final String GEOCODING_RESOURCE = "https://revgeocode.search.hereapi.com/v1/revgeocode";
    private static final String TruckerGeoCodingAPIKey = Constants.TRUCKER_LOCATION_API_KEY;

    /** Return location in form of http response body */
    public String revGeoCode(String query) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();

        String encodedQuery = URLEncoder.encode(query,"UTF-8");
        String requestUri = GEOCODING_RESOURCE + "?apiKey=" + TruckerGeoCodingAPIKey + "&at=" + encodedQuery + "&lang=en-US";
//        System.out.println(requestUri);
        HttpRequest geocodingRequest = HttpRequest.newBuilder().GET().uri(URI.create(requestUri))
                .timeout(Duration.ofMillis(2000)).build();

        HttpResponse<String> geocodingResponse = httpClient.send(geocodingRequest,
                HttpResponse.BodyHandlers.ofString());

        return geocodingResponse.body();

    }

    /** Extracts address from HTTP response body  */
    public VehicleLocation_Model getLocation(String coordinates) throws IOException, InterruptedException {

        ObjectMapper mapper = new ObjectMapper();

        String response = revGeoCode(coordinates);
        JsonNode responseJsonNode = mapper.readTree(response);

        JsonNode items = responseJsonNode.get("items");

        String label;
        VehicleLocation_Model vehicleLocation_model = new VehicleLocation_Model();

        for (JsonNode item : items) {

            JsonNode address = item.get("address");
            label = address.get("label").asText();
            vehicleLocation_model.setLocationName(label);

            JsonNode position = item.get("position");
            vehicleLocation_model.setLatitude(position.get("lat").asText());
            vehicleLocation_model.setLongitude(position.get("lng").asText());

        }
        return vehicleLocation_model;
    }

    /** Returns List of addresses including Latitude and longitude for each reading */
    public List<VehicleLocation_Model> getVehicleLocation(String vin) throws IOException, InterruptedException {

        List<VehicleReading_Model> vehicleReading_Model = vehicleReadingsService.getVehicleCoordinates(vin);
        List<VehicleLocation_Model> addresses = new ArrayList<>();
        VehicleLocation_Model tempLocation_Model;

        for (VehicleReading_Model vehicleReading : vehicleReading_Model){
            String coordinates = vehicleReading.getLatitude().toString() +","+ vehicleReading.getLongitude().toString() ;

            tempLocation_Model = getLocation(coordinates);

            if(tempLocation_Model.getLocationName() != null){
                addresses.add( tempLocation_Model );
            }

        }
        return addresses;
    }

}
