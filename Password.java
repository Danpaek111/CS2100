// Name: Daniel Paek
// Computing ID: jff7dm@virginia.edu
// Homework Name: HW 8 - Passwords
//Resources used: None
public class Password {
    private String curPassword;
    //No-arg constructor
    public Password() {
        this.curPassword = "";
    }
    /*
     Constructs a Password with an initial value.
     If the provided password is invalid, defaults to the empty string.
     */
    public Password(String initial){
        this.curPassword = "";
        if (initial != null && containsDigitRecursive(initial, 0)){
            this.curPassword = initial;
        }
    }
    //getter method
    public String getPassword(){
        return this.curPassword;
    }
    //Attempts to change the password
    public boolean setPassword(String newPassword){
        if (newPassword == null){
            return false;
        }
        // must contain a digit
        if (!containsDigitRecursive(newPassword, 0)){
            return false;
        }
        // cannot be equal to old password (recursive equality check)
        if (isEqualToCurrentRecursive(newPassword)){
            return false;
        }
        // must differ in at least half of the positions of the old password
        int minDiff = (this.curPassword.length() + 1) / 2; // "at least half" (ceil)
        if (!sufficientlyDifferent(newPassword, minDiff)){
            return false;
        }
        this.curPassword = newPassword;
        return true;
    }
    @Override
    public boolean equals(Object other){
        if (!(other instanceof Password)){
            return false;
        }
        Password o = (Password) other;
        if (this.curPassword == null && o.curPassword == null){
            return true;
        }
        if (this.curPassword == null || o.curPassword == null){
            return false;
        }
        return this.curPassword.equals(o.curPassword);
    }
    public String toString(){
        if (curPassword == null || curPassword.length() == 0){
            return "";
        }
        char first = curPassword.charAt(0);
        char last = curPassword.charAt(curPassword.length() - 1);
        int stars = 1 + (int)(Math.random() * 20); // 1..20 inclusive
        String mid = "";
        for (int i = 0; i < stars; i++) {
            mid += "*";
        }
        return "" + first + mid + last;
    }
    //Private recursive method to check if a password contains at least one digit.
    private boolean containsDigitRecursive(String s, int idx) {
        if (s == null){
            return false;
        }
        if (idx >= s.length()){
            return false;
        }
        char c = s.charAt(idx);
        if (c >= '0' && c <= '9'){
            return true;
        }
        return containsDigitRecursive(s, idx + 1);
    }
    //Private recursive method that checks if a candidate password equals the current password.
    private boolean isEqualToCurrentRecursive(String candidate){
        return equalsRecursive(candidate, this.curPassword, 0);
    }
    //Recursive equality check between two strings
    private boolean equalsRecursive(String a, String b, int idx) {
        if (a == null || b == null){
            return a == b;
        }
        // If lengths differ, cannot be equal
        if (a.length() != b.length()){
            return false;
        }
        if (idx >= a.length()){
            return true; // reached end, all matched
        }
        if (a.charAt(idx) != b.charAt(idx)){
            return false;
        }
        return equalsRecursive(a, b, idx + 1);
    }
    //Private recursive method that returns the number of positions where two passwords differ.
    private int differenceCountRecursive(String a, String b) {
        if (a == null) a = "";
        if (b == null) b = "";
        return differenceCountRecursive(a, b, 0);
    }

    //Helper recursive method for difference counting.
    private int differenceCountRecursive(String a, String b, int idx) {
        int lenA = a.length();
        int lenB = b.length();
        if (idx >= lenA && idx >= lenB){
            return 0;
        }
        int diff = 0;
        if (idx >= lenA || idx >= lenB){
            diff = 1; // one string ran out; remaining chars count as differences
        }
        else {
            diff = (a.charAt(idx) == b.charAt(idx)) ? 0 : 1;
        }
        return diff + differenceCountRecursive(a, b, idx + 1);
    }
    //Private non-recursive method that returns whether the new password and current password are different.
    private boolean sufficientlyDifferent(String candidate, int minDifferences) {
        int diffs = differenceCountRecursive(candidate, this.curPassword);
        return diffs >= minDifferences;
    }
}
