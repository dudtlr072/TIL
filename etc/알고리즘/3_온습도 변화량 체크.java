import java.util.Scanner;

public class Main {

	int N;

	// 온도값 범위 변수
	int min_temp;
	int max_temp;

	// 습도값 범위 변수
	int min_humidity;
	int max_humidity;

	// 온/습도 변화량
	int []Temperature_diff;
	int []Humidity_diff;

	// 온/습도 범위 초과 flag
	boolean flag_control_temp;
	boolean flag_control_humidity;
	
	// 온/습도 초기 값
	static final int INIT_TEMPERATURE = 20;
	static final int INIT_HUMIDITY = 50;
	
	int cur_temp;	// 현재 온도 값
	int cur_humidity;	// 현재 습도 값
	
	boolean Check_Temp_and_Control(int diff)
	{
		cur_temp += diff;
	
		if ((cur_temp > max_temp) || (cur_temp < min_temp))	// 범위 벗어났을 경우
		{
			flag_control_temp = true;
			cur_temp = INIT_TEMPERATURE;
			return true;
		}
		else return false;
	}
	
	boolean Check_Humidity_and_Control(int diff)
	{
		cur_humidity += diff;
	
		if ((cur_humidity > max_humidity) || (cur_humidity < min_humidity))		// 범위 벗어났을 경우
		{
			flag_control_humidity = true;
			cur_humidity = INIT_HUMIDITY;
			return true;
		}
		else return false;
	}
	
	void Reset_Flag()
	{
		//  flag 초기화
		if (flag_control_humidity) flag_control_humidity = false;
		if (flag_control_temp) flag_control_temp = false;
	}
	

	void Control_Temp_and_Humidity()
	{
		int i;
	
		for (i = 1; i <= N; i++)
		{
			// 온습도 범위 체크 및 제어
			boolean tempCheck = Check_Temp_and_Control(Temperature_diff[i]);
			boolean humiCheck = Check_Humidity_and_Control(Humidity_diff[i]);
			if (tempCheck || humiCheck)
			{
				if (flag_control_temp && flag_control_humidity) System.out.println(3);
				else if (flag_control_temp) System.out.println(1);
				else System.out.println(2);
	
				Reset_Flag();
			}
			else System.out.println(0);
		}
	}

	void Input_Data()
	{
		int i;
		
		Scanner sc = new Scanner(System.in);
		
		// 전체 동작시간 , 온도/ 습도 범위값 입력
		N = sc.nextInt();
		min_temp = sc.nextInt(); 
		max_temp = sc.nextInt();
		min_humidity = sc.nextInt();
		max_humidity = sc.nextInt();
	
		Temperature_diff = new int[N+1];
		Humidity_diff = new int[N+1];
		
		// 온도 변화량 입력
		for (i = 1; i <= N; i++)
		{
			Temperature_diff[i] = sc.nextInt();
		}
		
		// 습도 변화량 입력
		for (i = 1; i <= N; i++)
		{
			Humidity_diff[i] = sc.nextInt();
		}
		sc.close();
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		
		m.cur_temp = INIT_TEMPERATURE;
		m.cur_humidity = INIT_HUMIDITY;
		
		m.Input_Data(); // 입력 data

		m.Control_Temp_and_Humidity(); // 온습도 제어 처리 함수
	}
}
