package com.driver;

import java.util.Objects;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character

        if(Objects.equals(oldPassword, password)){

            boolean[] checks = new boolean[5]; //making a boolean array for the 5 checks

            if(newPassword.length()>=8){
                checks[0]=true;
            }

            for(int i = 0; i < newPassword.length(); i++) {
                char c = newPassword.charAt(i);

                if (c >= 'A' && c <= 'Z') { //ascii range of uppercase letter are 65-90
                    checks[1] = true;
                } else if (c >= 'a' && c <= 'z') { //ascii range of lowercase letter are 97-122
                    checks[2] = true;
                } else if (c >= '0' && c <= '9') {
                    checks[3] = true;
                } else {
                    checks[4] = true;
                }

            }

                for(boolean b : checks){
                    if(!b){
                        return;
                    }
                }

                password = newPassword;
        }
    }
}
