import java.io.*;
import java.util.*;

class FileWordReader {
	Vector<String> wordVector = new Vector<String>(); //단어를 추가할 Vector 컬렉션 생성
	Random random = new Random(); //임의의 단어를 선택하기 위한 Random 객체 생성
	public void fileWords(String filename) {
		File file = new File(filename); // File 객체 생성
		FileReader fileReader = null; // FileReader 객체 생성
		try {
			fileReader = new FileReader(file); // FileReader로 file 읽음.
			Scanner scan = new Scanner(fileReader); //scanner객체로 fileReader 읽음.
			while(scan.hasNext()){ //파일 끝까지 읽음.
				String word = scan.nextLine(); // 한 라인 단위로 읽음.
				wordVector.add(word); //vector에 추가
			}
		} catch(IOException e){
			e.printStackTrace();
		} finally {
			try {
				if(fileReader != null)
					fileReader.close();		
			}catch(IOException e){
				e.printStackTrace();
			} //fileReader != null --->fileReader.close() with try&catch
		}		
	}	
	
	// 랜덤 변수 생성 (단어의 글자 수가 4개 이상인 것만)
	public String getRandomword() {
		int r1 = random.nextInt(wordVector.size());  //단어를 선택할 임의의 수 하나 선택
		String choice_word = wordVector.get(r1); //vector에서 단어 하나 선택
		while (true) {
			if (choice_word.length()>=4) // 단어가 4글자 이상 될 때만 선택(이 프로그램에서는 4글자를 숨겨야 하므로)
				break;
			r1 = random.nextInt(wordVector.size()); // 다시 난수 발생
			choice_word = wordVector.get(r1); //새로운 단어 선택
		}
		return choice_word;
	}
}
public class HangManGame {
	private int f_count = 0; //틀린 것을 세는 카운트
	private char clone_word[]; // 선정된 단어를 저장하는 character 배열
	private char hidden [] = new char [4]; //숨긴 알파벳을 저장하는 character 배열.
	private int index [] = new int  [4]; //랜덤으로 선정한, 숨겨진 알파벳 위치를 저장하는 인덱스 저장하는 int 배열
	Random random = new Random();
	Scanner scanner = new Scanner(System.in);
	//행맨 게임 시작
	public void start() {
		FileWordReader wordReader = new FileWordReader();
		wordReader.fileWords("words.txt");
		String choice_word = wordReader.getRandomword();
		clone_word = Replacement(choice_word);
		
		System.out.println(clone_word); // -으로 가려진 단어 출력
		
		do {
			boolean f_c =false; // 카운트가 - 자리마다 되는 것을 막기 위해 임시 카운트 사용
			System.out.print(">> ");
			String ans = scanner.next();
			
			for (int i = 0; i<hidden.length;i++) { //저장된 값을 돌면서 같은 문자가 있는지 확인
				if(ans.toUpperCase().charAt(0) == hidden[i]) { //같은 알파벳인데 대소문자만 다를 경우, 같은 알파벳으로 취급함.
					clone_word[index[i]] = ans.toUpperCase().charAt(0); //있다면 clone_word에 넣어줌.
					f_c = true;
				}
				if (ans.charAt(0) == hidden[i] ) {
					clone_word[index[i]] = ans.charAt(0); //있다면 clone_word에 넣어줌.
					f_c = true;
				}	
			}
			if(f_c == false) //사용자가 말한 글자가 틀렸을 경우
				f_count=f_count+1;
			System.out.println(clone_word);
			if (f_count == 5) { // 5번 틀리면 게임 종료
				break;
			}
			
		}
		while(!complete()); //단어가 완성되지 못했다면 계속 do - while문 실행
		if(f_count == 5) { //실패했다면
			System.out.println("5번 실패 하였습니다.");
			System.out.println(choice_word);
			f_count=0; // 다음 게임을 위해 count 초기화
			YesOrNo(); // 다음 게임을 진행할 지 묻는 함수 호출.
		}
		else {
			f_count=0; // 다음 게임을 위해 count 초기화
			YesOrNo(); //성공했다면 다음 게임을 진행할 것인지 물어보는 함수 호출.
		}
	}
	//-가 있는지 없는지 확인하는 메소드
			public boolean complete() {
				for (int i = 0; i<clone_word.length; i++) {
					if(clone_word[i] == '-')
						return false;
				}
				return true;
			}
	
	//랜덤으로 고른 단어 중 일부를 숨기는 메소드
			public char[] Replacement(String choice_word) {
				char clone_word [] = new char [choice_word.length()]; //선택한 단어를 캐릭터 배열에 넣기 위해 캐릭터 배열 생성.
				clone_word = choice_word.toCharArray();
				int correct = choice_word.length(); // r2가 중복된 것을 파악하기 위한 변수
				int r2 = random.nextInt(choice_word.length()); //-로 바꿀 글자 위치 랜덤 생성
				for (int i = 0; i<4; i++) {// 선택한 단어에서 4 글자 가리기 (-)
					while (true) {  // 이미 -인 위치를 제외하기 위한 반복문
						if (clone_word[r2] == '-')
							r2 = random.nextInt(choice_word.length()); //-로 바꿀 글자 위치 랜덤 생성
						else if (r2 != correct) {	
							break;
						}						
					}
					index[i] = r2;
					hidden[i] = clone_word[r2];
					clone_word[r2] = '-'; // 문자 대치
					correct = r2; //중복 확인을 위해 correct에 r2 저장
				}
				return clone_word;
			}
	
	//다음 게임을 진행할 지 묻는 메소드
			public void YesOrNo() {
				System.out.print("Next(y/n)?");
				String next = scanner.next();
				if (next.equals("y"))
					start(); //다시 게임 시작
				else if (next.equals("n")) {
					System.out.println("게임을 종료합니다."); //게임 종료
					System.out.println("1511632 김수빈");
					}
				}
	public static void main(String[] args) {
		HangManGame hangman = new HangManGame();
		System.out.println("지금부터 행맨 게임을 시작합니다."); //game 시작 출력
		hangman.start();
	}

}
