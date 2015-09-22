package br.epa.diego.agendaepai;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ListAdapter(this, db.getContacts()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent i = new Intent(this, NewContactActivity.class);
            startActivityForResult(i, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1){
            listView.setAdapter(new ListAdapter(this, db.getContacts()));
        }

    }

    public List<Contact> getContacts(){
        List<Contact> contacts = new ArrayList<>();

        contacts.add(new Contact("Diego Ferreira","9591471537","Rua Itajara nº 60"));
        contacts.add(new Contact("José da Silva", "9836243749", "Av. Jose Bonifácio"));
        contacts.add(new Contact("Mateus Pereira", "9591362782", "Rua José Silveira"));

        return contacts;
    }

    public class ListAdapter extends ArrayAdapter<Contact> {

        public ListAdapter(Context context, List objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Contact contact = getItem(position);

            if (convertView == null){
                convertView = getLayoutInflater().from(getContext()).inflate(R.layout.item_list_contato, parent, false);
            }

            TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
            TextView tvPhone = (TextView) convertView.findViewById(R.id.tv_phone);
            TextView tvAddress = (TextView) convertView.findViewById(R.id.tv_address);

            tvName.setText(contact.getName());
            tvPhone.setText(contact.getPhone());
            tvAddress.setText(contact.getAddress());

            return convertView;
        }
    }
}
