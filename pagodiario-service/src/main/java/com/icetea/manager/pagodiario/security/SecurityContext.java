package com.icetea.manager.pagodiario.security;

public class SecurityContext {

    private static InheritableThreadLocal<String> username = new InheritableThreadLocal<String>();
    private static InheritableThreadLocal<String> timezone = new InheritableThreadLocal<String>();

    public static String getUsername() {
        return username.get();
    }

    public static void setUsername(String user) {
        username.set(user);
    }

    public static void setTimezone(String tz){
    	timezone.set(tz);
    }
    
    public static String getTimezone(){
    	return timezone.get();
    }
    
}
