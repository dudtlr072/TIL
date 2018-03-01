import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    int N;							//	모델명의 개수
    String str;
    String []arr;				//	모델명 저장

    public void solve() {
        //	코드를 작성하세요
        HashMap<String, String> map = new HashMap<String, String>();

        for(int i = 0; i < arr.length; i++) {
            String str = arr[i];
            if(map.get(str) == null) {
                map.put(str, (i + 1));
            } else {
                map.put(str, map.get(str) + " " + (i + 1));
            }
            if (map.get(str).split(" ").length >= N / 2) break;
        }

        ArrayList<Map.Entry> entryList = new ArrayList<>(map.entrySet());
        entryList.sor

        for(Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public void inputData()  {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.nextLine();
        str = sc.nextLine();
        arr = str.split(" ");
    }

    public static void main(String[] args){
        Main m = new Main();

        m.inputData();			//	입력 함수

        m.solve();					//	문제 풀이
    }
}