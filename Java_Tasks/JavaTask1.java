import java.util.ArrayList;
import java.util.List;

public class Task1 {
    public static List<Integer> getIntegersFromList(List<Object> Itemlist) {
      ArrayList<Integer> res = new ArrayList<Integer>();
        for(Object element: Itemlist){
            if(element instanceof Integer){
                res.add((Integer) element);
            }
        }
        return res;
    }
