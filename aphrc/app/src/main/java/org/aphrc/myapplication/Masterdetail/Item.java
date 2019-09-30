package org.aphrc.myapplication.Masterdetail;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable {
	private static final long serialVersionUID = -6099312954099962806L;
	private String title;
	private String body;

	public Item(String title, String body) {
		this.title = title;
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}


	@Override
	public String toString() {
		return getTitle();
	}

}
