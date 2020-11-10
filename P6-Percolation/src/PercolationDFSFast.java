public class PercolationDFSFast extends PercolationDFS {

    public PercolationDFSFast(int n) {
        super(n);
    }

    @Override
    protected void updateOnOpen(int row, int col) {
        if (row == 0) dfs(row, col);
        if (isFull(row, col)) return;

        else {
            int[] rr = {0, 0, -1, 1};
            int[] cc = {-1, 1, 0, 0};
            for (int k = 0; k < rr.length; k++) {
                int nxtR = row + rr[k];
                int nxtC = col + cc[k];
                if ((inBounds(nxtR, nxtC) && isFull(nxtR, nxtC)) ) {
                    dfs(row, col);
                    break;
                }

            }
        }
    }
}
