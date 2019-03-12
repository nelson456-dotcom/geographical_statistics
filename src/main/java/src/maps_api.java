package src;

import java.io.IOException;

import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;


public class maps_api {
private static final String API_KEY = "AIzaSyAPDjnlXEPt9AylZ3vme4fc1wG_2VovPGs"; // !!! insert your API_KEY here
	
	public static int getDistance(String addrOne, String addrTwo, TravelMode mode)
			throws ApiException, InterruptedException, IOException {

		GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();
		DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(context);
		DistanceMatrix distanceMatrix = request.origins(addrOne).destinations(addrTwo).mode(mode).avoid(RouteRestriction.TOLLS)
				.language("en-US").await();
		int distance = (int) distanceMatrix.rows[0].elements[0].distance.inMeters / 1000;
		
		return distance;

}}
