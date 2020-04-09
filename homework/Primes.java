import java.util.List;
import java.util.stream.*;

public class Primes {
	List<Integer> factors(Integer n) { // 어떤 한 수에 대해 약수를 구하는 메소드
		return IntStream.rangeClosed(1,n).filter(x -> n % x == 0).boxed().collect(Collectors.toList());
	}

	Boolean isPrime(Integer n){ // 어떤 한 수가 소수 인지를 판단하는 메소드
		return factors(n).equals(List.of(1,n));
	}
	List<Integer> primes(Integer n){ //범위 내의 숫자들 중 소수들을 리턴해주는 메소드
		return IntStream.rangeClosed(2,n).boxed().filter(x->isPrime(x) == true).collect(Collectors.toList());
	}

	public static void main(String[] args) {
		Primes p = new Primes();
		List<Integer> result = p.primes(40);
		System.out.println(result);
	}
}


