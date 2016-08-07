import sun.plugin.javascript.navig.Array;

import java.util.*;

/**
 * Created by abc84 on 2016/5/7.
 */
/*
Five cards game
Rank:
straight flush:8
four of kind:7
full of house:6
flush:5
staight:4
three of kind:3
two of pair:2
pair:1
nothing:0
 */
public class HandCards {
    private String rep;//手牌表示
    private int rank;//Rank of the hand cards from 0--8//手牌的Rank
    private static HashMap<Character, Integer> cToInt = new HashMap<>();//对2-->A的映射
    //private List<Integer> numbers; //55532->(5,3,2)
    private Map<List<Integer>,Integer> countToRank;
            ;//牌的同值数对Rank的映射（55533）->(3,2):6
//    static {
//        /*
//        map characters to Integer value
//         */
//        String str = "23456789TJQKA";
//        char[] chars = str.toCharArray();
//        for (char c : chars) {
//            cToInt.put(c, str.indexOf(c) + 2);
//        }
//        /*
//        init countToRank
//         */
////        int fk[] = new int[]{4,1};
////        int fh[] = {3,2};
////        //int f[] = {5};
////        //int s[] = {4};
////        int tk[] = {3,1,1};
////        int tp[] = {2,2,1};
////        int p[] = {2,1,1,1};
////        int nothing[] = {1,1,1,1,1};
//
//        countToRank.put(Arrays.asList(4,1),7);//四代一
//        countToRank.put(Arrays.asList(3,2),6);//三带二
//        countToRank.put(Arrays.asList(3,1,1),3);//三代1，1
//        countToRank.put(Arrays.asList(2,2,1), 2);//两对子
//        countToRank.put(Arrays.asList(2,1,1,1),1);//一对子
//        countToRank.put(Arrays.asList(1,1,1,1,1),0);//无
//    }

    static Map<Integer, String>  RankTonames = new HashMap<>();
    static {
        RankTonames.put(0,"High Card");
        RankTonames.put(1,"One Pair");
        RankTonames.put(2,"Tow Pairs");
        RankTonames.put(3,"Three Pairs");
        RankTonames.put(4,"Straight");
        RankTonames.put(5,"Flush");
        RankTonames.put(6,"Full House");
        RankTonames.put(7,"Four of Kind");
        RankTonames.put(8,"Straight Flush");
    }
    @Override
    public String toString() {
        return RankTonames.get(rank());
    }

    private int count(List<Integer> integers, int n) {
        int count = 0;
        for(int i : integers) {
            if(i == n)count++;
        }
        return count;
    }
    private void initCountToRank() {
        countToRank = new HashMap<>();
        String str = "23456789TJQKA";
        char[] chars = str.toCharArray();
        for (char c : chars) {
            cToInt.put(c, str.indexOf(c) + 2);
        }
        /*
        init countToRank
         */
//        int fk[] = new int[]{4,1};
//        int fh[] = {3,2};
//        //int f[] = {5};
//        //int s[] = {4};
//        int tk[] = {3,1,1};
//        int tp[] = {2,2,1};
//        int p[] = {2,1,1,1};
//        int nothing[] = {1,1,1,1,1};
        countToRank.put(Arrays.asList(5),9);
        countToRank.put(Arrays.asList(4,1),7);//四代一
        countToRank.put(Arrays.asList(3,2),6);//三带二
        countToRank.put(Arrays.asList(3,1,1),3);//三代1，1
        countToRank.put(Arrays.asList(2,2,1), 2);//两对子
        countToRank.put(Arrays.asList(2,1,1,1),1);//一对子
        countToRank.put(Arrays.asList(1,1,1,1,1),0);//无
    }
    public int rank() {
        return rank;
    }
    public HandCards(List<String> hand) {
        initCountToRank();
        StringBuilder sb = new StringBuilder();
        for (String s : hand) {
            sb.append(s + " ");
        }
        rep = sb.toString();
        List<Integer>numbers = new ArrayList<>();//54321
        List<Character> shape = new ArrayList<>();//DDDDD
        for (String s : hand) {
            // System.out.println(s);
            numbers.add(cToInt.get(s.charAt(0)));
            //System.out.println(numbers);
            shape.add(s.charAt(1));
        }
        numbers.sort((o1, o2) -> o2-o1);
        List<Integer> excep = Arrays.asList(14,5,4,3,2);
        if(numbers.equals(excep)) {//在A 5 4 3 2这种情况在A当作1
            numbers = Arrays.asList(5,4,3,2,1);
        }
        boolean straight = (numbers.get(0) - numbers.get(numbers.size() - 1)) == 4  &&
                (new HashSet<>(numbers).size()) == 5 ;//顺子
        boolean flush = new HashSet<>(shape).size() == 1;//同花
        //System.out.println(straight);
        evalRank(numbers, straight, flush);
    }
    public HandCards(String hand) {//hand "5D 4D 3D 2D AD"
        this(Arrays.asList(hand.split(" ")));
    }
    private void evalRank(List<Integer> numbers, boolean straight, boolean flush) {
        Map<Integer, Integer> counter = new HashMap<>();//store the number of same value card,say 55532->[3,1,1]\
        for (int i : numbers) {
            counter.put(i,count(numbers,i));
        }
        ArrayList<Integer> sortCounts = new ArrayList<>(counter.values());
        sortCounts.sort((o1, o2) -> o2-o1);
        if (straight && flush) rank = 8;
        else if (straight) rank = 4;
        else if (flush) rank = 5;
        else rank = countToRank.get(sortCounts);
    }
    public static void main(String[] args) {
        ArrayList<String> ss = new ArrayList<>();
        ss.add("5D");
        ss.add("5D");
        ss.add("5D");
        ss.add("2K");
        ss.add("2K");
        HandCards hc = new HandCards("5D 5D 5D 2K 2K");
        System.out.print(hc.rank());
    }

//    @Override
//    public int compareTo(HandCards o) {
//        /*
//        先比较Rank再比较牌大小
//         */
//        if (this.rank > o.rank) return +1;
//        else if (this.rank == o.rank) {
//
//        }
//    }
}
