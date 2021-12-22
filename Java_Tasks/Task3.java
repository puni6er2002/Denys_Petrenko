public class Task3 {
    public static int digitalRoot(int num){
       int result;
        String str = String.valueOf(num);
        do {
            result = 0;
            for (int i = 0; i < str.length(); i++) {
                int n = Character.getNumericValue(str.charAt(i));
                sum += n;
            }
            str = String.valueOf(result);
        } while (result > 9);
        return result;
    }
}
