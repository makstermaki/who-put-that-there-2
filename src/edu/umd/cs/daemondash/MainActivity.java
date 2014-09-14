package edu.umd.cs.daemondash;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graph.BarGraph;
import android.graph.LineGraph;
import android.graph.PieGraph;
import android.graph.ScatterGraph;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

import tw.com.quickmark.sdk.demo.*;




public class MainActivity extends Activity {

	public int pressed_ct = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void sendMessage(View view) {
        // Do something in response to button click
    	Button p1_button = (Button)findViewById(R.id.button1);
    	pressed_ct++;
    	p1_button.setText("Clicked! " + pressed_ct);
    	TextView p1_text = (TextView)findViewById(R.id.information_feedback);
    	//grab date/time objects
    	//grab gps stuff?
    	p1_text.setText("Prepare yourself for some infodrops:");
    }
    
    public void buttonPress(View view) {
    	Button p1_button = (Button)findViewById(R.id.button1);
    	pressed_ct++;
    	p1_button.setText("Clicked! " + pressed_ct);
    	
    	//grab date/time objects
    	
    	//grab gps stuff?	
    	LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    	String locationProvider = LocationManager.NETWORK_PROVIDER;
    	Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
    	Intent intent = new Intent(this, CaptureActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, 90);
    	
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	  super.onActivityResult(requestCode, resultCode, data);
    	  switch(requestCode) {
    	    case (90) : {
    	      if (resultCode == Activity.RESULT_OK) {
    	        // TODO Extract the data returned from the child Activity.
    	    	  TextView p1_text = (TextView)findViewById(R.id.information_feedback);
    	    	  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	      		Date date = new Date();
    	      	int barcode = Integer.parseInt((data.getExtras().get("code")).toString());
    	    	  p1_text.setText("Prepare yourself for the infodrops:\n" +
    	      			"\nDate: " + dateFormat.format(date) +
    	      			"\nBarcode: " + barcode);
    	      }
    	      break;
    	    } 
    	  }
    	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void lineGraphHandler (View view)
    {
    	LineGraph line = new LineGraph();
    	Intent lineIntent = line.getIntent(this);
        startActivity(lineIntent);
    }
    
    public void barGraphHandler (View view)
    {
    	BarGraph bar = new BarGraph();
    	Intent lineIntent = bar.getIntent(this);
        startActivity(lineIntent);
    }
    
    public void pieGraphHandler (View view)
    {
    	PieGraph pie = new PieGraph();
    	Intent lineIntent = pie.getIntent(this);
        startActivity(lineIntent);
    }
    
    public void scatterGraphHandler (View view)
    {
    	ScatterGraph scatter = new ScatterGraph();
    	Intent lineIntent = scatter.getIntent(this);
        startActivity(lineIntent);
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
