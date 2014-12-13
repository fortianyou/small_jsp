package wanczy.servlet.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wanczy.dao.StudentDao;
import wanczy.entity.Wanczy_Student;

public class FindAll extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentDao studentDao = new StudentDao();
		Wanczy_Student s = new Wanczy_Student();
		List studentList = null;
		try {
			studentList = studentDao.find(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("studentList", studentList);
		getServletContext().getRequestDispatcher("/student_jsp/FindAll.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
