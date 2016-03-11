//Kin Yick Cheung S1302770
package com.kincheungtrafficscotland.feed.data;

public class RssFeedItem {
	
	private String title;
	private String description;
	private String link;
	private String geoRss;
	private String pubDate;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getGeoRss() {
		return geoRss;
	}
	public void setGeoRss(String geoRss) {
		this.geoRss = geoRss;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
}
