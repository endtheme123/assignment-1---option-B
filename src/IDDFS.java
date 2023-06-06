import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class IDDFS extends SearchAlgo{
    public IDDFS(Env env){
        super(env);
        super.name = "IDDFS";
    }

    @Override
    public ArrayList<String> calculate_path() {
        ArrayList<String> result = new ArrayList<String>();




        State read = env.map[env.initial_pos[1]][env.initial_pos[0]];
        Stack<State> stack = new Stack<>();
//        Stack<State> stack = new Stack<>();
//        stack.push(read);
//        read.depth = 0;
        int depth = 0;
        while(!read.role.equals("dest")){
            depth = depth+1;
//            System.out.println("***************************");
            read = env.map[env.initial_pos[1]][env.initial_pos[0]];
            env.reset_map();
            stack.push(read);
            read.depth = 0;
//            Stack<State> stack = new Stack<>();




            while(!stack.empty()){

                read = stack.pop();

                if(read.role.equals("dest")){
                    break;
                }

                if(!read.visited && read.depth <= depth){
                    for(String key: read.available_path.keySet()){
                        if(!read.available_path.get(key).visited){
                            if(!stack.contains(read.available_path.get(key))){
                                read.available_path.get(key).parent = read;
                                env.print_map_parent();
                                System.out.println("___________");
                                read.available_path.get(key).depth = read.depth + 1;
                                stack.push(read.available_path.get(key));
                            }

                        }
                    }
                    read.visited = true;
//                    read.role = "visi";
//                    env.print_map();
                }





            }
//            System.out.println("gg");
        }


        System.out.println("im good");
        while(!read.equals(env.map[env.initial_pos[1]][env.initial_pos[0]])) {
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
