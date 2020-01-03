import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MonsterFollowAvatar extends JFrame {
	private JPanel gamepanel = new GamePanel("a","M",'q');
	
	public MonsterFollowAvatar() {
		super("MonsterFollowAvatar"); //타이틀 설정
		setSize(600,600); //창크기
		setContentPane(gamepanel);  //컨텐트팬으로 gamepanel 설정
		gamepanel.setLayout(null);  //배치 관리자 삭제
		setVisible(true);  //창 보이기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창 닫기 설정

	}
	
	class GamePanel extends JPanel {
		private JLabel avatar = new JLabel(); //아바타 레이블 생성
		private JLabel monster = new JLabel(); //몬스터 레이블 생성
		private JLabel location = new JLabel();  // 아바타 위치 출력 레이블
		private JLabel location2 = new JLabel();  //몬스터의 위치를 나타내는 레이블
		private char quit;  //프로그램 종료를 위한 캐릭터 변수
		private final int Moving_distance = 10;  //아바타가 한 번에 움직이는 거리
		
		public GamePanel(String a,String M, char q){
			avatar.setText(a);  //아바타 레이블을 a로 생성
			monster.setText(M);  //몬스터 레이블을 M으로 생성
			quit = q;
	
			avatar.setLocation(100, 100);  //아바타 위치 지정
			monster.setLocation(150,150); //몬스터 위치 지정
			avatar.setSize(15,15);  //아바타 레이블 크기 지정
			monster.setSize(15,15); //몬스터 크기 지정
			
			location.setText("avatar's location (x,y) : "+avatar.getX()+", "+avatar.getY());  //아바타 레이블 위치 출력
			location.setLocation(170,450);  //좌표를 보여주는 레이블 위치 지정
			location.setSize(400, 100);  //레이블 크기 지정
			
			add(avatar); //아바타 레이블을 패널에 부착
			add(monster);  //몬스터 레이블을 패널에 부착
			add(location);  //아바타 위치 표시 레이블을 패널에 부착 
			add(location2);  // 몬스터 위치 표시 레이블을 패널에 부착
			setVisible(true);  //패널이 보이도록 설정
			
			avatar.setFocusable(true);   //아바타 레이블이 키를 입력 받을 수 있도록 포커스 지정.
			avatar.requestFocus();
			
			MonsterThread th = new MonsterThread(avatar,monster,location2);  //MonsterThread 스레드 객체 생성
			th.start(); //스레드 시작 ----> run()으로 감.
						
			avatar.addKeyListener(new KeyAdapter() {   //아바타 레이블에 키 리스너를 부착. 
				public void keyPressed(KeyEvent e) {  //키가 눌렸을 때
					int keyCode = e.getKeyCode();  //입력된 키의 코드를 알아낸다.
					char keyChar = e.getKeyChar();  //입력된 키의 문자를 알아낸다.
					
					if(keyChar == quit) {
						System.exit(0);  // q 버튼을 누르면 프로그램 종료
					}
					
					//키 코드 값에 따라 상,하,좌,우 키를 판별하고 아바타의 위치를 이동시킨다.
					switch (keyCode) {
						case KeyEvent.VK_UP:
							avatar.setLocation(avatar.getX(), avatar.getY()-Moving_distance); break;
						case KeyEvent.VK_DOWN:
							avatar.setLocation(avatar.getX(), avatar.getY()+Moving_distance); break;
						case KeyEvent.VK_LEFT:
							avatar.setLocation(avatar.getX()-Moving_distance, avatar.getY()); break;
						case KeyEvent.VK_RIGHT:
							avatar.setLocation(avatar.getX()+Moving_distance, avatar.getY()); break;	
					}
					//아바타가 프레임 밖을 벗어나지 못하도록 설정
					if ((avatar.getX() <= 0 || avatar.getX() >= getWidth()-10) || (avatar.getY() <= 0 || avatar.getY() >= getHeight()-20)) {
						if (avatar.getX() <= 0)   //왼쪽 프레임 밖으로 벗어날 때
							avatar.setLocation(0, avatar.getY()); 
						if (avatar.getX() >= getWidth()-10)  //오른쪽 프레임 밖으로 벗어날 때
							avatar.setLocation(getWidth()-10, avatar.getY());
						if (avatar.getY() <= 0) //위쪽 프레임 밖으로 벗어날 때
							avatar.setLocation(avatar.getX(), 0);
						if (avatar.getY() >= getHeight()-20)  // 아래쪽 프레임 밖으로 벗어날 때
							avatar.setLocation(avatar.getX(), getHeight()-20);
					}	
					
					location.setText("avatar's location ( x, y ) :  "+avatar.getX()+", "+avatar.getY());  //아바타 레이블 위치를 출력하기 위해 레이블에 텍스트 추가
				}
			});
		}
	}
	public static void main(String[] args) {
		new MonsterFollowAvatar();
	}
	
}

