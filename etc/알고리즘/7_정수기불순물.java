import java.util.Scanner;

public class Main {

    int M;    // 측정데이터의 크기
    int P;    // 불순물데이터의 크기
    int SI;   // 확대 영역의 시작 행
    int SJ;   // 확대 영역의 시작 열
    int L;    // 확대 영역의 크기

    char[][] data_M; // 측정데이터 저장
    char[][] data_P; // 불순물데이터 저장

    void inputData() {
        int i;

        Scanner sc = new Scanner(System.in);

        data_M = new char[101][101];
        data_P = new char[101][101];

        // 측정데이터 읽기
        M = sc.nextInt();
        for(i = 1; i <= M; i++) {
            data_M[i] = ("\0" + sc.next()).toCharArray();
        }

        // 불순물데이터 읽기
        P = sc.nextInt();
        for(i = 1; i <= P; i++) {
            data_P[i] = ("\0" + sc.next()).toCharArray();
        }

        // 확대영역 정보 읽기
        SI = sc.nextInt();
        SJ = sc.nextInt();
        L = sc.nextInt();

        sc.close();
    }

    int checkPattern(int si, int sj)
    {
        int i, j;

        // 측정데이터와 불순물데이터를 비교
        for (i = 1; i <= P; i++) {
            for (j = 1; j <= P; j++) {
                if (data_M[si + i - 1][sj + j - 1] != data_P[i][j]) {
                    return 0;
                }
            }
        }
        return 1;
    }

    int Solve() {
        int i, j;
        int cnt = 0;

        // 확대영역 내의 불순물 개수 세기
        for (i = SI; i <= SI + L - P; i++) {
            for (j = SJ; j <= SJ + L - P; j++) {
                cnt += checkPattern(i, j);
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        Main m = new Main();

        m.inputData();					//	입력 함수
        int sol = m.Solve();			//	문제 풀이

        System.out.println(sol);		//	정답 출력

    }
}