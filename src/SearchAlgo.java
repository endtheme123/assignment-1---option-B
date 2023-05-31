import java.util.ArrayList;
import java.util.HashMap;

public abstract class SearchAlgo {
    String name;
    Env env;
    public SearchAlgo(Env env) {
        this.env = env;
    }

    public abstract ArrayList<String> calculate_path();

}
