//Kin Yick Cheung S1302770
package com.kincheungtrafficscotland.feed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowDetail  extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get XML values from previous intent
        String title = in.getStringExtra("title");
        String desc = in.getStringExtra("desc");
        String pd = in.getStringExtra("pd");
        
        // Displaying all values on the screen
        TextView dtlTitle = (TextView) findViewById(R.id.detTitle);
        TextView dtlDesc = (TextView) findViewById(R.id.detDesc);
        TextView dtlPubDate = (TextView) findViewById(R.id.detPubDate);
        
        dtlTitle.setText(title);
        dtlDesc.setText(desc);
        dtlPubDate.setText(pd);
    }
}
