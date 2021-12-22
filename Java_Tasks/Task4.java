import java.util.Arrays;

class Task4 {
    public static int CountNumberOfPairs(int[] arr, int target_numb, boolean Streamquestion) {
      var count = 0;
      if(Streamquestion){
        count = ((int)Arrays.stream(arr).flatMap(i -> Arrays.stream(arr)
                .map(j -> i + j)).filter(i -> i == target_numb).count()- (int)Arrays.stream(arr).map(i -> 2*i)
                .filter(i -> i == target_numb).count())/2;
        } 
      else{
            for(int i = 0; i < arr.length-1; i++){
                for(int j = i+1; j < arr.length; j++){
                    if(arr[i]+arr[j] == target_numb){
                        counter++;
                    }
                }
            }
        }
        return count;
    }
