import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class A_star extends SearchAlgo{
    public A_star(Env env) {
        super(env);

        super.name = "A*";
    }

    public void heuristic(State looked, int cost) {
        for(int[] desti_loc : env.desti_list) {
            looked.path_cost.add(looked.manhattan_cost_to_des(desti_loc[1], desti_loc[0]) + cost);
        }
    }
    @Override
    public ArrayList<String> calculate_path() {
        ArrayList<String> result = new ArrayList<String>();

        PriorityQueue<State> pqueue = new PriorityQueue<State>(new StateComparator());



        heuristic(env.map[env.initial_pos[1]][env.initial_pos[0]], 0);


        State read = env.map[env.initial_pos[1]][env.initial_pos[0]];
        int add_cost = 0;
        while(!read.role.equals("dest")){
            add_cost = add_cost+1;
            if(!read.visited) {
                for(String key: read.available_path.keySet()){
                    if(!read.available_path.get(key).visited && !pqueue.contains(read.available_path.get(key))){
                        read.available_path.get(key).parent = read;
                        heuristic(read.available_path.get(key), add_cost);
                        pqueue.add(read.available_path.get(key));
                    }
                }
                read.visited = true;
            }
            read = pqueue.poll();
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


        return result ;
    }
}
