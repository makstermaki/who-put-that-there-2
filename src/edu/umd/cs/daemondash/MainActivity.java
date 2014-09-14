package edu.umd.cs.daemondash;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.daemondash.sqlite.DatabaseHelper;
import edu.umd.cs.daemondash.sqlite.model.ContainerEntry;
import edu.umd.cs.daemondash.sqlite.model.ContainerFood;
import edu.umd.cs.daemondash.sqlite.model.Diner;
import edu.umd.cs.daemondash.sqlite.model.Food;
import edu.umd.cs.daemondash.sqlite.model.User;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;



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
        
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        
        db.clearDb();
        
        Diner diner1 = new Diner("BOB");
        Diner diner2 = new Diner("JACK");
        Diner diner3 = new Diner("JOE");
        
        long diner1_id = db.createDiner(diner1);
        long diner2_id = db.createDiner(diner2);
        long diner3_id = db.createDiner(diner3);
        
        Food food1 = new Food("Pizza");
        Food food2 = new Food("Wrap");
        Food food3 = new Food("Value Meal");
        
        long food1_id = db.createFood(food1);
        long food2_id = db.createFood(food2);
        long food3_id = db.createFood(food3);
        
        User user1 = new User("Bobby");
        User user2 = new User("Koji");
        User user3 = new User("Maki");
        
        long user1_id = db.createUser(user1);
        long user2_id = db.createUser(user2);
        long user3_id = db.createUser(user3);
        
        List<ContainerFood> foods1 = new ArrayList<ContainerFood>();
        foods1.add(new ContainerFood(new Long(1), food1));
        foods1.add(new ContainerFood(new Long(1), food2));
        List<ContainerFood> foods2 = new ArrayList<ContainerFood>();
        foods1.add(new ContainerFood(new Long(2), food1));
        foods1.add(new ContainerFood(new Long(2), food3));
        List<ContainerFood> foods3 = new ArrayList<ContainerFood>();
        foods1.add(new ContainerFood(new Long(3), food3));
        foods1.add(new ContainerFood(new Long(3), food2));
        ContainerEntry entry1 = new ContainerEntry(Long.valueOf(1), Long.valueOf(1), 
        		user1, diner1, Double.valueOf(1.1), Double.valueOf(1.1), foods1);
        ContainerEntry entry2 = new ContainerEntry(Long.valueOf(2), Long.valueOf(2), 
        		user1, diner2, Double.valueOf(2.2), Double.valueOf(2.2), foods2);
        ContainerEntry entry3 = new ContainerEntry(Long.valueOf(3), Long.valueOf(3), 
        		user1, diner3, Double.valueOf(3.3), Double.valueOf(3.3), foods3);
        
        long entry_id1 = db.createContainerEntry(entry1);
        long entry_id2 = db.createContainerEntry(entry2);
        long entry_id3 = db.createContainerEntry(entry3);
        
        Log.d("Entry ID", "Entry ID 1: " + entry_id1);
        Log.d("Entry ID", "Entry ID 2: " + entry_id2);
        Log.d("Entry ID", "Entry ID 3: " + entry_id3);
                
        List<ContainerEntry> entries = db.getContainerEntries();
        for (ContainerEntry curr : entries) {
        	for (ContainerFood currFood : curr.getFoods()) {
        		
        	}
        }
        
        db.closeDB();
        
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
