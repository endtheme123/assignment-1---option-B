import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class GBFS extends SearchAlgo {

    public GBFS(Env env) {
        super(env);

        super.name = "GBFS";
    }

    public void heuristic(State looked) {
        for(int[] desti_loc : env.desti_list) {
            looked.path_cost.add(looked.manhattan_cost_to_des(desti_loc[1], desti_loc[0]));
        }
    }
    @Override
    public ArrayList<String> calculate_path() {
        ArrayList<String> result = new ArrayList<String>();

        PriorityQueue<State> pqueue = new PriorityQueue<State>(new StateComparator());



        heuristic(env.map[env.initial_pos[1]][env.initial_pos[0]]);


        State read = env.map[env.initial_pos[1]][env.initial_pos[0]];

        while(!read.role.equals("dest")){
            if(!read.visited) {
                for(String key: read.available_path.keySet()){
                    if(!read.available_path.get(key).visited && !pqueue.contains(read.available_path.get(key))){
                        read.available_path.get(key).parent = read;
                        heuristic(read.available_path.get(key));
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
                    read.role = "back";
                    env.print_map();
                    System.out.println("___________");
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

class StateComparator implements Comparator<State>{

    public int compare(State s1, State s2){
        int minest = s1.path_cost.get(0);
        State minState = s1;
        for(int x : s1.path_cost) {
            if(x<minest){
                minest = x;
                minState = s1;
            }
        }
        for(int x : s2.path_cost) {
            if(x<minest){
                minest = x;
                minState = s2;
            }
        }

        if(minState.equals(s1)){
            return -1;
        } else if (minState.equals(s2)) {
            return 1;
        }


        return 0;

    }
}