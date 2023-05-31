import java.util.ArrayList;

public class Agent {
    Env env;
    SearchAlgo main_algo;
    ArrayList<SearchAlgo> algo_list = new ArrayList<SearchAlgo>();
    public Agent (Env env, String algo_name) {
        SearchAlgo bfs = new BFS(env);
        System.out.println(bfs.name);
        SearchAlgo dfs = new DFS((env));
        algo_list.add(dfs);
        algo_list.add(bfs);
        this.env = env;
        this.main_algo = algo_lookup(algo_name);

    }

    public ArrayList<String> get_path() {
        ArrayList<String> result;
        if(main_algo!= null) {
            result = main_algo.calculate_path();

        } else {
            result = new ArrayList<>();
            result.add("there is no matching algorithm name");
        }
        return result;
    }

    public SearchAlgo algo_lookup(String algo_name) {
        SearchAlgo result = null;
        for(SearchAlgo algo:algo_list) {

            if (algo.name.equals(algo_name)) {
                result = algo;
            }
        }
        return result;
    }



}
