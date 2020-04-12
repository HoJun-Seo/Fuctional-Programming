import java.util.List;
import java.util.stream.*;

public class QsortStream {
	static List<Integer> qsort(List<Integer> list) {
		if(list.size() <= 1) return list;
		Integer pivot = list.remove(0); //0번째 원소를 떼어내어 pivot 으로 한다.(첫번째 값을 기준값으로 설정한다.)
		List<Integer> lesser;
		List<Integer> greater;

		lesser = list.stream().filter(x -> pivot > x).collect(Collectors.toList()); //스트림으로 변환한 이후 pivot 보다 작은 원소들을 filter 한다.
		greater = list.stream().filter(x -> pivot < x).collect(Collectors.toList());

		//재귀적 quick sort 적용

		lesser = qsort(lesser); //각 리스트 내부에서도 정렬을 수행해야 하므로 재귀적으로 메소드를 호출해준다.
		greater = qsort(greater);

		//Concatenation using stream
		//concat is a static method
		lesser.add(pivot);
		Stream<Integer> combined = Stream.concat(lesser.stream(), greater.stream());
		return combined.collect(Collectors.toList());
	}

	public static void main(String []args){
		List<Integer> list = Stream.of(5,9,2,3,7,4,3,1,8,3,6,3).collect(Collectors.toList());
		System.out.println("Input : " + list);
		List<Integer> sorted = qsort(list);
		System.out.println("Sorted : " + sorted);
	}
}
