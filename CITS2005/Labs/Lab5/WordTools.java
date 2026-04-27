public class WordTools {

    public static void main(String[] args) {
        String input = String.join(" ", args);
        
        // tests
        System.out.println(isWord("Hello")); // true
        System.out.println(isWord("Hello!")); // false
        System.out.println(isWord("H ello")); // false
        System.out.println(isLowerCaseWord("hello")); // true
        System.out.println(isLowerCaseWord("hI")); // false
        System.out.println(isUpperCaseWord("TEST")); // true
        System.out.println(isUpperCaseWord("TEST ")); // false
        System.out.println(isUpperCaseWord("tEST")); // false
        System.out.println(isSarcasmCaseWord("tEsT")); // true
        System.out.println(isSarcasmCaseWord("TeSt")); // true
        System.out.println(isSarcasmCaseWord("test")); // false
        System.out.println(isSarcasmCaseWord("TeST")); // false
        
    }

    public static boolean isWord(String string) {
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (!Character.isLetter(c)) {
                return false;
            }
            else if (Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLowerCaseWord(String string) {
        for (int i = 0; i < string.length(); i ++) {
            char c = string.charAt(i);
            if (!Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isUpperCaseWord(String string) {
        for (int i = 0; i < string.length(); i ++) {
            char c = string.charAt(i);
            if (!Character.isUpperCase(c)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isSarcasmCaseWord(String string) {
        if (!isWord(string)) {
            return false;
        }
        for (int i = 0; i < string.length() - 1; i++) {
            char c = string.charAt(i);
            char nextChar = string.charAt(i+1);
            if (Character.isUpperCase(c)) {
                if (Character.isUpperCase(nextChar)) {
                    return false;
                }
            } else {
                if (Character.isLowerCase(nextChar)) {
                    return false;
                }
            }
        }
        return true;
    }

}








/* 
Notes:
- Java Api Character Class may be useful

Requirements/Process:
- Implement a method 'isWord(String string)' that checks if an input string is a word (only upper and lowercase A-Z (no symbols))
- Implement 'isLowerCaseWord(String string)' that tests if input is lowercase only
- Implement 'isUpperCaseWord(String string)' with same logic
- Implement 'isSarcasmCaseWord(String string)' 
    - Return false if input is not a word (call isWord)
    - Checks if sarcastic case (alternating upper and lower case)

Tests:
    System.out.println(isWord("Hello")); // true
    System.out.println(isWord("Hello!")); // false
    System.out.println(isWord("H ello")); // false
    System.out.println(isLowerCaseWord("hello")); // true
    System.out.println(isLowerCaseWord("hI")); // false
    System.out.println(isUpperCaseWord("TEST")); // true
    System.out.println(isUpperCaseWord("TEST ")); // false
    System.out.println(isUpperCaseWord("tEST")); // false
    System.out.println(isSarcasmCaseWord("tEsT")); // true
    System.out.println(isSarcasmCaseWord("TeSt")); // true
    System.out.println(isSarcasmCaseWord("test")); // false
    System.out.println(isSarcasmCaseWord("TeST")); // false
*/