import java.util.*;
/**
 * Created by abc84 on 2016/5/7.
 */
public class Poker {
    public static List<HandCards> deal(int players) {//发牌
        String nums = "2 3 4 5 6 7 8 9 T J Q K A";
        String shapes = "H S D C";
        StringBuilder unshuffle = new StringBuilder();
        for(String n : nums.split(" ")) {
            for(String s : shapes.split(" ")) {
                unshuffle.append(n+s+" ");
            }
        }
        List<String> deck = Arrays.asList(unshuffle.toString().split(" "));
        Collections.shuffle(deck);
        List<HandCards> ret = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            ret.add(new HandCards(deck.subList(i*5,(i+1)*5)));
        }
        //System.out.println(deck);
        return ret;
    }

    public static void simulate(int n) {//模拟发牌次数,测试不同牌的概率
        Map<Integer, Integer> RankToCount = new HashMap<>();
        for (int i = 0; i < 9; i++) {//init
            RankToCount.put(i,0);
        }
        for (int i = 0; i < n/10; i++) {//模拟十人对战n/10次
            List<HandCards> hands = deal(10);
            for (HandCards hand : hands) {
                int count = RankToCount.get(hand.rank());
                count++;
                RankToCount.put(hand.rank(), count);
            }
        }
        for (int i : RankToCount.keySet()) {
            System.out.format("%14s : %2.6f%% \n",HandCards.RankTonames.get(i),(100.0 * RankToCount.get(i))/n);
        }
    }
    public static void main(String[] args) {
       simulate(700*1000);
    }
}
