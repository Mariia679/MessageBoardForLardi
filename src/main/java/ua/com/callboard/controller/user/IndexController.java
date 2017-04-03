package ua.com.callboard.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.callboard.service.TopicService;

@Controller
public class IndexController {

	@Autowired
	private TopicService topicService;

	/**
	 * This const used for test
	 */
	public static final String VIEW_TODO_ADD = "user-index";

	public static final String MODEL_ATTRIBUTE = "headTopics";

	public static final String VIEW_TODO_LOGIN = "user-login";

	public static final String VIEW_TODO_ADMIN = "admin-admin";

	/**
	 * 
	 * @return folder user file index.jsp
	 */
	@RequestMapping("/")
	public String index() {
		return "user-index";
	}

	/**
	 * 
	 * @return folder admin file admin.jsp
	 */
	@GetMapping("/admin")
	public String admin() {
		return "admin-admin";
	}

	/**
	 * 
	 * @return folder user file login.jsp
	 */

	@GetMapping("/login")
	public String login() {
		return "user-login";
	}
}
