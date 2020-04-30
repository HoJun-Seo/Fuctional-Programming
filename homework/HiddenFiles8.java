import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.*;
import java.io.IOException;

public class HiddenFiles8 {
	public static void main(String []args) throws IOException {
		Path path = Paths.get("."); //current directory
		Stream<Path> files = Files.list(path);
		files.filter(x -> x.toString().startsWith("./.")).forEach(x -> System.out.println(x.toString().substring(3)));
	}
}