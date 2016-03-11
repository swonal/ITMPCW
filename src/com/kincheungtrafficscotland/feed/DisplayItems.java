//Kin Yick Cheung S1302770
package com.kincheungtrafficscotland.feed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.kincheungtrafficscotland.feed.util.DOMParser;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class DisplayItems extends ListActivity {
	
	private String URL = null;
	
	static final String TAG_ITEM = "item";
	static final String TAG_TITLE = "title";
	static final String TAG_DESCRIPTION = "description";
	static final String TAG_LINK = "link";
	static final String TAG_PUBDATE = "pubDate";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feed_show);
		
		Bundle extras = getIntent().getExtras();
		URL = extras.getString("ci");

		ArrayList<HashMap<String, String>> rssFeedItems = new ArrayList<HashMap<String, String>>();

		DOMParser parser = new DOMParser();
		String xml = null;
		try {
			xml = parser.getXmlFromUrl(URL); //putting the xml in string form
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		Document doc = parser.getDomElement(xml); // getting DOM element

		NodeList no = doc.getElementsByTagName(TAG_ITEM);
		for (int i = 0; i < no.getLength(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) no.item(i);
			// adding each value to HashMap
			map.put(TAG_TITLE, parser.getValue(e, TAG_TITLE));
			map.put(TAG_DESCRIPTION, parser.getValue(e, TAG_DESCRIPTION));
			map.put(TAG_LINK, parser.getValue(e, TAG_LINK));
			map.put(TAG_PUBDATE, "Pub. Date = " + parser.getValue(e, TAG_PUBDATE));

			rssFeedItems.add(map);
		}
		

		ListAdapter adapter = new SimpleAdapter(this, rssFeedItems,
				R.layout.item_view,
				new String[] { TAG_TITLE, TAG_DESCRIPTION, TAG_PUBDATE, TAG_LINK}, new int[] {
						R.id.txtTitle, R.id.txtDesc, R.id.txtPubDate, R.id.txtLink});

		setListAdapter(adapter);

		ListView lv = getListView();
		lv.setVerticalFadingEdgeEnabled(true);
				
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				
				String title = ((TextView) v.findViewById(R.id.txtTitle)).getText().toString();
				String desc = ((TextView) v.findViewById(R.id.txtDesc)).getText().toString();
				String pd = ((TextView) v.findViewById(R.id.txtPubDate)).getText().toString();
				String link = ((TextView) v.findViewById(R.id.txtLink)).getText().toString();
				
				
				Intent in = new Intent(getApplicationContext(), ShowDetail.class);
				in.putExtra("title", title);
				in.putExtra("desc", desc);
				in.putExtra("pd", pd);
				in.putExtra("link", link);
				

				startActivity(in);

			}
		});
	}
}