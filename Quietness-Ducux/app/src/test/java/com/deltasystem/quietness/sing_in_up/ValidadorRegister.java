package com.deltasystem.quietness.sing_in_up;

import androidx.core.util.PatternsCompat;

public class ValidadorRegister {
    public boolean validate(String name,String user,String email,String password,String re_password) {
        boolean valid = true;
        if (name.isEmpty() || name.length() < 3) {
            valid = false;
        }

        if (email.isEmpty() || !PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            valid = false;
        }

        if (user.isEmpty()) {
            valid = false;
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 12) {
            valid = false;
        }

        if (re_password.isEmpty() || re_password.length() < 4 || re_password.length() > 12 || !(re_password.equals(password))) {

            valid = false;
        }

        return valid;
    }
}
