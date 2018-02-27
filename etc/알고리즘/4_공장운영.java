import java.util.Scanner;

public class Main {
	int N; //일
	int S; //보관비용
	int C[] = new int [50010]; //가격
	int Y[] = new int [50010]; //용량

	public void inputData()  {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = sc.nextInt();
		for (int i = 0; i < N; i++) {
			C[i] = sc.nextInt();
			Y[i] = sc.nextInt();
		}
	}
	
	// 연속된 2일만 비교함
	// 문제에는 그런조건이 없었는데....
	public long calculate() {
		int i = 0;
		int price = 0;
		long sum = 0;
		price = C[0];
		sum = sum + (price * Y[0]);
		for(i = 1; i < N; i++) {
			price = price + S;
			if (C[i] < price) {
				price = C[i];
			}
			sum = sum + (price * Y[i]);
		}
		return sum;
	}

	public static void main(String[] args){
		Main m = new Main();
		long ans = 0;

		m.inputData();				// 입력 함수

		// TODO : 코드를 작성하세요
		ans = m.calculate();
		
		
		System.out.println(ans);	// 정답 출력
	}
}