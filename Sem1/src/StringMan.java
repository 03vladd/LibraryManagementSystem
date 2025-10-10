class StringManipulation {
    //umkehren eines Strings
    public String umkehren(String s) {
       String umk = "";
       char ch;

       for (int i = 0; i < s.length(); i++) {
           ch = s.charAt(i);
           umk = ch + umk;
       }
       return umk;
    }

    //methode die die worter in einem satz zahlt
    public int wordCount(String s) {
        // null case
        if (s == null) {
            return 0;
        }

        String trimmed = s.trim();

        if (trimmed.isEmpty()) {
            return 0;
        }

        // split nach whitespace(s)
        String[] words = trimmed.split("\\s+");

        return words.length;
    }
}
