package musictag.hytham1.com.musictag;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import data.CustomListViewAdapter;
import model.Event;
import utils.Prefs;

public class MainActivity extends AppCompatActivity {
    String url = "http://ws.audioscrobbler.com/2.0/?method=geo.gettopartists&country=spain&api_key=82eb6fa4ef5d5fcb3e698b9e5aedc5ff&format=json";
    private CustomListViewAdapter adapter;
    private ArrayList<Event> events = new ArrayList<>();
    private ListView listView;
    private TextView selectedCity;
    private ProgressDialog pDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListViewAdapter(MainActivity.this , R.layout.list_row , events);
        listView.setAdapter(adapter);

        Prefs cityPreference = new Prefs(MainActivity.this);
        String city = cityPreference.getCity();

        showEvents(city);
        selectedCity = (TextView) findViewById(R.id.selectedLocationText);
        selectedCity.setText("Selected City: " + city);



    }



    private void getEvents(String city){

        events.clear();
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Loading......");
        pDialog.show();


        JsonObjectRequest eventsRequest = new JsonObjectRequest(Request.Method.GET, url, (JSONObject) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hidePDialog();

                        try {
                            JSONObject eventsObject = response.getJSONObject("events");
                            JSONArray eventsArray =  eventsObject.getJSONArray("event");

                            for (int i = 0 ; i < eventsObject.length(); i++){
                                JSONObject jsonObject = eventsArray.getJSONObject(i);
                                //get Artist object

                                JSONObject artistsObject = jsonObject.getJSONObject("artists");
                                String headLinerText = artistsObject.getString("headLiner");


                                //Get Venue Object
                                JSONObject venueObject = jsonObject.getJSONObject("venue");
                                String venueName = venueObject.getString("name");

                                //Get Location Object
                                JSONObject locationObject = jsonObject.getJSONObject("location");

                                String streetObject = locationObject.getString("street");
                                String cityObject = locationObject.getString("city");
                                String countryObject = locationObject.getString("country");
                                String postalCode = locationObject.getString("postalCode");

                                //Get Url Image

                                JSONArray urlImage = jsonObject.getJSONArray("image");

                                //get Image now!
                                JSONObject largeImage = urlImage.getJSONObject(3);

                                //get actual image
                                String image = largeImage.getString("#text");

                                //get started date
                                String startDate = jsonObject.getString("startDate");

                                //get website url
                                String website = jsonObject.getString("website");

                                Event event = new Event();
                                event.setHeadliner(headLinerText);
                                event.setVenueName(venueName);
                                event.setStreet(streetObject);
                                event.setCity(cityObject);
                                event.setCountry(countryObject);
                                event.setUrl(image);
                                event.setStartDate(startDate);
                                event.setWebSite(website);

                                events.add(event);
                            }
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidePDialog();

            }
        });
        AppController.getInstance().addToRequestQueue(eventsRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.change_locationId){
            showInputDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void showInputDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Change City");

        final EditText cityInput = new EditText(MainActivity.this);
        cityInput.setInputType(InputType.TYPE_CLASS_TEXT);
        cityInput.setHint("Portland");
        builder.setView(cityInput);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Prefs cityPreference = new Prefs(MainActivity.this);
                cityPreference.setCity(cityInput.getText().toString());
                String newCity = cityPreference.getCity();

                selectedCity.setText("Selected City: " + newCity);

                //re-render everything again

                showEvents(newCity);
            }
        });
        builder.show();
    }

    private void showEvents(String newCity) {
        getEvents(newCity);
    }
    private void hidePDialog(){
        if (pDialog!=null){
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
