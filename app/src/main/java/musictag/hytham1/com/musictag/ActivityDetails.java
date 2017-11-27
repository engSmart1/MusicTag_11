package musictag.hytham1.com.musictag;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import model.Event;

public class ActivityDetails extends AppCompatActivity {
    private Event event;
    private TextView headLiner;
    private TextView venue;
    private TextView where;
    private TextView when;
    private NetworkImageView bandImage;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Get Serializable object
        event = (Event) getIntent().getSerializableExtra("eventObj");

        headLiner = (TextView) findViewById(R.id.detsHeadLiner);
        venue = (TextView) findViewById(R.id.detsVenue);
        where = (TextView) findViewById(R.id.detsWhere);
        when = (TextView) findViewById(R.id.detsWhen);
        bandImage = (NetworkImageView)findViewById(R.id.detsBandImage);

        headLiner.setText("Headliner: " + event.getHeadliner());
        bandImage.setImageUrl(event.getUrl(), imageLoader);
        venue.setText("Venue: " + event.getVenueName());
        when.setText("When: " + event.getStartDate());
        where.setText("Where: " + event.getStreet() + ", " + event.getCity() + ", " + event.getCountry() );



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_websiteId){

            String url = event.getWebSite();
            if (!url.equals("")){
                Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse(url));
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Website is not exist" ,Toast.LENGTH_LONG).show();
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
