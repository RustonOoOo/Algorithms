import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by abc84 on 2016/3/20.
 */
public class Outcast {
    private WordNet wordNet;
    public Outcast(WordNet wordnet) {// constructor takes a WordNet object
        this.wordNet = wordnet;
    }
    public String outcast(String[] nouns) {// given an array of WordNet nouns, return an outcast
        int maxDist = 0;
        String outcasr = null;
        for (int i=0;i<nouns.length;i++) {
            int dis = 0;
            for (int j = 0;j<nouns.length;j++) {
                dis += wordNet.distance(nouns[i],nouns[j]);
            }
            if (dis > maxDist){
                maxDist = dis;
                outcasr = nouns[i];
            }
        }
        return outcasr;
    }
    public static void main(String[] args) {
        String f1 = "D:/BaiduYunDownload/CS基础/wordnet-testing/wordnet/synsets.txt";
        String f2 = "D:/BaiduYunDownload/CS基础/wordnet-testing/wordnet/hypernyms.txt";
        WordNet wordnet = new WordNet(f1, f2);
        Outcast outcast = new Outcast(wordnet);
        String wordsF = "D:/BaiduYunDownload/CS基础/wordnet-testing/wordnet/outcast11.txt";
        String[] nouns = new In(wordsF).readAllStrings();
        System.out.println(outcast.outcast(nouns));
    }
}