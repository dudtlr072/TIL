import java.util.Scanner;

public class Main {
	int N;							//	모델명의 개수
	String str;
	String []arr;				//	모델명 저장
	int []check;					//	check 배열
	int []cnt;						//	투표수 저장
	int []nxt;						//	다음번호 저장

	public void Print_Max(int start) {
		System.out.print(arr[start]+" "+(start+1));
		for (int i = nxt[start]; i != 0; i = nxt[i]) System.out.print(" " + (i+1));
		System.out.println();
	}
						
	public void solve() {
		int unique = 1;
		check = new int [N+1];		//	해당 모델명을 처리했는지 여부 표시
		cnt = new int [N+1];		
		nxt = new int [N+1];		
		
		//	모든 모델명을 비교
		for (int i = 0; i<N-1; i++){
			cnt[i] = 1;
			if (check[i] == 1)	continue;		//	이미 비교되어 제거된 모델명인 경우 continue
			int prev = i;
			for (int j = i + 1; j<N; j++){
				if (arr[i].compareTo(arr[j]) == 0){	//	같은 모델명인 경우
					cnt[i]++;
					check[j] = 1;		//	해당 모델명 처리로 표시 
					nxt[prev] = j;		//	순서번호 저장
					prev = j;
					unique = 0;			//	중복되는 모델명 있음(UNIQUE 아님)
				}
			}
		}

		if (unique == 1) System.out.println("unique");	//	중복되는 모델명이 하나도 없는 경우 UNIQUE 출력
		else{
			int max = 1;
			for (int num = 0; num<N/2; num += max){
				int maxi = 0;
				max = 1;
				for (int i = 0; i < N; i++){		//	최대값 찾기
					if (max<cnt[i]){
						max = cnt[i];
						maxi = i;
					}
				}
				if (max == 1) break;			//	최대값이 1이면 인쇄종료
				Print_Max(maxi);				//	해당 모델명 및 순서번호 인쇄
				cnt[maxi] = 0;
			}
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