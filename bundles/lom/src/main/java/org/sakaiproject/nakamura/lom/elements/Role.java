package org.sakaiproject.nakamura.lom.elements;

import org.apache.sling.commons.json.JSONObject;
import org.sakaiproject.nakamura.lom.type.VocabularyType;

public class Role extends VocabularyType {

  private String[] vocabulary = new String[] {"author", "publisher", "unknown", "initiator", "terminator", "validator", "editor",
      "graphical designer", "techinical implementer", "content provider", "technical validator", "educational validator", "script writer",
      "instructional designer", "subject matter expert"
  };

  private String[] vocabularyForMetaData = new String[] {"creator", "validator"};
  
  
  private Contribute.CONTRIBUTETYPE belongsTo;

  public Role(Contribute.CONTRIBUTETYPE roleType) {
    super();
    this.belongsTo = roleType; 
  }
  
  public Role (JSONObject json, Contribute.CONTRIBUTETYPE roleType) {
    super(json);
    this.belongsTo = roleType; 
  }

  @Override
  public String[] getLOMVocabulary() {
    return vocabulary;
  }
  
  public String[] getLOMVocabularyForLifeCycle() {
    return vocabulary;
  }
  
  public String[] getLOMVocabularyForMetametaData() {
    return this.vocabularyForMetaData;
  }
  
  public Contribute.CONTRIBUTETYPE getRoleType() {
    return belongsTo;
  }
  
  @Override
  public String generateXML() {
    if (super.generateXML().equals(""))
      return "";
    return new String("<role>" + super.generateXML() + "</role>");
  }

}
