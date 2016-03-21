import edu.princeton.cs.algs4.*;

import java.io.File;
import java.util.Iterator;

/**
 * Created by abc84 on 2016/3/20.
 */
public class SAP {
    private final Digraph digraph;
    private BreadthFirstDirectedPaths vbfs;
    private BreadthFirstDirectedPaths wbfs;
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        digraph = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        preprocess(v, w);

        int length = Integer.MAX_VALUE;
        for (int i=0;i<digraph.V();i++) {
            int distance = vbfs.distTo(i) + wbfs.distTo(i);
            if (vbfs.hasPathTo(i) && wbfs.hasPathTo(i) && distance < length) {
                length = distance;
            }
        }
        return length == Integer.MAX_VALUE ? -1 : length;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {

        preprocess(v, w);

        int ancestor = -1;
        int length = Integer.MAX_VALUE;
        for (int i=0;i<digraph.V();i++) {
            int distance = vbfs.distTo(i) + wbfs.distTo(i);
            if (vbfs.hasPathTo(i) && wbfs.hasPathTo(i) && distance < length) {
                length = distance;
                ancestor = i;
            }
        }
        return ancestor;
    }
    /*
    preprocess before the length or ancestor of single word
     */
    private void preprocess(int v, int w) {
        vbfs = new BreadthFirstDirectedPaths(digraph,v);
        wbfs = new BreadthFirstDirectedPaths(digraph,w);
    }

    private void preprocess(Iterable<Integer> v, Iterable<Integer> w) {
        vbfs = new BreadthFirstDirectedPaths(digraph,v);
        wbfs = new BreadthFirstDirectedPaths(digraph,w);
    }


    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {

        preprocess(v, w);
        int length = Integer.MAX_VALUE;
        for (int i=0;i<digraph.V();i++) {
            int distance = vbfs.distTo(i) + wbfs.distTo(i);
            if (vbfs.hasPathTo(i) && wbfs.hasPathTo(i) && distance < length) {
                length = distance;
            }
        }
        return length == Integer.MAX_VALUE ? -1 : length;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        preprocess(v, w);

        int ancestor = -1;
        int length = Integer.MAX_VALUE;
        for (int i=0;i<digraph.V();i++) {
            int distance = vbfs.distTo(i) + wbfs.distTo(i);
            if (vbfs.hasPathTo(i) && wbfs.hasPathTo(i) && distance < length) {
                length = distance;
                ancestor = i;
            }
        }
        return ancestor;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        File file = new File("D:/BaiduYunDownload/CS基础/wordnet-testing/wordnet/digraph1.txt ");
        In in = new In(file);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}