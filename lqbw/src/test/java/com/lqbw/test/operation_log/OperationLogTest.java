package com.lqbw.test.operation_log;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.lqbw.model.student.Student;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OperationLogTest {
	@Autowired
	private LogControllerTest logController;

	MockHttpServletRequest req = null;

	@Before
	public void setUp() throws Exception {
		req = new MockHttpServletRequest();
		Student user = new Student();
		user.setId("1234567890");
		req.getSession().setAttribute("user", user);
		req.setParameter("bId", "123456");
	}

	@Test
	public void testSave() {
		ModelAndView mv = logController.testSave(req);

		ModelAndViewAssert.assertViewName(mv, "save");
	}

	@Test
	public void testUpdate() {
		ModelAndView mv = logController.testUpdate(req);

		ModelAndViewAssert.assertViewName(mv, "update");
	}

	@Test
	public void testDelete() {
		ModelAndView mv = logController.testDelete(req);

		ModelAndViewAssert.assertViewName(mv, "delete");
	}

	@Test
	public void testThrow() {
		try {
			logController.testThrow(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNoReq1() {
		logController.testNoReq();
	}
	
	@Test
	public void testNoReq2() {
		logController.testNoReq("123");
	}
}
