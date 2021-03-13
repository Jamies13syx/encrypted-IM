package common;

import java.io.Serializable;

public class User implements Serializable {
    private String UID;
    private String password;

    public String getUID() {
        return UID;
    }

    public String getPassword() {
        return password;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
