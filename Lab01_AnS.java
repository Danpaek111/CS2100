//jff7dm Daniel Paek
public void main(){

}
public class Lab01_AnS {
    public static int findLongest (String[] arr){
        int longest = 0;
        /*Starting at i = 1 because I already picked index 0 as the current
        longest when I set int longest to 0*/
        for (int i = 1; i < arr.length; i++){
            if(arr[i].length() > arr[longest].length()){
                    longest = i;
            }
        }
        return longest;
    }
    public static int countSubstring (String[] arr, String str){
        int count = 0;
        int m = str.length();
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            for (int j = 0; j <= s.length() - m; j++) {
                if (s.substring(j, j + m).equals(str)) {
                    count++;
                }
            }
        }
        return count;
    }
    public static void removeSubstring (String[] arr, String str){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].replace(str, "");
        }
    }
    public static String[] removeDup (String[] arr) {
        String[] copy = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        String[] temp = new String[copy.length];
        int count = 0;
        for (int i = 0; i < copy.length; i++) {
            boolean seen = false;
            for (int j = 0; j < i; j++) {
                if (copy[i].equals(temp[j])) {
                    seen = true;
                    break;
                }
            }
            if (!seen) {
                temp[count] = copy[i];
                count++;
            }
        }
        String[] result = new String[count];
        for (int i = 0; i < count; i++) {
            result[i] = temp[i];
        }
        return result;
    }
}
