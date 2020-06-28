package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

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
				int[] flag = new int[database.size()];
				for(int i = 0; i < database.size(); i++) {
					for(int j = 0; j < database.size(); j++) {
						if(scoreArray[i] == database.get(hashkey[j]).getScore() && flag[j] == 0) {
							scoreKey[i] = hashkey[j];
							flag[j]++;
							break;
						}
					}
				}
				System.out.println("1. 특점 점수 이상 학생 출력");
				System.out.println("2. 특정 점수 이하 학생 출력");
				System.out.println("3. 특정 점수 대상 학생 출력");
				System.out.println("4. 전체 출력");
				System.out.println("옵션을 선택하십시오. : ");
				String option = br.readLine();
				if(option.charAt(0) == '1') {
					System.out.println("점수가 몇 점 이상인 학생을 출력 하시겠습니까? : ");
					Integer pivotscore = Integer.parseInt(br.readLine());
					if(Arrays.stream(scoreArray).anyMatch(x -> x == pivotscore)) {
						long count = Arrays.stream(scoreArray).filter(x -> x >= pivotscore).count();
						for(int i = (int) (database.size() - count); i < database.size(); i++) {
							System.out.println(database.get(scoreKey[i]).getName() + "," + database.get(scoreKey[i]).getScore());
						}
					}
					else {
						System.out.println("해당 점수를 받은 학생이 존재하지 않습니다.");
						break;
					}
				}
				
				else if(option.charAt(0) == '2') {
					System.out.println("점수가 몇 점 이하인 학생을 출력 하시겠습니까? : ");
					Integer pivotscore = Integer.parseInt(br.readLine());
					if(Arrays.stream(scoreArray).anyMatch(x -> x == pivotscore)) {
						long count = Arrays.stream(scoreArray).filter(x -> x <= pivotscore).count();
						for(int i = 0; i < count; i++) {
							System.out.println(database.get(scoreKey[i]).getName() + "," + database.get(scoreKey[i]).getScore());
						}
					}
					else {
						System.out.println("해당 점수를 받은 학생이 존재하지 않습니다.");
						break;
					}
				}
				
				else if(option.charAt(0) == '3') {
					System.out.println("점수가 몇 점인 학생을 출력 하시겠습니까? : ");
					Integer pivotscore = Integer.parseInt(br.readLine());
					if(Arrays.stream(scoreArray).anyMatch(x -> x == pivotscore)) {
						long count = Arrays.stream(scoreArray).filter(x -> x == pivotscore).count();
						int index = IntStream.range(0, database.size()).filter(x -> pivotscore.equals(scoreArray[x]))
								.findFirst().orElse(-1);
						for(int i = index; i < index + count; i++) {
							System.out.println(database.get(scoreKey[i]).getName() + "," + database.get(scoreKey[i]).getScore());
						}
					}
					else {
						System.out.println("해당 점수를 받은 학생이 존재하지 않습니다.");
						break;
					}
				}
				
				else if(option.charAt(0) == '4') {
					for(int i = 0; i < database.size(); i++) {
						System.out.println(database.get(scoreKey[i]).getName() + "," + database.get(scoreKey[i]).getScore());
					}
				}
				
				else {
					System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break;
			case 5:
				double result = 0;
				Object[] hashkey2 = database.keySet().toArray();
				Object[] scoreKey2 = new Object[database.size()];
				Integer[] scoreArray2 = new Integer[database.size()];
				for(int i = 0; i < database.size(); i++) {
					scoreArray2[i] = database.get(hashkey2[i]).getScore();
				}
				Arrays.sort(scoreArray2); // sort 메소드 사용
				int[] flag2 = new int[database.size()];
				for(int i = 0; i < database.size(); i++) {
					for(int j = 0; j < database.size(); j++) {
						if(scoreArray2[i] == database.get(hashkey2[j]).getScore() && flag2[j] == 0) {
							scoreKey2[i] = hashkey2[j];
							flag2[j]++;
							break;
						}
					}
				}
				System.out.println("1. 상위 N명 성적 평균 출력");
				System.out.println("2. 하위 N명 성적 평균 출력");
				System.out.println("3. 학생 성적 전체 평균 출력");
				System.out.println("메뉴를 선택하십시오 : ");
				String option2 = br.readLine();
				if(option2.charAt(0) == '1') {
					System.out.println("학생 숫자 입력 : ");
					Integer number = Integer.parseInt(br.readLine());
					if(number <= database.size()) {
						for(int i = database.size() - 1; i > database.size() - number; i--) {
							result += database.get(scoreKey2[i]).getScore();
						}
						result /= database.size();
						System.out.println("상위 " + number + "명 성적 평균 : " + result);
					}
					else {
						System.out.println("잘못된 숫자입니다.");
						break;
					}
				}
				
				else if(option2.charAt(0) == '2') {
					System.out.println("학생 숫자 입력 : ");
					Integer number = Integer.parseInt(br.readLine());
					if(number <= database.size()) {
						for(int i = 0; i < number; i++) {
							result += database.get(scoreKey2[i]).getScore();
						}
						result /= database.size();
						System.out.println("하위 " + number + "명 성적 평균 : " + result);
					}
					else {
						System.out.println("잘못된 숫자입니다.");
						break;
					}
				}
				
				else if(option2.charAt(0) == '3') {
					for(int i = 0; i < database.size(); i++) {
						result += database.get(scoreKey2[i]).getScore();
					}
					
					result /= database.size();
					System.out.println("학생 들의 전체 성적 평균 : " + result);
				}
				
				else {
					System.out.println("잘못된 입력입니다.");
					break;
				}
				
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				Object[] hashkey3 = database.keySet().toArray();
				for(int i = 0; i < database.size(); i++) {
					System.out.println(database.get(hashkey3[i]).getName() + "," + database.get(hashkey3[i]).getScore());
				}
				break;
			case 9:
				System.out.println("프로그램을 종료합니다.");
				return;
			}
		}
	}
}
