package jk.framework.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jk.framework.test.entity.TestEntity;
import jk.framework.test.service.TestService;

@Controller
public class TestController {
	
	@Autowired
	TestService testService;
	
	/* Test Code */
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "home.test";
	}
	
	@RequestMapping("/test")
	public String test(Model model) {
		model.addAttribute("test", "test입니다.");
		return "test";
	}
	
	@GetMapping("/select")
	public ModelAndView readTest() {
		List<TestEntity> testList = testService.findByBno(1);
		ModelAndView nextPage = new ModelAndView("test/readTest");
		nextPage.addObject("testList", testList);
		return nextPage;
	}
	/* Test Code */
	
	@GetMapping("/list")
	@ResponseBody
	public List<TestEntity> list() {
		List<TestEntity> list = testService.findAll();
		return list;
	}
 
}
