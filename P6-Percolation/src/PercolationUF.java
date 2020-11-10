public class PercolationUF implements IPercolate{

    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;

    private int mySize;

    public PercolationUF( IUnionFind finder, int size) {
        mySize = size;
        VTOP = mySize * mySize;
        VBOTTOM = mySize * mySize + 1;
        myGrid = new boolean[mySize][mySize];
        myFinder = finder;
        myFinder.initialize(mySize * mySize + 2);
    }


    @Override
    public void open(int row, int col) {
        checkBounds(row, col);

        //if already open
        if (myGrid[row][col])
            return;

        myGrid[row][col] = true;
        myOpenCount++;

        int ape = pairInt(row,col);
        if (row == 0)
            myFinder.union(ape, VTOP);
        if (row == mySize - 1)
            myFinder.union(ape, VBOTTOM);

        int[] dR = {0,0,-1,1};
        int[] dC = {-1,1,0,0};

        for (int k=0; k<dR.length; k++) {
            int nR = row + dR[k];
            int nC = col + dC[k];

            if (inBounds(nR, nC) && isOpen(nR, nC)) {
                myFinder.union(ape, pairInt(nR, nC));
            }
        }
    }

    private void checkBounds(int row, int col) {
        if (!inBounds(row,col))
            throw new IndexOutOfBoundsException("Inputs must be in a " + mySize + "x" + mySize + " grid's bounds");
    }

    private boolean inBounds(int r, int c) {
        return r >= 0 && c >= 0 && r < myGrid.length && c < myGrid[0].length;
    }

    private int pairInt(int row, int col) {
        return row * mySize + col;
    }

    @Override
    public boolean isOpen(int row, int col) {
        checkBounds(row, col);
        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        checkBounds(row, col);
        return myFinder.connected(VTOP, pairInt(row,col));
    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }
}
