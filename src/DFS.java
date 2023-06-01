import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class DFS extends SearchAlgo{
    public DFS(Env env) {
        super(env);
        super.name = "DFS";
    }

    @Override
    public ArrayList<String> calculate_path() {
        ArrayList<String> result = new ArrayList<>();
        Stack<State> stack = new Stack<>();

        for(String key: env.map[env.initial_pos[1]][env.initial_pos[0]].available_path.keySet()){
            System.out.println(key);
        }

        stack.push(env.map[env.initial_pos[1]][env.initial_pos[0]]);

        State read = stack.pop();

        while(!read.role.equals("dest")){
            if(!read.visited){

                for(String key: read.available_path.keySet()) {
                    if(!read.available_path.get(key).visited && !stack.contains(read.available_path.get(key))){
                        read.available_path.get(key).parent = read;
                        stack.push(read.available_path.get(key));
                    }
                }
                read.visited = true;
            }
            read.role = "visi";
            read = stack.pop();
//            env.print_map_parent();
//            System.out.println("____________");

        }
        System.out.println("im good");
        while(read.parent != null) {
            for(String key: read.parent.available_path.keySet()){
                if(read.parent.available_path.get(key).equals(read)){
                    result.add(key);
//                    read.role = "back";
//                    env.print_map();
//                    System.out.println("___________");
                }
            }

            read = read.parent;
        }
        Collections.reverse(result);
        return result;


    }
}
