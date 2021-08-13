package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //Can't be replaced with @Repository or @Component !!!
public class HomeController {
	@GetMapping("/")
	public String home() {
		return "home"; // returns the view name
	}
}
