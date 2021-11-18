package com.deltasystem.quietness.sing_in_up;


import junit.framework.TestCase;

public class SignUpTest extends TestCase {
    private String[] emails ={"juancho15325@gmail.com","first.last@iana.org",
            "1234567890123456789012345678901234567890123456789012345678901234@iana.org",
            "first.last@sub.do,com","first\\\"last\"@iana.org","first.last",".first.last@iana.org",
            "first.last@-xample.com","user+mailbox@iana.org","TEST@iana.org",
            "test@123456789012345678901234567890123456789012345678901234567890123.123456789012345678901234567890123456789012345678901234567890123.123456789012345678901234567890123456789012345678901234567890123.123456789012345678901234567890123456789012345678901234567890.com",
            "NotAnEmail","a-b@bar.com","valid@about.museum","invalid@about.museum-"};

    public void testAllEmpty() {
        String name="";
        String username="";
        String email="";
        String pass="";
        String re_pass="";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }

    public void testNameEmpty(){
        String name="";
        String username="juan7567";
        String email="juancho15325@gmail.com";
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }
    public void testUsernameEmpty(){
        String name="Juan Manuel";
        String username="";
        String email="juancho15325@gmail.com";
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }
    public void testEmailEmpty(){
        String name="Juan Manuel";
        String username="juan7460";
        String email="";
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }
    public void testPassEmpty_ReNot(){
        String name="Juan Manuel";
        String username="juan7460";
        String email="juancho15325@gmail.com";
        String pass="";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }
    public void testPass_Re_Empty(){
        String name="Juan Manuel";
        String username="juan7567";
        String email="juancho15325@gmail.com";
        String pass="";
        String re_pass="";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }

    public void testOne_Sillab_name(){
        String name="J";
        String username="juan7567";
        String email="juancho15325@gmail.com";
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }

    public void testTwo_Sillab_name(){
        String name="Jo";
        String username="juan7567";
        String email="juancho15325@gmail.com";
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }

    public void testThree_Sillab_name(){
        String name="Jay";
        String username="juan7567";
        String email="juancho15325@gmail.com";
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }

    public void testUsername_numbers(){
        String name="Joaquin";
        String username="4";
        String email="juancho15325@gmail.com";
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }

    public void testUsername_Random(){
        String name="Jay";
        String username="jo982yujnbvabvb@";
        String email="juancho15325@gmail.com";
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }

    public void testEmailA(){
        String name="Jay";
        String username="juan7567";
        String email=emails[1]; //"first.last@iana.org"
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }
    public void testEmailB(){
        String name="Jay";
        String username="juan7567";
        String email=emails[2]; //"1234567890123456789012345678901234567890123456789012345678901234@iana.org"
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }

    public void testEmailC(){
        String name="Jay";
        String username="juan7567";
        String email=emails[3]; //"first.last@sub.do,com"
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }


    public void testEmailD(){
        String name="Jay";
        String username="juan7567";
        String email=emails[4]; //"first\\\"last\"@iana.org"
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }

    public void testEmailE(){
        String name="Jay";
        String username="juan7567";
        String email=emails[5]; //"first.last"
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }
    public void testEmailF(){
        String name="Jay";
        String username="juan7567";
        String email=emails[6]; //".first.last@iana.org"
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }


    public void testEmailG(){
        String name="Jay";
        String username="juan7567";
        String email=emails[7]; //"first.last@-xample.com"
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }

    public void testEmailH(){
        String name="Jay";
        String username="juan7567";
        String email=emails[8]; //"user+mailbox@iana.org"
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }

    public void testEmailI(){
        String name="Jay";
        String username="juan7567";
        String email=emails[9]; //"TEST@iana.org"
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }

    public void testEmailJ(){
        String name="Jay";
        String username="juan7567";
        String email=emails[10]; //"test@123456789012345678901234567890123456789012345678901234567890123.123456789012345678901234567890123456789012345678901234567890123.123456789012345678901234567890123456789012345678901234567890123.123456789012345678901234567890123456789012345678901234567890.com"
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }
    
    public void testEmailK(){
        String name="Jay";
        String username="juan7567";
        String email=emails[11]; //"NotAnEmail"
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }

    public void testEmailL(){
        String name="Jay";
        String username="juan7567";
        String email=emails[12]; ////"a-b@bar.com"
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }

    public void testEmailM(){
        String name="Jay";
        String username="juan7567";
        String email=emails[13];  //"valid@about.museum"
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }

    public void testEmailN(){
        String name="Jay";
        String username="juan7567";
        String email=emails[14]; //"invalid@about.museum-"
        String pass="Soybatman";
        String re_pass="Soybatman";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }

    public void testPass_Minor(){
        String name="Jay";
        String username="juan7567";
        String email=emails[1];
        String pass="123";
        String re_pass="123";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }

    public void testPass_Mayor(){
        String name="Jay";
        String username="juan7567";
        String email="hola@.com";
        String pass="absjkaskjsjajkassajkasaskjaajas";
        String re_pass="absjkaskjsjajkassajkasaskjaajas";
        ValidadorRegister sign = new ValidadorRegister();
        boolean result = sign.validate(name,username,email,pass,re_pass);
        assertEquals(false, result);
    }




}