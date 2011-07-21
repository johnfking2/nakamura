package org.sakaiproject.nakamura.files.lomcp;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import java.util.Iterator;

/**
 * This class is to help search element in json object, ignoring case sensitive in element names.
 */
public class JSONUtil {
  
  public static JSONObject getJSONObject(JSONObject json, String elementName) {
    Iterator<String> names = json.keys();
    if (names != null) {
      while(names.hasNext()) {
        String name = names.next();
        if (elementName.equalsIgnoreCase(name)) {
          return json.optJSONObject(name);
        }
       }
    }
    return null;
  }
  
  public static JSONArray getJSONArray(JSONObject json, String elementName) {
    Iterator<String> names = json.keys();
    if (names != null) {
      while(names.hasNext()) {
        String name = names.next();
        if (elementName.equalsIgnoreCase(name)) {
          return json.optJSONArray(name);
        }
       }
    }
    return null;
  }
  
  public static String getStringValue(JSONObject json, String elementName) {
    Iterator<String> names = json.keys();
    if (names != null) {
      while(names.hasNext()) {
        String name = names.next();
        if (elementName.equalsIgnoreCase(name)) {
          return json.optString(name);
        }
       }
    }
    return null;
  }

}
