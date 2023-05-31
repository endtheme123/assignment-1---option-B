import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class Env {
    String filepath;
    State[][] map;
    int[] initial_pos;
    public Env(String filepath) {
        this.filepath = filepath;
        generate_map();
    }

    public void generate_map() {
        try{
            File myObj = new File(filepath);

            Scanner myReader = new Scanner(myObj);
            String map_wide = myReader.nextLine();
            String map_wide_p = map_wide.trim().replaceAll("\\[", "").replaceAll("\\]", "");
            String[] map_wide_p2 = map_wide_p.split(",");

            this.map = new State[parseInt(map_wide_p2[0])][parseInt(map_wide_p2[1])];
            for(int i = 0; i<map.length;i++){
                for(int j = 0; j < map[0].length; j++){
                    map[i][j] = new State();
                    map[i][j].role = "path";
                    map[i][j].pos = new int[]{i,j};
                }
            }

            String initial = myReader.nextLine();
            String initial_p = initial.trim().replaceAll("\\(", "").replaceAll("\\)", "");

            this.initial_pos = Arrays.stream(initial_p.split(",")).mapToInt(Integer::parseInt).toArray();



            String desti = myReader.nextLine();
            String desti_p = desti.trim().replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(" ","");
            String[] desti_p2 = desti_p.split("\\|");
            int[][] desti_list = new int[desti_p2.length][];
            for(int i = 0; i<desti_p2.length;i++){
                int[] a = Arrays.stream(desti_p2[i].split(",")).mapToInt(Integer::parseInt).toArray();
                desti_list[i] = a;
            }

            for(int i = 0; i<desti_list.length;i++){
                String test = "";
                for(int j = 0; j < desti_list[0].length; j++){
                    test = test + " " +desti_list[i][j];
                }
                System.out.println(test);
            }


            for(int[] des:desti_list){
                map[des[1]][des[0]].role = "dest";
            }
            while(myReader.hasNextLine()) {
                String wall = myReader.nextLine();
                wall = wall.trim().replaceAll("\\(", "").replaceAll("\\)", "");
                int[] wall_loc = Arrays.stream(wall.split(",")).mapToInt(Integer::parseInt).toArray();
                for(int i = wall_loc[0]; i<wall_loc[0]+wall_loc[2];i++){
                    for(int j = wall_loc[1]; j < wall_loc[1]+ wall_loc[3]; j++){
                        map[j][i].role = "wall";
                    }
                }
            }

            for(int i = 0; i<map.length;i++){
                for(int j = 0; j < map[0].length; j++){

                    if(i-1>=0) {
//                        System.out.println("working1");
                        if (!map[i-1][j].role.equals("wall")){
                            map[i][j].up = map[i-1][j];
                        }
                    }

                    if(i+1<map.length) {
//                        System.out.println("working2");
                        if (!map[i+1][j].role.equals("wall")){
                            map[i][j].down = map[i+1][j];
                        }
                    }

                    if(j-1>=0){
//                        System.out.println("working3");
                        if(!map[i][j-1].role.equals("wall")){
                            map[i][j].left = map[i][j-1];
                        }
                    }

                    if(j+1<map[0].length) {
//                        System.out.println("working4");
                        if (!map[i][j+1].role.equals("wall")){
                            map[i][j].right = map[i][j+1];
                        }
                    }
//                    System.out.println(map[i][j].down.role);
                }
            }

            for(int i = 0; i<map.length;i++){
                for(int j = 0; j < map[0].length; j++){
                    map[i][j].get_available_path();

                }
            }

//            map[initial_pos[0]][initial_pos[1]].role = "init";

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }
    }

    public void print_map(){
        for(int i = 0; i<map.length;i++){
            String a = "";
            for(int j = 0; j < map[0].length; j++){
                a = a + " " + map[i][j].role;
            }
            System.out.println(a);
        }
    }

    public void print_map_parent(){
        for(int i = 0; i<map.length;i++){
            String a = "";
            for(int j = 0; j < map[0].length; j++){
                a = a + " " + ((map[i][j].parent!=null)? map[i][j].parent.pos[0]+","+map[i][j].parent.pos[1] : "nul");
            }
            System.out.println(a);
        }
    }

}
