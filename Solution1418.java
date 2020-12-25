import java.util.*;

//TreeMap可以自动按键进行升序
public class Solution1418 {
    /*public List<List<String>> displayTable(List<List<String>> orders) {
        Collections.sort(orders, new Comparator<List<String>>() {
            public int compare(List<String> l1, List<String> l2) {
                return Integer.parseInt(l1.get(1)) - Integer.parseInt(l2.get(1));
            }
        });

        List<List<String>> display = new ArrayList<>();
        List<String> title = new ArrayList<>();
        title.add("Table");
        for (List<String> order : orders) {
            if (!title.contains(order.get(2))) {
                title.add(order.get(2));
            }
        }
        Collections.sort(title, new Comparator<String>() {// 排序标题行
            public int compare(String s1, String s2) {
                if (s1 == "Table") {
                    return -1;
                } else if (s2 == "Table") {
                    return 1;
                } else {
                    char[] chars1 = s1.toCharArray();
                    char[] chars2 = s2.toCharArray();
                    int i1 = 0;
                    int i2 = 0;
                    int i = 0;
                    while (i1 == i2) {
                        i1 += (int) chars1[i];
                        i2 += (int) chars2[i];
                        i++;
                    }
                    return i1 - i2;
                }
            }
        });
        display.add(title);
        Map<String, Integer> muen = new HashMap<>();// 菜品索引
        for (int i = 1; i < title.size(); i++) {
            muen.put(title.get(i), i);
        }
        for (List<String> l : orders) {
            List<String> last = display.get(display.size() - 1);   //上一个桌号
            if (last.get(0).equals(l.get(1))) {   //如果和上一个相同,则证明桌号存在过  
                int count=Integer.parseInt(display.get(display.size() - 1).get(muen.get(l.get(2))));
                count++;
                display.get(display.size()-1).set(muen.get(l.get(2)), Integer.toString(count));
            } else {
                int[] order_array = new int[title.size()];      //order菜名
                order_array[0] = Integer.parseInt(l.get(1));   // 加入桌号
                order_array[muen.get(l.get(2))]++;
                List<String> list=new ArrayList<>();
                for(int i:order_array){
                    list.add(Integer.toString(i));
                }
                display.add(list);
            }
        }
        return display;
    }*/

    //别人的答案
    public List<List<String>> displayTable(List<List<String>> orders) {
        //映射 dishToId，其中的每个键值对表示 (餐品名称，数据表列) 的映射关系；因为是映射所以重复的会自动忽略   
        Map<String, Integer> dishToId = new TreeMap<String, Integer>();
        //映射 tableToId，其中的每个键值对表示 (餐桌编号，数据表行)的映射关系
        Map<Integer, Integer> tableToId = new TreeMap<Integer, Integer>();
        for (List<String> order : orders) {
            dishToId.put(order.get(2), -1);
            tableToId.put(Integer.parseInt(order.get(1)), -1);
        }
        
        int dishId = -1;
        List<String> headline = new ArrayList<String>();//标题行
        headline.add("Table");
        for (String key : dishToId.keySet()) {//keyset返回所有关键字,用一个集合返回
            ++dishId;
            dishToId.put(key, dishId);
            headline.add(key);
        }
        
        int tableId = -1;
        //二维数组 info_int，存储每一桌点每一道菜的数量。由于我们最终返回的数据表中的数据都是字符串类型，
        //直接用字符串对数量进行加减较为困难。因此 info_int 充当一个临时的、整数类型的数据表，当所有的统计结束后，我们将 info_int 中的数据复制到最终答案中，并转换为字符串类型
        List<List<Integer>> infoInt = new ArrayList<List<Integer>>();
        for (int key : tableToId.keySet()) {
            ++tableId;
            tableToId.put(key, tableId);
            List<Integer> line = new ArrayList<Integer>();
            line.add(key);
            for (int i = 1; i <= dishToId.size(); ++i) {
                line.add(0);
            }
            infoInt.add(line);
        }
        
        for (List<String> order : orders) {
            int tId = tableToId.get(Integer.parseInt(order.get(1)));
            int dId = dishToId.get(order.get(2));
            infoInt.get(tId).set(dId + 1, infoInt.get(tId).get(dId + 1) + 1);
        }
        
        List<List<String>> ans = new ArrayList<List<String>>();
        ans.add(headline);
        for (List<Integer> line : infoInt) {
            List<String> ansLine = new ArrayList<String>();
            for (int elem : line) {
                ansLine.add(String.valueOf(elem));
            }
            ans.add(ansLine);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution1418 solution1418 = new Solution1418();
        //[["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
        List<String> a = new ArrayList<>();
        a.add("David");
        a.add("3");
        a.add("Ceviche");
        List<String> b = new ArrayList<>();
        b.add("Corina");
        b.add("10");
        b.add("Burrito");
        List<String> c = new ArrayList<>();
        c.add("David");
        c.add("3");
        c.add("Chicken");
        List<String> d = new ArrayList<>();
        d.add("Carla");
        d.add("5");
        d.add("Water");
        List<String> e = new ArrayList<>();
        e.add("Carla");
        e.add("5");
        e.add("Ceviche");
        List<String> f = new ArrayList<>();
        f.add("Rous");
        f.add("3");
        f.add("Ceviche");
        List<List<String>> display = new ArrayList<>();
        display.add(a);
        display.add(b);
        display.add(c);
        display.add(d);
        display.add(e);
        display.add(f);
        solution1418.displayTable(display);
        return;
    }
}
