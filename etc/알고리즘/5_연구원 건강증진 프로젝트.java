import java.util.Scanner;

public class Main {

	int N;				//	계단의 개수
	int[] P;				//	P[i]: i 계단을 밟았을 때 얻는 점수
	int[] D;

	public int solve() {
		int sol=0;

		//	코드를 작성하세요
		D = new int [N+1];

		//	DP 테이블 초기값 설정 
		D[0] = 0; 
		D[1] = P[0]; 
		D[2] = P[0] + P[1];
		
		for (int i = 3; i <= N; i++){
			//	이전 계단을 안 밟은 경우와 밟은 경우 중 더 높은점수로 D[i] 갱신
			// 2칸 이동 전 단계는 1칸이동/2칸이동 모두 가능하기때문에 어떤게 올지 모름.
			// 어떤게 올지 모르기때문에 2칸 이동 전 단계는 i계단을 밟았을때 얻는 최대 점수 값을 이용한다.
			// 이미 최대값이 계산되었기 때문에 어떤게 올지 신경안써도됨....
			D[i] = D[i - 2] + P[i - 1];		
			if (D[i] < D[i - 3] + P[i - 2] + P[i-1]){
				D[i] = D[i - 3] + P[i - 2] + P[i-1];
			} 
		}
		sol = D[N];
		
		return sol;
	}

	public void inputData()  {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();			// 계단의 개수
		P = new int [N+1];		

		for(int i=0 ; i<N ; i++){
			P[i] = sc.nextInt();	//	계단별 점수
		}
	}

	public static void main(String[] args){
		Main m = new Main();

		m.inputData();				//	입력 함수
		int sol = m.solve();			//	문제 풀이
		System.out.println(sol);	//	정답 출력
	}
}