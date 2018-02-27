import java.util.Scanner;

public class Main {
	
	int N, M;
	int[] TV;    // 판매된 TV 모델
	int[] sold;  // 판매수량
	int[] score; // 인기도
	
	void inputData() {
		int i;
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		TV = new int[100000 + 10];
		sold = new int[100000 + 10];
		score = new int[100000 + 10];
		
		for(i = 0; i < N; i++) {
			TV[i] = sc.nextInt();
		}
		
		sc.close();
	}
	
	int Solve() {
		int i, j, max;
		
		// 모델 별 판매수량을 카운트
		for (i = 0; i < N; i++) {
			sold[TV[i]]++;
		}

		i = 0;
		j = 1;
		while(i != N && j != N) {
			if (TV[i] != TV[j]) {
				i = j;
				j++;
			} else {
				j++;
			}
			score[TV[i]] = Math.max(score[TV[i]], (sold[TV[i]] * (j-i)));
		}
		
		// 기존 방법에 의한 인기도 계산 (인기도 = 판매수량)
		//for (i = 1; i <= M; i++) {
		//	score[i] = sold[i];
		//}
		
		// 인기도가 가장 높은 모델번호 찾기
		max = 1;
		for (i = 2; i <= M; i++) {
			if(score[i] > score[max]) max = i;
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		
		m.inputData();					//	입력 함수
		int sol = m.Solve();			//	문제 풀이
		
		System.out.println(sol);		//	정답 출력

	}
}

