package src;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import javax.swing.text.DefaultEditorKit.CopyAction;

public class Board {
	private int dimention;
	private int[][]blocks;
    public Board(int[][] blocks) {
    	// construct a board from an N-by-N array of blocks
    	dimention = blocks.length;
    	this.blocks = new int[dimention][dimention];
    	for(int i=0;i<dimention;i++){
    		for(int j=0;j<dimention;j++){
    				this.blocks[i][j] = blocks[i][j];
    		}
    	}
    }
                                           // (where blocks[i][j] = block in row i, column j)
    public int dimension() {
    	// board dimension N
    	return dimention;
    }
    public int hamming() {
    	// number of blocks out of place
    	int hamming = 0;
    	for(int i=0;i<dimention;i++){
    		for(int j=0;j<dimention;j++){
    			if(blocks[i][j]!=i*dimention+j+1&&blocks[i][j]!=0)
    				hamming++;
    		}
    	}
    	return hamming;
    }
    public int manhattan() {
    	// sum of Manhattan distances between blocks and goal
    	int sum = 0;
    	for(int i=0;i<dimention;i++){
    		for(int j=0;j<dimention;j++){
    			if(blocks[i][j]!=i*dimention+j+1&&blocks[i][j]!=0){
    				int row = blocks[i][j]/dimention;
    				int col = blocks[i][j] - row*dimention - 1;
    				if(col < 0) {
    					col = dimention - 1;
    					row -= 1;
    				}
    				sum += Math.abs(i-row) + Math.abs(j - col);
    			}
    		}
    	}
    	return sum;
    }
    public boolean isGoal() {
    	// is this board the goal board?
    	return hamming()==0;
    }
    public Board twin()  {
    	// a board that is obtained by exchanging any pair of blocks
    	int[][] copy = copyBlocks();
    	if(blocks[0][0]!=0&&blocks[0][1]!=0){
    		copy[0][0] = blocks[0][1];
    		copy[0][1] = blocks[0][0];
    	}
    	else {
    		copy[1][0] = blocks[1][1];
    		copy[1][1] = blocks[1][0];
		}
    	return new Board(copy);
    }
    private int[][] copyBlocks() {
    	int[][] copy = new int[dimention][dimention];
    	for(int i=0;i<dimention;i++){
    		for(int j=0;j<dimention;j++){
    			copy[i][j] = blocks[i][j];
    		}
    	}
    	return copy;
	}
	public boolean equals(Object y) {
    	// does this board equal y?
    	if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass())  return false;
        Board that = (Board)y;
        return Arrays.deepEquals(this.blocks, that.blocks);
    }
    public Iterable<Board> neighbors() {
    	// all neighboring boards
    	int posi = 0,posj = 0;//0 position 
    	ArrayList<Board> neighbors = new ArrayList<Board>();
    	for(int i=0;i<dimention;i++){
    		for(int j=0;j<dimention;j++){
    			if(blocks[i][j]==0){
    				posi = i;
    				posj = j;
    			}
    		}
    	}
    	if(posj>0){
    		int[][] copy = copyBlocks();
    		copy[posi][posj] = copy[posi][posj-1];
    		copy[posi][posj-1] = 0;
    		neighbors.add(new Board(copy));
    	}
    	if(posi>0){
    		int[][] copy = copyBlocks();
    		copy[posi][posj] = copy[posi-1][posj];
    		copy[posi-1][posj] = 0;
    		neighbors.add(new Board(copy));
    	}
    	if(posj<dimention - 1){
    		int[][] copy = copyBlocks();
    		copy[posi][posj] = copy[posi][posj+1];
    		copy[posi][posj+1] = 0;
    		neighbors.add(new Board(copy));
    	}
    	if (posi<dimention - 1) {
    		int[][] copy = copyBlocks();
    		copy[posi][posj] = copy[posi+1][posj];
    		copy[posi+1][posj] = 0;
    		neighbors.add(new Board(copy));
		}
    	return neighbors;
    }
    public String toString() {
    	// string representation of this board (in the output format specified below)
    	StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((int)dimention + "\n");
        for (int i = 0; i < dimention; i++) {
            for (int j = 0; j < dimention; j++) {
                stringBuilder.append(String.format("%d ", (int)blocks[i][j]));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
    	int[][]blocks = new int[][]{
    		{5,2,3},
    		{0,1,6},
    		{7,8,4}
    	};
    	// unit tests (not graded)
    	Board board = new Board(blocks);
    	for(Board b : board.neighbors()){
    		System.out.println(b);
    	}
    }
}