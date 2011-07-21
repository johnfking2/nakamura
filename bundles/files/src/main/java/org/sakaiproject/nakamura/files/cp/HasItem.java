package org.sakaiproject.nakamura.files.cp;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.files.lomcp.JSONUtil;

import java.util.ArrayList;
import java.util.List;

public class HasItem extends HasMetadata{

  private List<Item> items;
  private String title;

  protected String itemName = "item";
  protected String titleName = "title";
  
  public HasItem() {
    super();
  }
  @Override
  protected void setElementName() {
    itemName = prefix + "item";
    titleName = prefix + "title";
  }

  @Override
  protected void init() {
    super.setElementName();
    super.init();
    JSONObject itemJSON = JSONUtil.getJSONObject(json, itemName);
    if (itemJSON == null){
      JSONArray itemArray = JSONUtil.getJSONArray(json, itemName);
      if (itemArray != null) {
        for (int i = 0; i < itemArray.length(); i++) {
          JSONObject object = itemArray.optJSONObject(i);
          if (object != null) {
            addItem(new Item(object, prefix, lomPrefix));
          }
        }
      }
    } else {
      addItem(new Item(itemJSON, prefix, lomPrefix));
    }
    title = JSONUtil.getStringValue(json, titleName);
    if (title == null)
      title = "";
  }
  
  public List<Item> getItems() {
    return items;
  }

  public void addItem(Item item, int index) {
    if (items == null) {
      items = new ArrayList<Item>();
    }
    items.add(index, item);
  }
  
  public void addItem(Item item) {
    if (items == null) {
      items = new ArrayList<Item>();
    }
    items.add(item);
  }
  
  public Item searchSubItem(String id) {
    if (items == null || id == null || "".equals(id))
      return null;
    for (int i = 0; i < items.size(); i++) {
      if (id.equalsIgnoreCase(items.get(i).getIdentifier())){
        return items.get(i);
      }
    }
    for (Item item : items) {
      Item i = item.searchSubItem(id);
      if (i != null) {
        return i;
      }
    }
    return null;
  }
  
  public void setItems(List<Item> items) {
    this.items = items;
  }
  
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
  
  public boolean hasSubItems() {
    if (items == null)
      return false;
    return true;
  }

  
}
