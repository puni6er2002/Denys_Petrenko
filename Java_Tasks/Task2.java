public static String first_NonRepeatingLetter(String str) {
        var lowercase = str.toLowerCase();
        for(int i = 0; i < lowercase.length(); i++) {
            if(lowercase.indexOf(lowercase.charAt(i), lowercase.indexOf(lowercase.charAt(i))+1) == -1)
                return String.valueOf(str.charAt(i));
        }
        return "";
    }
