package one.stk.com.indextextview;

/**
 * Created by admin on 2016/8/23.
 */
public class TextModel {
    String Name;
    String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public TextModel(String name, String phone) {
        Name = name;
        this.phone = phone;
    }
}
