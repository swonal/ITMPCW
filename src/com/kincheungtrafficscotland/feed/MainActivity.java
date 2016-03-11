//Kin Yick Cheung S1302770
package com.kincheungtrafficscotland.feed;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
 
public class MainActivity extends Activity {
 
    ListView listView;
    Button ciButton;
    Button rwButton;
    Button prwButton;
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        ciButton = (Button) findViewById(R.id.curButton);
        rwButton = (Button) findViewById(R.id.rwButton);
        prwButton = (Button) findViewById(R.id.prwButton);
        
        ciButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), DisplayItems.class);
				intent.putExtra("ci", "https://trafficscotland.org/rss/feeds/currentincidents.aspx");
				startActivity(intent);
			}
		});
        
        	rwButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), DisplayItems.class);
				intent.putExtra("ci", "https://trafficscotland.org/rss/feeds/roadworks.aspx");
				startActivity(intent);
			}
		});
        	
        	prwButton.setOnClickListener(new View.OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				Intent intent = new Intent(getApplicationContext(), DisplayItems.class);
    				intent.putExtra("ci", "https://trafficscotland.org/rss/feeds/plannedroadworks.aspx");
    				startActivity(intent);

    				
    			}
    		});
    }
 
 
}