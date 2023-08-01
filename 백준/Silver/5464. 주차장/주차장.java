import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	//주차 공간 s의 단위 무게당 요금을 저장할 HashMap<주차공간 번호, 단위 무게당 요금>
	static Map<Integer, Integer> rsMap = new HashMap<>();
	//차량 k의 무게를 저장할 HashMap<차량 번호, 무게>
	static Map<Integer, Integer> wkMap = new HashMap<>();
	//주차 위지 정보를 저장할 HashMap<차 번호, 주차공간>
	static Map<Integer, Integer> parkingLocationMap = new HashMap<>();
	static int n, m;
	
	//현재 주차 공간에 자리가 있는지를 확인하는 pq
	static PriorityQueue<Integer> parkinglotQ = new PriorityQueue<>();
	//주차 공간이 꽉찼을 때 대기 q
	static Queue<Integer> waitingQ = new LinkedList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringTokenizer tk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(tk.nextToken());
		m = Integer.parseInt(tk.nextToken());
		
		//pq에 1~n 까지의 주차 공간을 넣음
		for(int i=1; i<=n; i++)
			parkinglotQ.add(i);
		
		getRs();
		getWk();
		
		System.out.println(sumParkingFee(2*m));
	}
	
	//rsMap에 정보를 저장하는 함수
	private static void getRs() throws NumberFormatException, IOException {
		for(int i=1; i<=n; i++)
			rsMap.put(i, Integer.parseInt(br.readLine()));
	}
	
	//wkMap에 정보를 저장하는 함수
	private static void getWk() throws NumberFormatException, IOException {
		for(int i=1; i<=m; i++)
			wkMap.put(i,  Integer.parseInt(br.readLine()));
	}
	
	private static int sumParkingFee(int command) throws NumberFormatException, IOException {
		int sum = 0;
		//m x 2회 반복
		while(command-- > 0) {
			int car = Integer.parseInt(br.readLine());
			
			//차가 나갈 때
			if(car < 0) {		
				//차가 주차되어 있던 공간 반납 -> pq에 add
				parkinglotQ.add(parkingLocationMap.get(-1 * car));
				//대기줄에 차가 있다면 방금 차가 나갔기 떄문에 들어올 수 있다
				if(!waitingQ.isEmpty()) {
					//대기줄에 가장 먼저 들어온 차
					int nextCar = waitingQ.poll();
					//방금 생긴 주차 공간
					int nextParkinglot = parkinglotQ.poll();
					//요금 갱신
					sum += rsMap.get(nextParkinglot) * wkMap.get(nextCar);
					//주차 위지 정보 저장
					parkingLocationMap.put(nextCar, nextParkinglot);
				}
			}
			//차가 들어올 때
			if(car > 0) {
				//주차 공간이 없을 때
				if(parkinglotQ.isEmpty()) {
					//대기줄에 차 넣기
					waitingQ.add(car);
				}
				//주차 공간이 있을 때
				else {
					//가장 앞의 주차 공간
					int nextParkinglot = parkinglotQ.poll();
					//요금 갱신
					sum += rsMap.get(nextParkinglot) * wkMap.get(car);
					//주차 위지 정보 저장
					parkingLocationMap.put(car, nextParkinglot);
				}
			}
		}
		return sum;
	}
}
