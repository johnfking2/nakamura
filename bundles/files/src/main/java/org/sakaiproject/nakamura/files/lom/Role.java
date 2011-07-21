package org.sakaiproject.nakamura.files.lom;

import org.apache.sling.commons.json.JSONObject;

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
  
  public Role (JSONObject json, String prefix, Contribute.CONTRIBUTETYPE roleType) {
    super(json, prefix);
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

}
