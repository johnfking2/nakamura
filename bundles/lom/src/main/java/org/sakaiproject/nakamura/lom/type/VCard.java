package org.sakaiproject.nakamura.lom.type;
/**
 * Simple support for vCard(not full support)
 */
public class VCard {
  private String content;
  private String name;
  private String tel;
  private String organization;
  private String email;
  private String address;
  
  public VCard() {
    
  }
  
  public VCard(String vcard) {
    this.content = vcard.toUpperCase();
    init();
  }
  protected void init() {
    name = "";
    tel = "";
    organization = "";
    email = "";
    address = "";
    if (content!=null && content.trim().length()>0){
      int start = content.indexOf("BEGIN:VCARD");
      if (start > -1){
        String[] lines = content.split("\n");
        for(int i=0; i<lines.length; i++) {
          String l = lines[i].trim();
          if (l.startsWith("FN")){
            name += l.substring(l.indexOf(":")+1);
          }else if(l.startsWith("ORG")){
            organization += l.substring(l.indexOf(":")+1);
          }else if(l.startsWith("TEL")){
            tel += l.substring(l.indexOf(":")+1);
          }else if(l.startsWith("EMAIL")){
            email += l.substring(l.indexOf(":")+1);
          }else if(l.startsWith("ADR")){
            address += l.substring(l.indexOf(":")+1);
          }
        }
      }
    }
  }
  
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getContent() {
    StringBuffer buf = new StringBuffer();
    buf.append("BEGIN:VCARD\n");
    if (name!=null && name.length()>0)
      buf.append("FN:"+name+"\n");
    if (organization!=null && organization.length()>0)
      buf.append("ORG:"+organization+"\n");
    if (tel!=null && tel.length()>0)
      buf.append("TEL:"+tel+"\n");
    if (email!=null && email.length()>0)
      buf.append("EMAIL;TYPE=INTERNET:"+email+"\n");
    if (address!=null && address.length()>0)
      buf.append("ADR:"+address+"\n");
    buf.append("END:VCARD\n");
    content = buf.toString();
    return content;
  }

  public String getOrganization() {
    return organization;
  }

  public String getTel() {
    return tel;
  }

  public void setContent(String vcard) {
    this.content = vcard.trim().toUpperCase();
    init();
  }
}
