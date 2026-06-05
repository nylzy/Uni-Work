public String reverse(String str) {
    String reversed = "";
    for (int i = str.length() - 1; i >= 0; i--) {
        reversed = reversed + str.charAt(i);
    }
    return reversed;
}

public int countPalindromes(String[] strings) {
    int palindromeCount = 0;
    for (int i = 0; i < strings.length; i++) {
        if (strings[i].equals(reverse(strings[i]))) {
            palindromeCount ++;
        }
    }
    return palindromeCount;

}