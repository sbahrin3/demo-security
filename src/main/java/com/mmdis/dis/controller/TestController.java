package com.mmdis.dis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmdis.dis.entity.User;
import com.mmdis.dis.jpa.Persistence;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("test")
public class TestController {
	
	Persistence db = Persistence.db();
	
	@RequestMapping("")
	public String ping() {
		return "SUCCESS";
	}
	
	@RequestMapping(value = "/readmykad", method = RequestMethod.POST)
	public String readMyKad(@RequestBody MyKadData data) {
		System.out.println("READ MY DATA:");
		
		String name = data.getName();
		String icno = data.getIcno();
		String oldno = data.getOldno();
		String birthDate = data.getBirthDate();
		String gender = data.getGender();
		String citizen = data.getCitizen();
		String race = data.getRace();
		String address1 = data.getAddress1();
		String address2 = data.getAddress2();
		
		
		System.out.println("name = " + name);
		System.out.println("icno = " + icno);
		System.out.println("oldno = " + oldno);
		System.out.println("birthDate = " + birthDate);
		System.out.println("gender = " + gender);
		System.out.println("citizen = " + citizen);
		System.out.println("race = " + race);
		System.out.println("address1 = " + address1);
		System.out.println("address1 = " + address2);
		
		
		
		return "Successfully read mykad of " + name;
	}
	
	@RequestMapping(value = "/send2", method = RequestMethod.POST)
	public String testSendData(@RequestParam String name) {
		System.out.println("testSendData: " + name);
		
		
		return "Ok";
	}
	
	@RequestMapping("/users")
	public ResponseEntity<DataTable> getUsers(HttpServletResponse res, HttpServletRequest req) {
		
		String orderColumn = req.getParameter("order[0][column]");
		String orderDirection = req.getParameter("order[0][dir]");
		String recordStart = req.getParameter("start");
		String recordLength = req.getParameter("length");
		String searchValue = req.getParameter("search[value]");
			
		int start = 0; 
		int length = 10;
		try {
			start = Integer.parseInt(recordStart);
		} catch ( Exception e ) { }
		
		try {
			length = Integer.parseInt(recordLength);
		} catch ( Exception e ) { }
		
		String q = "select u from User u ";
		String qf = "";
		if ( !"".equals(searchValue)) {
			qf += " where u.firstName like '%" + searchValue + "%' ";
			qf += " or u.lastName like '%" + searchValue + "%' ";
			q += qf;
		}
		
		List<User> users = db.list(q, start, length);

		//total number of records
		Long total = db.get("select count(u) from User u");
		Long totalFiltered = !"".equals(searchValue) ? db.get("select count(u) from User u " + qf) : total;
	    
	    DataTable<User> dataTable = new DataTable<User>();
	    dataTable.setData(users);
	    dataTable.setiTotalRecords(total);
	    dataTable.setiTotalDisplayRecords(totalFiltered);
	    	
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("My-Header", "Hello");
		return new ResponseEntity<DataTable>(dataTable, headers, HttpStatus.OK);
		
	}
	
	@RequestMapping("/list_users")
	public ResponseEntity<List<User>> getUsers(@RequestParam int start, int length) {
				
		System.out.println("start=" + start);
		System.out.println("length=" + length);
				
		String q = "select u from User u";
		List<User> users = db.list("select u from User u");
		
		//guna cb
		
		//List<User> users = db.list(q, start, length);
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("My-Header", "hello");
	    	    
		return new ResponseEntity<List<User>>(users, headers, HttpStatus.OK);
		
	}
	
	@PostMapping("/listUsers")
	public ResponseEntity<List<User>> listUsers(@RequestBody DataTable dataTable) {
		
		System.out.println("Invoking List users.");
		System.out.println("Request Body: " + dataTable);
		
		List<User> users = db.list("select u from User u");
		
		
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("My-Header", "hello"); 	    
		return new ResponseEntity<List<User>>(users, headers, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@RequestParam String id) {
		
		User user = db.find(User.class, id);
		if ( user != null ) {
		    HttpHeaders headers = new HttpHeaders();
		    headers.add("My-Header", "hello");
			return new ResponseEntity<User>(user, headers, HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			
		}
		
	}
	


}
