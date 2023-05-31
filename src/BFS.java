import java.util.*;

public class BFS extends SearchAlgo{




    public BFS(Env env) {
        super(env);
        super.name = "BFS";
    }

    @Override
    public ArrayList<String> calculate_path() {
        ArrayList<String> result = new ArrayList<>();
        Queue<State> queue = new LinkedList<>();
        queue.add(env.map[env.initial_pos[1]][env.initial_pos[0]]);
        env.map[env.initial_pos[1]][env.initial_pos[0]].role = "init";
//        env.print_map_parent();
        State read = queue.poll();
//        for(String key : read.available_path.keySet()) {
//            System.out.println("---");
//            System.out.println(key);
//            System.out.println("---");
//
//        }
        while(!read.role.equals("dest")) {
//            System.out.println(read.available_path.get("right").role);
            if(!read.visited) {

                for(String key : read.available_path.keySet()) {
//                    System.out.println(key);

                    if(!read.available_path.get(key).visited){
                        read.available_path.get(key).parent = read;
                        if(!read.available_path.get(key).equals(read.parent)){
//                            System.out.println(read.pos[0] + ", " + read.pos[1]);
                            queue.add(read.available_path.get(key));
                        }

                    }

                }

                read.visited = true;
                read.role = "visi";
//                env.print_map_parent();
//                System.out.println(read.visited);
            }


            read = queue.poll();

        }
        System.out.println(env.map[env.initial_pos[1]][env.initial_pos[0]].parent);
        while(!read.equals(env.map[env.initial_pos[1]][env.initial_pos[0]])) {
            State parent = read.parent;
//            System.out.println("im good");
            for(String key:parent.available_path.keySet()){
                if(parent.available_path.get(key).equals(read)){
//                    System.out.println(read.pos[0] + ", " + read.pos[1]);
//                    read.role = "back";
//                    env.print_map();
                    result.add(key);
                }
            }
            read = read.parent;
        }
        Collections.reverse(result);
        return result;
    }
}
