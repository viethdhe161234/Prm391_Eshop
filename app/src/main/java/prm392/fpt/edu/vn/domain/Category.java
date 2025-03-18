package prm392.fpt.edu.vn.domain;

public class Category {
    String type;
    String img_uml;
    public Category(){

    }
    public Category(String type, String img_uml) {
        this.type = type;
        this.img_uml = img_uml;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_uml() {
        return img_uml;
    }

    public void setImg_uml(String img_uml) {
        this.img_uml = img_uml;
    }
}
