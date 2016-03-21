/**
 * Created by abc84 on 2016/3/19.
 */
//WordNet data type. Implement an immutable data type WordNet with the following API:
import edu.princeton.cs.algs4.*;

import java.io.File;
import java.util.*;

public class WordNet {

    private HashMap<Integer,String> idMapToWord = new HashMap<>();//id对单词集的映射
    private HashMap<String,Bag<Integer>>  wordMapToId = new HashMap<>();//单个单词对id映射，不同的id
    private Digraph digraph;
    private int numOfVertices;
    private SAP sap;

    // constructor takes the name of the two input files

    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) throw new NullPointerException();
        File syn = new File(synsets);
        In in = new In(syn);
        String line = "";
        //0,'hood,(slang) a neighborhood

        while ((line = in.readLine()) != null) {
            numOfVertices ++;
            String[] info = line.split(",");
            int id = Integer.parseInt(info[0]);
            String name = info[1];
            idMapToWord.put(id, name);

            for (String word : info[1].split(" ")) {
                if (wordMapToId.get(word) == null) {
                    Bag<Integer> ids = new Bag<>();
                    ids.add(id);
                    wordMapToId.put(word, ids);
                }
                else {
                    wordMapToId.get(word).add(id);
                }
            }
        }
        line = "";
        digraph = new Digraph(numOfVertices);
        in = new In(hypernyms);
        //0,1,2,3...
        while ((line = in.readLine()) != null) {
            String[] nums = line.split(",");
            int V = Integer.parseInt(nums[0]);
            for (int i=1;i<nums.length;i++) {
                int num = Integer.parseInt(nums[i]);
                digraph.addEdge(V, num);
            }
        }

        if (hasCycle(digraph) || !isRooted(digraph))throw new IllegalArgumentException();
        sap = new SAP(digraph);//init sap
    }
    private boolean isRooted(Digraph digraph) {
        int root = 0;
        for (int i=0;i<digraph.V();i++) {
            if (!digraph.adj(i).iterator().hasNext()){
                root ++;
            }
        }
        return root==1;
    }
    private boolean hasCycle(Digraph digraph) {
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        return directedCycle.hasCycle();
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
       return wordMapToId.keySet();
    }
    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return wordMapToId.keySet().contains(word);
    }
    // distance between nounA and nounB (defined below) {

    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA)||!isNoun(nounB)) throw new IllegalArgumentException();

        return sap.length(wordMapToId.get(nounA),wordMapToId.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA)||!isNoun(nounB)) throw new IllegalArgumentException();
        return idMapToWord.get(sap.ancestor(wordMapToId.get(nounA),wordMapToId.get(nounB)));
    }

    // do unit testing of this class
    public static void main(String[] args) {

        String synFile= "D:/BaiduYunDownload/CS基础/wordnet-testing/wordnet/synsets.txt";
        String hypFile = "D:/BaiduYunDownload/CS基础/wordnet-testing/wordnet/hypernyms.txt";
        WordNet wordNet = new WordNet(synFile, hypFile);

        while (!StdIn.isEmpty()) {
            String nounA = StdIn.readString();
            String nounB = StdIn.readString();

            if (!wordNet.isNoun(nounA)) {
                StdOut.printf("%s is not a noun!\n", nounA);
                continue;
            }

            if (!wordNet.isNoun(nounB)) {
                StdOut.printf("%s is not a noun!\n", nounB);
                continue;
            }

            int distance = wordNet.distance(nounA, nounB);
            String ancestor = wordNet.sap(nounA, nounB);

            StdOut.printf("distance = %d, ancestor = %s\n", distance, ancestor);
        }
    }

}