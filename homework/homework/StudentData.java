package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

import homework.Student;

public class StudentData {
	public static void main(String[] args) throws IOException {
		HashMap<String, Student> database = new HashMap<String, Student>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.printf("학생 성적 관리 프로그램 입니다.\n");
		
		
		while(true) {
			System.out.println();
			System.out.printf("1. 학생 정보 입력\n2. 학생 데이터 검색\n3. 학생 데이터 수정\n4. 학생 성적 별 출력\n5. 학생 성적 평균 출력\n");
			System.out.println("6. 특정 성적 학생 삭제\n7. 특정 이름 학생 삭제\n8. 학생 전체 데이터 출력\n9. 프로그램 종료\n");
			System.out.println("메뉴를 입력하세요 : ");
			Integer menu = Integer.parseInt(br.readLine());
			switch (menu) {
			case 1:
				Student student = new Student();
				System.out.println("학생의 학번을 입력하세요 : ");
				String str = br.readLine(); // 학번은 절대 중복 될 수 없으므로 해쉬 맵의 키 값으로 설정한다.
				System.out.println("학생의 이름을 입력하세요 : ");
				student.setName(br.readLine());
				System.out.println("학생의 성적을 입력하세요 : ");
				Integer score = Integer.parseInt(br.readLine());
				student.setScore(score);
				database.put(str, new Student(student.getName(), student.getScore()));
				break;
			case 2:
				while(true) {
					System.out.println("검색하고자 하는 학생의 학번을 입력하십시오 (실행 취소 : q): ");
					str = br.readLine();
					if(str.charAt(0) == 'q') break;
					else if(database.containsKey(str)) {
						System.out.println(database.get(str).getName() + "," + database.get(str).getScore());
						break;
					}
					else {
						System.out.println("입력하신 학번의 학생은 존재하지 않습니다.");
						System.out.println("학번을 다시 한번 확인 후 입력해 주십시오");
						System.out.println();
					}
				}
				
				break;
			case 3:
				while(true) {
					System.out.println("수정하고자 하는 학생의 학번을 입력하십시오(실행취소 : q): ");
					str = br.readLine();
					if(str.charAt(0) == 'q') break;
					else if(database.containsKey(str)) {
						System.out.println("학생의 이름을 입력하세요 : ");
						Student updateST = new Student();
						updateST.setName(br.readLine());
						System.out.println("학생의 성적을 입력하세요 : ");
						Integer updateSC = Integer.parseInt(br.readLine());
						updateST.setScore(updateSC);
						database.put(str, new Student(updateST.getName(), updateST.getScore()));
						break;
					}
					else {
						System.out.println("입력하신 학번의 학생은 존재하지 않습니다.");
						System.out.println("학번을 다시 한번 확인 후 입력해 주십시오");
						System.out.println();
					}
				}
				break;
			case 4:
				Object[] hashkey = database.keySet().toArray();
				Object[] scoreKey = new Object[database.size()];
				Integer[] scoreArray = new Integer[database.size()];
				for(int i = 0; i < database.size(); i++) {
					scoreArray[i] = database.get(hashkey[i]).getScore();
				}
				Arrays.sort(scoreArray); // sort 메소드 사용
				
				for(int i = 0; i < database.size(); i++) {
					for(int j = 0; j < database.size(); j++) {
						if(scoreArray[i] == database.get(hashkey[j]).getScore()) {
							scoreKey[i] = hashkey[j];
							break;
						}
					}
				}
				for(int i = 0; i < database.size(); i++) {
					System.out.println(database.get(scoreKey[i]).getName() + "," + database.get(scoreKey[i]).getScore());
				}
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				System.out.println("프로그램을 종료합니다.");
				return;
			}
		}
	}
}
