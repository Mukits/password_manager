package com.multipasswordmngr.domain.vo;
import org.apache.commons.lang3.Validate;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;



public final class Password {
    private String password;
    public final int MIN_SIZE = 2;
    public final int MAX_SIZE = 100;
    public Password() {
    }

    public Password(String p) {
        Validate.notNull(p);
        checkInvariants(p);
    }

    private void checkInvariants(String pwd) {
        
        Validate.notNull(pwd);
        int sz = pwd.length();
        Validate.inclusiveBetween(MIN_SIZE,MAX_SIZE, sz,
                "The value must be between %d and %d",
                MIN_SIZE,MAX_SIZE);
            
           
        
            encryptPassword(pwd);
           
    
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String encryptPassword(String pass){
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm("sha256");
        passwordEncryptor.setPlainDigest(false);
        passwordEncryptor.setStringOutputType("hexadecimal");
        String encryptedPass = passwordEncryptor.encryptPassword(pass);
        return this.password = encryptedPass;
    }
        


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Password that = (Password) o;
        return password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return password.hashCode();
    }

    @Override
    public String toString() {
        return password;
    }

}

