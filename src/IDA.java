import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

public class IDA extends SearchAlgo{
    public IDA(Env env) {
        super(env);
        super.name = "IDA";
    }

    public void heuristic(State looked, int cost) {
        for(int[] desti_loc : env.desti_list) {
            looked.path_cost.add(looked.manhattan_cost_to_des(desti_loc[1], desti_loc[0]) + cost);
        }
    }

    public int min(ArrayList<State> pruned){
        int min = -1;

        if(!pruned.isEmpty()){
            min = pruned.get(0).get_representative_();

            for(State pruned_state: pruned){
                if(pruned_state.get_representative_()<= min){
                    min = pruned_state.get_representative_();
                }
            }
        }

        return min;

    }
    @Override
    public ArrayList<String> calculate_path() {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<State> pruned = new ArrayList<>();


        heuristic(env.map[env.initial_pos[1]][env.initial_pos[0]], 0);
        State read = env.map[env.initial_pos[1]][env.initial_pos[0]];
        pruned.add(read);
        int add_cost = 0;

        while(!read.role.equals("dest")){
//            System.out.println("***************************");
            add_cost = add_cost +1;
            int deep_cost = add_cost;
            Stack<State> stack = new Stack<>();
            ArrayList<State> to_be_removed = new ArrayList<>();
            int threshold = this.min(pruned);
            System.out.print("threshold: " + threshold);
            for(State potential: pruned){
                if(potential.get_representative_()<=threshold){
                    stack.push(potential);
                    to_be_removed.add(potential);
                }
            }

            for(State remove:to_be_removed){
                pruned.remove(remove);
            }
            while(!stack.empty()){

                read = stack.pop();

                if(read.role.equals("dest")){
                    break;
                }

                if(!read.visited){
                    for(String key: read.available_path.keySet()){
                        if(!read.available_path.get(key).visited){
                            if(!stack.contains(read.available_path.get(key)) && !pruned.contains(read.available_path.get(key))){
                                read.available_path.get(key).parent = read;
                                heuristic(read.available_path.get(key),deep_cost);
                                System.out.println(read.available_path.get(key).get_representative_());
                                if(read.available_path.get(key).get_representative_()<= threshold){

                                    stack.push(read.available_path.get(key));
                                    System.out.println("block added to stack: " +read.available_path.get(key).pos[0] +","+ read.available_path.get(key).pos[0]);
                                    System.out.println("stack size: "+ stack.size());
//                                    read.available_path.get(key).role = "stac";
                                }else{

                                    pruned.add(read.available_path.get(key));
//                                    read.available_path.get(key).role = "prun";
                                    System.out.println("pruned_size: " + pruned.size());
                                }
                            }

                        }
                    }
                    read.visited = true;
//                    read.role = "visi";
//                    env.print_map();
                }
                deep_cost = deep_cost + 1;




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
            System.out.println("___________");
            env.print_map_cost();
            read = read.parent;
        }
        Collections.reverse(result);


        return result;
    }
}
