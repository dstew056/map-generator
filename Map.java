import java.util.*  ;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.Random;

public class Map {

    private boolean[][] map;
    int width;
    int height;
    double chance;
    ArrayBlockingQueue<Step> steps;

    public Map() {
        width=10;
        height=10;
        chance=0.3;
        steps = new ArrayBlockingQueue<Step>(width*height);
        buildMap();
    }

    private void buildMap(){
        map = new boolean[width][height];
        for (int i=0;i<map.length;i++){
            for(int k=0;k<map[0].length;k++){
                map[i][k]=false;
            }
        }

        boolean flag=true;
        int x = width/2;
        int y = height/2;
        map[x][y] = true;
        map[x+1][y] = true;
        steps.add(new Step(3,x+1,y));
        map[x-1][y] = true;
        steps.add(new Step(1,x-1,y));
        map[x][y+1] = true;
        steps.add(new Step(2,x,y+1));
        map[x][y-1] = true;
        steps.add(new Step(0,x,y-1));

        Random r = new Random();

        while(steps.peek()!=null){
            Step current = steps.remove();
            if(current.origin!=0 && r.nextDouble()<chance && current.y!=height-1) {
                map[current.x][current.y] = true;
                steps.add(new Step(2, current.x, current.y+1));
            }
            if(current.origin!=1 && r.nextDouble()<chance && current.x!=width-1) {
                map[current.x][current.y] = true;
                steps.add(new Step(3, current.x+1, current.y));
            }
            if(current.origin!=2 && r.nextDouble()<chance && current.y!=0) {
                map[current.x][current.y] = true;
                steps.add(new Step(0, current.x, current.y-1));
            }
            if(current.origin!=3 && r.nextDouble()<chance && current.x!=0) {
                map[current.x][current.y] = true;
                steps.add(new Step(1, current.x-1, current.y));
            }
            print();
        }
        print();
    }

    private void print(){
        for(int y=height-1;y>=0;y--) {
            for (int x = 0; x < height; x++) {
                System.out.print(map[x][y] ? 1 : 0);
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
