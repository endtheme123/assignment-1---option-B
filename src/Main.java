import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Env env = new Env("D:\\sem3-2\\cos30019\\ass1\\assignment 1 - option B\\RobotNav-test.txt");
        env.generate_map();
        env.print_map();

        Agent p = new Agent(env, "A*");
        ArrayList<String> directions = p.get_path();
        String way_to_go = String.join("; ", directions);
        System.out.println(way_to_go);


    }
}