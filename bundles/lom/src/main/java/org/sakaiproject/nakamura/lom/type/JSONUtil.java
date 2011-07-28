package org.sakaiproject.nakamura.lom.type;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import java.util.Iterator;

/**
 * This class is to help search element in json object, ignoring case sensitive in element names.
 */
public class JSONUtil {
  
  public static JSONObject getJSONObject(JSONObject json, String elementName) {
    if (json.optJSONObject(elementName) != null)
      return json.optJSONObject(elementName);
    Iterator<String> names = json.keys();
    elementName = elementName.toLowerCase();
    if (names != null) {
      while(names.hasNext()) {
        String s = names.next();
        if (s.length() < elementName.length())
          continue;
        if (elementName.equalsIgnoreCase(s) || s.toLowerCase().endsWith(":" + elementName.toLowerCase())) {
          return json.optJSONObject(s);
        }
       }
    }
    return null;
  }
  
  public static JSONArray getJSONArray(JSONObject json, String elementName) {
    if (json.optJSONArray(elementName) != null)
      return json.optJSONArray(elementName);
    Iterator<String> names = json.keys();
    if (names != null) {
      while(names.hasNext()) {
        String s = names.next();
        if (s.length() < elementName.length())
          continue;
        if (elementName.equalsIgnoreCase(s) || s.toLowerCase().endsWith(":" + elementName.toLowerCase())) {
          return json.optJSONArray(s);
        }
       }
    }
    return null;
  }
  
  public static String getStringValue(JSONObject json, String elementName) {
    if (getJSONObject(json, elementName) != null || getJSONArray(json, elementName) != null)
      return null;
    if (json.optString(elementName) != null && !("".equals(json.optString(elementName))))
      return json.optString(elementName);
    Iterator<String> names = json.keys();
    if (names != null) {
      while(names.hasNext()) {
        String s = names.next();
        if (s.length() < elementName.length())
          continue;
        if (elementName.equalsIgnoreCase(s) || s.toLowerCase().endsWith(":" + elementName.toLowerCase())) {
          return json.optString(s);
        }
       }
    }
    return null;
  }
}
