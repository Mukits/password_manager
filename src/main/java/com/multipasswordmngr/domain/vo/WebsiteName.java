package com.multipasswordmngr.domain.vo;

import org.apache.commons.lang3.Validate;

public class WebsiteName {
    private String websiteName;
    
    public WebsiteName() {
    }
    public WebsiteName(String wbName) {
        Validate.notNull(wbName);
        int sz = wbName.length();
        Validate.inclusiveBetween(2, 100, sz, "The value must be between 5 and 100 characters");
        websiteName = wbName;
    }

    public String getWebsiteName() {
        return websiteName;
    }
    public void setWebpageName(String webpageName) {
        this.websiteName = webpageName;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
            WebsiteName that = (WebsiteName) o;
        return websiteName.equals(that.websiteName);
    }
    @Override
    public int hashCode() {
        return websiteName.hashCode();
    }
    @Override
    public String toString() {
        return websiteName;
    }
}