class MonsterThread extends Thread{
	private JLabel location2 = new JLabel();  //몬스터 위치 출력 레이블
	private JLabel followed = new JLabel();   //목적 레이블
	private JLabel following = new JLabel(); // 따라가는 레이블
	private int [] direction = {1,1,1,1}; //{위 아래 왼쪽 오른쪽} 방향을 정하는 배열
	
	public MonsterThread(JLabel avatar, JLabel monster,JLabel location2) {
		followed = avatar;   //매개 변수로 받은 아바타 레이블은 목적 레이블
		following = monster;  //매개 변수로 받은 괴물 레이블은 따라가는 주체 레이블
		this.location2 = location2;  //매개 변수로 받은 location2는 여전히 이 클래스에서도 몬스터의 위치를 출력해주는 레이블
	}
	
	
	@Override
	public void run() {
		int x = 0; //몬스터의 x좌표
		int y = 0; //몬스터의 y좌표
		Random random = new Random();  // 확률을 구하기 위한 랜덤 객체 생성
		
		while (true) {
			
			if(followed.getY() < following.getY()) {  //아바타가 몬스터의 위쪽에 있을 때
				direction[0] = 3;   //위쪽 방향에 확률을 높임.
			}
			else if (followed.getY() > following.getY()) {   //아바타가 몬스터의 아래쪽에 있을 때
				direction[1] = 3;    //아래쪽 방향에 확률을 높임.
			}
			
			if(followed.getX() < following.getX()) {  //아바타가 몬스터의 왼쪽에 있을 때
				direction[2] = 3;
			}
			else if (followed.getX() > following.getX()) {  //아바타가 몬스터의 오른쪽에 있을 때
				direction[3] = 3;
			}


			int index;  // 랜덤 변수로 선택된 배열을 찾아내기 위한 변수
			int big_direction;  //큰 방향 4개 (위쪽, 아래쪽, 왼쪽, 오른쪽)
			int detail_direction; // 세부 방향 선택을 위한 랜덤 변수
			int sum = 0;  //배열에 저장된 값들을 더하는 변수
			
			// 배열의 값은 3이 1개 이거나 2개인 경우가 발생 ex, 아바타가 몬스터의 왼쪽 & 위쪽에 존재한다면{3,1,3,1} / 아바타가 몬스터의 오른쪽에 존재하고 y좌표는 동일할 경우 {1,1,1,3}
			big_direction = random.nextInt(8)+1;  // 위, 아래, 왼쪽, 오른쪽 총 4방향 중 하나를 선택함. 나올 수 있는 수 중 가장 큰 수는 8이므로 1~8 중 하나를 선택하는 랜덤 변수 생성.  
			detail_direction = random.nextInt(5)+1; // 각 방향은 각각 3개씩의 세부 방향을 가지며 (왼쪽 - 왼쪽, 왼쪽 대각선 위, 왼쪽 대각선 아래), 대각선을 제외한 방향을 두 번씩 더 추가 설정함. 숫자는 1~5 중 하나를 선택
			
			while (true) {				
				sum = direction[0];  //sum에 배열 첫번째 값 저장
				if (big_direction <= sum) {  //배열의 첫번째 값보다 작다면
					index = 0;  //index에 0 저장 (위)
					break;}
				else if (sum < big_direction && big_direction <= sum+direction[1]) {  //배열의 첫번째 값보다 크고 두번째 값보다 작다면
					index = 1; //index에 1 저장 (아래)
					break;
				}
				else if (sum + direction[1] < big_direction && big_direction <= sum+direction[1]+direction[2] ) {  //배열의 두번째 값보다 크고 세번째 값보다 작다면
					index = 2;  //index에 2 저장 (왼쪽)
					break;
				}
				else {
					index = 3;  // 그 외에는 3 저장  (오른쪽)
					break;}
			}
			
			if (index == 0) {  //큰 방향이 위쪽이라면
				switch (detail_direction) {
				case 1:
					x = following.getX(); y = following.getY()-5;  //위쪽 방향, 위로 5 픽셀 이동
				case 2:
					x = following.getX()-4; y = following.getY()-3; //왼쪽 대각선 위 방향, 왼쪽으로 4, 위로 3 이동 (대각선 길이 5)
				case 3:
					x = following.getX()+4; y = following.getY()-3;  //오른쪽 대각선 위 방향, 오른쪽으로 4, 위로 3 이동 (대각선 길이 5)
				case 4:
					x = following.getX(); y = following.getY()-5;  //위쪽 방향, 위로 5 픽셀 이동
				case 5:
					x = following.getX(); y = following.getY()-5;  //위쪽 방향, 위로 5 픽셀 이동
				}
			}
			
			if (index == 1) {  //큰 방향이 아래쪽이라면
				switch (detail_direction) {
				case 1:
					x = following.getX(); y = following.getY()+5;  //아래 방향, 아래쪽으로 5픽셀 이동
				case 2:
					x = following.getX()-4; y = following.getY()+3; //왼쪽 대각선 아래 방향, 왼쪽으로 4, 아래로 3 이동 (대각선 길이 5)
				case 3:
					x = following.getX()+4; y= following.getY()+3;  //오른족 대각선 아래 방향, 오른족으로 4, 아래로 3이동 (대각선 길이 5)
				case 4:
					x = following.getX(); y = following.getY()+5;  //아래 방향, 아래쪽으로 5픽셀 이동
				case 5:
					x = following.getX(); y = following.getY()+5;  //아래 방향, 아래쪽으로 5픽셀 이동
				}
			}
			
			if (index == 2) {  //큰 방향이 왼쪽이라면
				switch (detail_direction) {
				case 1:
					x = following.getX()-5; y = following.getY();   //왼쪽 방향 왼쪽으로 5 픽셀 이동
				case 2:
					x = following.getX()-4; y = following.getY()-3; //왼쪽 대각선 위 방향, 왼쪽으로 4, 위로 3 이동 (대각선 길이 5)
				case 3:
					x = following.getX()-4; y = following.getY()+3; //왼쪽 대각선 아래 방향, 왼쪽으로 4, 아래로 3 이동 (대각선 길이 5)
				case 4:
					x = following.getX()-5; y = following.getY();   //왼쪽 방향 왼쪽으로 5 픽셀 이동
				case 5:
					x = following.getX()-5; y = following.getY();   //왼쪽 방향 왼쪽으로 5 픽셀 이동
				}
			}
			
			if (index == 3) {  //큰 방향이 오른쪽이라면
				switch (detail_direction) {
				case 1:
					x = following.getX()+5; y = following.getY();  //오른쪽 방향, 오른쪽으로 5픽셀 이동
				case 2:
					x = following.getX()+4; y = following.getY()-3;  //오른쪽 대각선 위 방향, 오른쪽으로 4, 위로 3 이동 (대각선 길이 5)
				case 3:
					x = following.getX()+4; y= following.getY()+3;  //오른족 대각선 아래 방향, 오른족으로 4, 아래로 3이동 (대각선 길이 5)
				case 4:
					x = following.getX()+5; y = following.getY();  //오른쪽 방향, 오른쪽으로 5픽셀 이동
				case 5:
					x = following.getX()+5; y = following.getY();  //오른쪽 방향, 오른쪽으로 5픽셀 이동
				}
			}

			// 아바타와 몬스터의 위치가 같아지는 경우
			if (followed.getX() == following.getX() && followed.getY() == following.getY()) {  //아바타와 몬스터가 만나는 경우				
				try {
				location2.setText("아바타가 괴물에게 잡혔습니다.");   // 아바타가 몬스터에게 잡혔다는 메세지를 출력하고 
				Thread.sleep(1200);  // 스레드를 잠시 멈춤
				x= 150; y=150;   //몬스터 레이블의 초기 위치로 리셋
			} catch(InterruptedException e) {return;}		
		}

			//아바타는 10픽셀씩 움직이고 몬스터는 5픽셀씩 움직이기 때문에 위치가 완전히 동일해지지 않고 아바타가 몬스터의 움직이는 거리에 갇히는 경우가 생김. 그 상황을 타파하기 위한 if문 작성
			if (Math.abs(followed.getX()-following.getX()) <5 && Math.abs(followed.getY() - following.getY()) <5) { //x 좌표와 y 좌표 모두 5픽셀 범위 내에 두 개의 좌표가 존재하는 경우(절대값 사용)
				try {
					x = followed.getX(); y= followed.getY();
					location2.setText("아바타가 괴물에게 잡혔습니다.");   // 아바타가 몬스터에게 잡혔다는 메세지를 출력하고 
					Thread.sleep(1200);  // 스레드를 잠시 멈춤
					x= 150; y=150;   //몬스터 레이블의 초기 위치로 리셋
				} catch(InterruptedException e) {return;}	
			}
			
					
			following.setLocation(x,y);  //몬스터 레이블 위치 설정
			location2.setText("monster's location (x,y) : "+x+","+y); //몬스터의 현재 위치 좌표 출력
			location2.setLocation(170, 480);  //location2 레이블 위치 지정
			location2.setSize(400,100); //location2 레이블 크기 지정 

			try {
				sleep(400);				
			} catch(InterruptedException e){
				return;
			}
		}
	}
		
}

	
	


