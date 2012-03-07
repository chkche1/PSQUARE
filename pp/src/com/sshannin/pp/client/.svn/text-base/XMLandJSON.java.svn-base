package com.sshannin.pp.client;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.XMLParser;

public class XMLandJSON {
	/**
	 * Parse XML, return root element (not document root)
	 * 
	 * @param result
	 * @return
	 */
	public static Node parseXML(final String result) {
		try {
			Document d = XMLParser.parse(result);
			for (Node n = d.getFirstChild(); n != null; n = n.getNextSibling()) {
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					return n;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Converts the XML representation of the user into a JSON representation
	 * for the JavaScript Infovis Toolkit
	 * 
	 * @param doc
	 * @return
	 */
	public static JSONObject getJsonForUser(final Node doc) {
		String name = "";
		int id = 0;

		JSONArray circles = new JSONArray();

		JSONObject data = new JSONObject();
		JSONArray photos = new JSONArray();

		for (Node child = doc.getFirstChild(); child != null; child = child
				.getNextSibling()) {

			if (child.getNodeName().equals("id")) {
				id = Integer.valueOf(getTextNode(child));
			} else if (child.getNodeName().equals("circle")) {
				for (Node circle = child.getFirstChild(); circle != null; circle = circle
						.getNextSibling()) {
					if (circle.getNodeName().equals("name")) {
						// Currently ignores circle name
					} else if (circle.getNodeName().equals("containsFriend")) {
						int fid = 0;
						String fname = "";
						for (Node friendInfo = circle.getFirstChild(); friendInfo != null; friendInfo = friendInfo
								.getNextSibling()) {
							if (friendInfo.getNodeType() == Node.TEXT_NODE) {

							} else if (friendInfo.getNodeName().equals("id")) {
								fid = Integer.valueOf(getTextNode(friendInfo));
							} else if (friendInfo.getNodeName().equals("name")) {
								fname = getTextNode(friendInfo);
							}
						}
						JSONObject thisFriend = new JSONObject();

						thisFriend.put("id", new JSONNumber(fid));
						thisFriend.put("name", new JSONString(fname));
						circles.set(circles.size(), thisFriend);
					}
				}
			} else if (child.getNodeName().equals("name")) {
				name = getTextNode(child);
			} else if (child.getNodeName().equals("age")) {
				data.put("age",
						new JSONNumber(Integer.valueOf(getTextNode(child))));
			} else if (child.getNodeName().equals("gender")) {
				data.put("gender", new JSONString(getTextNode(child)));
			} else if (child.getNodeName().equals("email")) {
				data.put("email", new JSONString(getTextNode(child)));
			} else if (child.getNodeName().equals("photo")) {
				for (Node ph = child.getFirstChild(); ph != null; ph = ph
						.getNextSibling()) {
					if (ph.getNodeName().equals("url"))
						photos.set(photos.size(), new JSONString(
								getTextNode(ph)));
				}
			}
		}

		JSONObject obj = new JSONObject();

		obj.put("id", new JSONNumber(id));
		obj.put("name", new JSONString(name));
		obj.put("children", circles);
		obj.put("data", data);
		data.put("photos", photos);

		//System.out.println(obj.toString());
		return obj;
	}

	/**
	 * Retrieves the text node child of an XML element node
	 * 
	 * @param parent
	 * @return
	 */
	public static String getTextNode(final Node parent) {
		Node child = parent.getFirstChild();

		while (child != null && child.getNodeType() != Node.TEXT_NODE)
			child = child.getNextSibling();

		if (child != null)
			return child.getNodeValue();
		else
			return null;
	}

}
