package org.sakaiproject.nakamura.lom.elements;

import org.sakaiproject.nakamura.lom.type.VCard;

public class Entity extends VCard {
  public Entity() {
    super();
  }
  public Entity(String content) {
    super(content);
  }
  
  public String generateXML() {
    return new String("<entity>" + this.getContent() + "</entity>");
  }
}
