import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class State {
    int[] pos;

    State parent;
    boolean visited = false;
    String role = "";
    State right;
    State left;
    State up;

    State down;

    LinkedHashMap<String,State> available_path =   new LinkedHashMap<>();




    public void get_available_path() {
//        System.out.println("work");
        if (right != null) {
            available_path.put("right",right);
        }

        if (down != null) {
            available_path.put("down",down);
        }

        if (left != null) {
            available_path.put("left",left);
        }

        if (up != null) {

            available_path.put("up",up);
        }










    }

}
