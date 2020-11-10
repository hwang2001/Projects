import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast{

    PercolationBFS(int size) {
        super(size);
    }

    @Override
    public void dfs(int row, int col) {
        int[] rDelta = {-1,1,0,0};
        int[] cDelta = {0,0,-1,1};


        Queue<int[]> pq = new LinkedList<>();
        myGrid[row][col] = FULL;  // mark pixel
        pq.add(new int[]{row,col});
        while (pq.size() != 0){
            int[] rem = pq.remove();
            for(int k=0; k < rDelta.length; k++){
                row = rem[0] + rDelta[k];
                col = rem[1] + cDelta[k];
                if (inBounds(row,col) && myGrid[row][col] == OPEN){
                    pq.add(new int[]{row,col});
                    myGrid[row][col] = FULL;
                }
            }
        }

    }

}

