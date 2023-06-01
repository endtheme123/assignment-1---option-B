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

    ArrayList<Integer> path_cost = new ArrayList<Integer>();
    LinkedHashMap<String,State> available_path =   new LinkedHashMap<>();


    public int manhattan_cost_to_des(int des_x, int des_y){
        System.out.println((this.pos[0]) + "," + this.pos[1]);
        System.out.println(des_x + "," + des_y);
        System.out.println("_____");
        return (Math.abs(this.pos[0] - des_x) + Math.abs(this.pos[1]-des_y));
    }



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
