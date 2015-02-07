package com.grubit.dannykevin.foodapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 * Code sample for accessing the Yelp API V2.
 *
 * This program demonstrates the capability of the Yelp API version 2.0 by using the Search API to
 * query for businesses by a search term and location, and the Business API to query additional
 * information about the top result from the search query.
 *
 * <p>
 * See <a href="http://www.yelp.com/developers/documentation">Yelp Documentation</a> for more info.
 *
 */
public class YelpAPI {

    private static final String API_HOST = "api.yelp.com";
    private static final String DEFAULT_TERM = "dinner";
    private static final String DEFAULT_LOCATION = "San Francisco, CA";
    private static final int SEARCH_LIMIT = 3;
    private static final String SEARCH_PATH = "/v2/search";
    private static final String BUSINESS_PATH = "/v2/business";

    /*
     * Update OAuth credentials below from the Yelp Developers API site:
     * http://www.yelp.com/developers/getting_started/api_access
     */
    private static final String CONSUMER_KEY = "kmUv_m_f7FKQnhtp871VCw";
    private static final String CONSUMER_SECRET = "2ubrjqEWEz5rfc1gkfS-4Ji37-g";
    private static final String TOKEN = "PqN9k_L6aV0QRTqgS0olT6NzH44_eCRo";
    private static final String TOKEN_SECRET = "b4WZlv30A5XzBafBhO7uecsHCKg";

    OAuthService service;
    Token accessToken;

    /**
     * Setup the Yelp API OAuth credentials.
     *
     * @param consumerKey Consumer key
     * @param consumerSecret Consumer secret
     * @param token Token
     * @param tokenSecret Token secret
     */
    public YelpAPI(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        this.service =
                new ServiceBuilder().provider(TwoStepOAuth.class).apiKey(consumerKey)
                        .apiSecret(consumerSecret).build();
        this.accessToken = new Token(token, tokenSecret);
    }

    /**
     * Creates and sends a request to the Search API by term and location.
     * <p>
     * See <a href="http://www.yelp.com/developers/documentation/v2/search_api">Yelp Search API V2</a>
     * for more info.
     *
     * @param term <tt>String</tt> of the search term to be queried
     * @param latitude <tt>String</tt> of the location
     * @param longitude
     * @return <tt>String</tt> JSON Response
     */
    public Response searchForBusinessesByLocation(String term, String latitude, String longitude) {
        OAuthRequest request = createOAuthRequest(SEARCH_PATH);
        request.addQuerystringParameter("term", term);
        //request.addQuerystringParameter("location", "Waterloo");
        request.addQuerystringParameter("category_filter", "restaurants");
        request.addQuerystringParameter("radius-filter", "15000");
        request.addQuerystringParameter("ll", latitude+","+longitude);
        request.addQuerystringParameter("limit", String.valueOf(SEARCH_LIMIT));
        return sendRequestAndGetResponse(request);
    }

    /**
     * Creates and sends a request to the Business API by business ID.
     * <p>
     * See <a href="http://www.yelp.com/developers/documentation/v2/business">Yelp Business API V2</a>
     * for more info.
     *
     * @param businessID <tt>String</tt> business ID of the requested business
     * @return <tt>String</tt> JSON Response
     */
    public Response searchByBusinessId(String businessID) {
        OAuthRequest request = createOAuthRequest(BUSINESS_PATH + "/" + businessID);
        return sendRequestAndGetResponse(request);
    }

    /**
     * Creates and returns an {@link OAuthRequest} based on the API endpoint specified.
     *
     * @param path API endpoint to be queried
     * @return <tt>OAuthRequest</tt>
     */
    private OAuthRequest createOAuthRequest(String path) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://" + API_HOST + path);
        return request;
    }

    /**
     * Sends an {@link OAuthRequest} and returns the {@link Response} body.
     *
     * @param request {@link OAuthRequest} corresponding to the API request
     * @return <tt>String</tt> body of API response
     */
    private Response sendRequestAndGetResponse(OAuthRequest request) {
        //System.out.println("Querying " + request.getCompleteUrl() + " ...");
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response;
    }

    /**
     * Queries the Search API based on the command line arguments and takes the first result to query
     * the Business API.
     *
     * @param yelpApi <tt>YelpAPI</tt> service instance
     */
    public static String[] queryAPI(YelpAPI yelpApi, String type, String latitude, String longitude) {
        Response searchResponseJSON =
                yelpApi.searchForBusinessesByLocation(type, latitude, longitude);

        JSONParser parser = new JSONParser();
        JSONObject response = null;
        try {
            response = (JSONObject) parser.parse(searchResponseJSON.getBody());
        } catch (ParseException pe) {
            System.out.println("Error: could not parse JSON response:");
            System.out.println(searchResponseJSON);
            System.exit(1);
        }

        JSONArray businesses = (JSONArray) response.get("businesses");
        if(businesses.size() < 1 ){
            return new String[] {
                    "No Restaurants Nearby of That Type",
                    "No Restaurants Nearby of That Type",
                    "No Restaurants Nearby of That Type",
                    "No Restaurants Nearby of That Type",
                    "No Restaurants Nearby of That Type",
                    "No Restaurants Nearby of That Type",
                    "No Restaurants Nearby of That Type",
                    "No Restaurants Nearby of That Type",
                    "No Restaurants Nearby of That Type"
            };
        }

        JSONObject firstBusiness = (JSONObject) businesses.get(0);
        JSONObject secondBusiness = (JSONObject) businesses.get(1);
        JSONObject thirdBusiness = (JSONObject) businesses.get(2);

        String firstBusinessName = firstBusiness.get("name").toString();
        String secondBusinessName = secondBusiness.get("name").toString();
        String thirdBusinessName = thirdBusiness.get("name").toString();

        JSONObject firstBusinessAdd = (JSONObject) firstBusiness.get("location");
        JSONObject secondBusinessAdd = (JSONObject) secondBusiness.get("location");
        JSONObject thirdBusinessAdd = (JSONObject) thirdBusiness.get("location");

        JSONArray firstBusinessAddress = (JSONArray) firstBusinessAdd.get("address");
        JSONArray secondBusinessAddress = (JSONArray) secondBusinessAdd.get("address");
        JSONArray thirdBusinessAddress = (JSONArray) thirdBusinessAdd.get("address");

        String firstBusinessCity = firstBusinessAdd.get("city").toString();
        String secondBusinessCity = secondBusinessAdd.get("city").toString();
        String thirdBusinessCity = thirdBusinessAdd.get("city").toString();

        String firstBusinessPhone = firstBusiness.get("display_phone").toString();
        String secondBusinessPhone = secondBusiness.get("display_phone").toString();
        String thirdBusinessPhone = thirdBusiness.get("display_phone").toString();

        String review1 = firstBusiness.get("rating_img_url").toString();
        String review2 = secondBusiness.get("rating_img_url").toString();
        String review3 = thirdBusiness.get("rating_img_url").toString();

        // Select the first business and display business details
        //Response businessResponseJSON = yelpApi.searchByBusinessId(firstBusinessID.toString());
        String[] restaurants = {
                firstBusinessName,
                firstBusinessAddress.get(0).toString() + " " + firstBusinessCity,
                firstBusinessPhone,
                secondBusinessName,
                secondBusinessAddress.get(0).toString() + " " + secondBusinessCity,
                secondBusinessPhone,
                thirdBusinessName,
                thirdBusinessAddress.get(0).toString() + " " + thirdBusinessCity,
                thirdBusinessPhone,
                review1,
                review2,
                review3
        };
        return restaurants;
    }


}
