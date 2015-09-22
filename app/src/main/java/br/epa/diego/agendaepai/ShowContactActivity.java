package br.epa.diego.agendaepai;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ShowContactActivity extends AppCompatActivity implements OnMapReadyCallback {
    Contact contact;
    TextView tvPhone;
    TextView tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);

        contact = (Contact) getIntent().getSerializableExtra("contact");
        getSupportActionBar().setTitle(contact.getName());

        tvPhone = (TextView) findViewById(R.id.tv_phone);
        tvPhone.setText(contact.getPhone());
        tvAddress = (TextView) findViewById(R.id.tv_address);
        tvAddress.setText(contact.getAddress());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            Log.v("Log", "Entrando try getAddress");
            addresses = geocoder.getFromLocationName(contact.getAddress(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses != null) {
            Address address = addresses.get(0);
            LatLng coordinates = new LatLng(address.getLatitude(), address.getLongitude());
            map.addMarker(new MarkerOptions().position(coordinates).title(contact.getAddress()));
            Log.v("Log", "Latitude: " + address.getLatitude() + " Longitude: " + address.getLongitude());
        }else{
            Log.v("Log", "Não deu certo!");
        }
    }
}
