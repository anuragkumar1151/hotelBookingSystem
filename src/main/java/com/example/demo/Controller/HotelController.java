package com.example.demo.Controller;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Customer;
import com.example.demo.model.Hotel;
import com.example.demo.model.HotelBooking;
import com.example.demo.model.HotelRooms;

@Controller
public class HotelController {
	
	private String url="jdbc:mysql://localhost:3306/HotelBooking";
	private String user="root";
	private String pass="password";
	
	@RequestMapping("/")
	public ModelAndView hotelBooking() {
		System.out.println("Welcome....");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("welcome.html");
		return modelAndView;
	}
	
	@RequestMapping("/createNewCustomer")
	public ModelAndView createNewCustomer() {
		System.out.println("Inside CreateNewCustomer...");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("createNewCustomer.html");
		return modelAndView;
	}
	
	@RequestMapping("/addCustomerToDB")
	public ModelAndView addCustomerToDB(String firstName, String lastName,
			String emailAddress, String mobileNumber, String password) {
		String query="INSERT INTO Customer "
				+"VALUES(':firstName',':lastName',':emailAddress',':mobileNumber',':password');";
		query = query.replace(":firstName", firstName);
		query = query.replace(":lastName", lastName);
		query = query.replace(":emailAddress", emailAddress);
		query = query.replace(":mobileNumber", mobileNumber);
		query = query.replace(":password", password);
		System.out.println("Query is "+query);
		int resultSet = executeUpdate(query);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("welcome.html");
		return modelAndView;
	}
	
	@RequestMapping("/customerSignIn")
	public ModelAndView customerSignIn(String emailAddress, String password, HttpSession httpSession) throws SQLException {
		System.out.println("Inside Customer Sign In....");
		String query = "SELECT "
				+"* "
				+"FROM "
				+"Customer "
				+"WHERE "
				+"Customer.EMAIL_ADDRESS IN (':emailAddress') "
				+"AND Customer.PASSWORD IN (':password'); ";
		query = query.replace(":emailAddress", emailAddress);
		query = query.replace(":password", password);
		System.out.println("Query is "+query);
		ResultSet resultSet = executeQuery(query);
		ModelAndView modelAndView = new ModelAndView();
		if(resultSet.next() == false) {
			modelAndView.setViewName("welcome.html");
			System.out.println("No Such Customer Found....");
			return modelAndView;
		}
		else {
			Customer customer = new Customer();
			customer.setEmailAddress(resultSet.getString("EMAIL_ADDRESS"));
			customer.setFirstName(resultSet.getString("FIRST_NAME"));
			customer.setLastName(resultSet.getString("LAST_NAME"));
			customer.setMobileNumber(resultSet.getString("MOBILE_NUMBER"));
			customer.setPassword(resultSet.getString("PASSWORD"));
			httpSession.setAttribute("customer", customer);
			modelAndView.addObject("customer", customer);
			modelAndView.setViewName("customerHomePage.jsp");
			return modelAndView;
		}
	}
	
	@RequestMapping("/createNewHotel")
	public ModelAndView createNewHotel() {
		System.out.println("Inside createNewHotel....");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("createNewHotel.html");
		return modelAndView;
	}
	
	@RequestMapping("/addHotelToDB")
	public ModelAndView addHotelToDB(String hotelName, String hotelAddress,
			String emailAddress, String mobileNumber, String password) {
		String query="INSERT INTO Hotel "
				+"VALUES(':hotelName',':hotelAddress',':emailAddress',':mobileNumber',':password');";
		query = query.replace(":hotelName", hotelName);
		query = query.replace(":hotelAddress", hotelAddress);
		query = query.replace(":emailAddress", emailAddress);
		query = query.replace(":mobileNumber", mobileNumber);
		query = query.replace(":password", password);
		System.out.println("Query is "+query);
		int resultSet = executeUpdate(query);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("welcome.html");
		return modelAndView;
	}
	
	@RequestMapping("/hotelSignIn")
	public ModelAndView hotelSignIn(String emailAddress, String password, HttpSession httpSession) throws SQLException {
		System.out.println("Inside Hotel Sign In....");
		String query = "SELECT "
				+"* "
				+"FROM "
				+"Hotel "
				+"WHERE "
				+"Hotel.EMAIL_ADDRESS IN (':emailAddress') "
				+"AND Hotel.PASSWORD IN (':password'); ";
		query = query.replace(":emailAddress", emailAddress);
		query = query.replace(":password", password);
		System.out.println("Query is "+query);
		ResultSet resultSet = executeQuery(query);
		ModelAndView modelAndView = new ModelAndView();
		if(resultSet.next() == false) {
			modelAndView.setViewName("welcome.html");
			System.out.println("No Such Customer Found....");
			return modelAndView;
		}
		else {
			Hotel hotel = new Hotel();
			hotel.setHotelName(resultSet.getString("HOTEL_NAME"));
			hotel.setHotelAddress(resultSet.getString("HOTEL_ADDRESS"));
			hotel.setEmailAddress(resultSet.getString("EMAIL_ADDRESS"));
			hotel.setMobileNumber(resultSet.getString("MOBILE_NUMBER"));
			hotel.setPassword(resultSet.getString("PASSWORD"));
			System.out.println(hotel.toString());
			httpSession.setAttribute("hotel", hotel);
			modelAndView.addObject("hotel", hotel);
			modelAndView.setViewName("hotelHomePage.jsp");
			return modelAndView;
		}
	}
	
	@RequestMapping("/addRoomForHotel")
	public ModelAndView addRoomForHotel(String roomName, String roomType, String price, HttpSession httpSession) {
		Hotel hotel = (Hotel) httpSession.getAttribute("hotel");
		System.out.println(hotel.toString());
		String query = "INSERT INTO HOTEL_ROOMS (HOTEL_ROOM_NAME, EMAIL_ADDRESS, ROOM_TYPE, IS_AVAILABLE, PRICE) "
				+ "VALUES(':roomName', ':emailAddress', ':roomType', true, :price);";
		query = query.replace(":roomName", roomName);
		query = query.replace(":emailAddress", hotel.getEmailAddress());
		query = query.replace(":roomType", roomType);
		query = query.replace(":price", price);
		System.out.println(query);
		int resultSet = executeUpdate(query);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hotelHomePage.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/updatePriceOfRoom")
	public ModelAndView updatePriceOfRoom(String roomName, String roomType, String price, HttpSession httpSession) throws SQLException {
		Hotel hotel = (Hotel) httpSession.getAttribute("hotel");
		System.out.println(hotel.toString());
		String query = "SELECT * FROM HOTEL_ROOMS WHERE "
				+ "HOTEL_ROOMS.HOTEL_ROOM_NAME IN (':roomName') AND "
				+ "HOTEL_ROOMS.EMAIL_ADDRESS IN (':emailAddress') AND "
				+ "HOTEL_ROOMS.ROOM_TYPE IN (':roomType');";
		query = query.replace(":roomName", roomName);
		query = query.replace(":emailAddress", hotel.getEmailAddress());
		query = query.replace(":roomType", roomType);
		System.out.println(query);
		ResultSet resultSet = executeQuery(query);
		if(resultSet.next() == false) {
			System.out.println("No Such Room...");
		}
		else {
			int id = resultSet.getInt("HOTEL_ROOMS_ID");
			query = "UPDATE HOTEL_ROOMS "
					+ "SET "
					+ "PRICE = :price "
					+ "WHERE "
					+ "HOTEL_ROOMS.HOTEL_ROOMS_ID IN (:id);";
			query = query.replace(":id", String.valueOf(id));
			query = query.replace(":price", price);
			System.out.println(query);
			int result = executeUpdate(query);
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hotelHomePage.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/deleteRoom")
	public ModelAndView deleteRoom(String roomName, String roomType, HttpSession httpSession) throws SQLException {
		Hotel hotel = (Hotel) httpSession.getAttribute("hotel");
		System.out.println(hotel.toString());
		String query = "SELECT * FROM HOTEL_ROOMS WHERE "
				+ "HOTEL_ROOMS.HOTEL_ROOM_NAME IN (':roomName') AND "
				+ "HOTEL_ROOMS.EMAIL_ADDRESS IN (':emailAddress') AND "
				+ "HOTEL_ROOMS.ROOM_TYPE IN (':roomType');";
		query = query.replace(":roomName", roomName);
		query = query.replace(":emailAddress", hotel.getEmailAddress());
		query = query.replace(":roomType", roomType);
		System.out.println(query);
		ResultSet resultSet = executeQuery(query);
		if(resultSet.next() == false) {
			System.out.println("No Such Room...");
		}
		else {
			int id = resultSet.getInt("HOTEL_ROOMS_ID");
			query = "DELETE FROM HOTEL_ROOMS "
					+ "WHERE "
					+ "HOTEL_ROOMS.HOTEL_ROOMS_ID IN (:id);";
			query = query.replace(":id", String.valueOf(id));
			System.out.println(query);
			int result = executeUpdate(query);
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hotelHomePage.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/showAllRooms")
	public ModelAndView showAllRooms(HttpSession httpSession) throws SQLException {
		Hotel hotel = (Hotel)httpSession.getAttribute("hotel");
		String query = "SELECT * FROM HOTEL_ROOMS "
				+ "WHERE HOTEL_ROOMS.EMAIL_ADDRESS IN (':emailAddress');";
		query = query.replace(":emailAddress", hotel.getEmailAddress());
		ResultSet resultSet = executeQuery(query);
		ModelAndView modelAndView = new ModelAndView();
		if(resultSet.next() == false) {
			System.out.println("No Rooms for this Hotel....");
			modelAndView.setViewName("hotelHomePage.jsp");
			return modelAndView;
		}
		else {
			List<HotelRooms> hotelRoomsList = new ArrayList<HotelRooms>();
			do {
				HotelRooms hotelRooms = new HotelRooms();
				hotelRooms.setHotelRoomsId(resultSet.getInt("HOTEL_ROOMS_ID"));
				hotelRooms.setHotelRoomName(resultSet.getString("HOTEL_ROOM_NAME"));
				hotelRooms.setEmailAddress(resultSet.getString("EMAIL_ADDRESS"));
				hotelRooms.setRoomType(resultSet.getString("ROOM_TYPE"));
				hotelRooms.setAvailable(resultSet.getBoolean("IS_AVAILABLE"));
				hotelRooms.setPrice(resultSet.getInt("PRICE"));
				hotelRoomsList.add(hotelRooms);
			}while(resultSet.next());
			modelAndView.addObject("rooms", hotelRoomsList);
			modelAndView.setViewName("showAllRooms.jsp");
			return modelAndView;
		}
	}
	
	@RequestMapping("/showAllBookingsHotel")
	public ModelAndView showAllBookingsHotel(HttpSession httpSession) throws SQLException {
		Hotel hotel = (Hotel)httpSession.getAttribute("hotel");
		String query = "SELECT * FROM BOOKING "
				+ "WHERE BOOKING.HOTEL_EMAIL_ADDRESS IN (':emailAddress');";
		query = query.replace(":emailAddress", hotel.getEmailAddress());
		ResultSet resultSet = executeQuery(query);
		ModelAndView modelAndView = new ModelAndView();
		if(resultSet.next() == false) {
			System.out.print("No Bookings For the Hotel....");
			modelAndView.setViewName("hotelHomePage.jsp");
			return modelAndView;
		}
		else {
			List<HotelBooking> hotelBookingList = new ArrayList<HotelBooking>();
			do {
				HotelBooking hotelBooking = new HotelBooking();
				hotelBooking.setHotelBookingId(resultSet.getInt("BOOKING_ID"));
				hotelBooking.setCustomerEmailAddress(resultSet.getString("CUSTOMER_EMAIL_ADDRESS"));
				hotelBooking.setHotelEmailAddress(resultSet.getString("HOTEL_EMAIL_ADDRESS"));
				hotelBooking.setRoomType(resultSet.getString("HOTEL_ROOM_TYPE"));
				hotelBooking.setHotelRoomName(resultSet.getString("HOTEL_ROOM_NAME"));
				hotelBooking.setBookingFrom(resultSet.getDate("BOOKING_FROM"));
				hotelBooking.setBookingTo(resultSet.getDate("BOOKING_TO"));
				hotelBooking.setCost(resultSet.getInt("COST"));
				hotelBookingList.add(hotelBooking);
			}while(resultSet.next());
			modelAndView.addObject("booking", hotelBookingList);
			modelAndView.setViewName("showAllBookings.jsp");
			return modelAndView;
		}
	}
	
	@RequestMapping("/booking")
	public ModelAndView booking(String hotelEmailAddress, String roomType,
			String bookingFrom, String bookingTo, HttpSession httpSession) throws SQLException, ParseException {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date1 = LocalDate.parse(bookingFrom, format);
		LocalDate date2 = LocalDate.parse(bookingTo, format);
		int diff = (int)date1.until(date2, ChronoUnit.DAYS);
		System.out.println(diff);
		Customer customer = (Customer)httpSession.getAttribute("customer");
		String query = "SELECT * FROM HOTEL_ROOMS "
				+ "WHERE HOTEL_ROOMS.EMAIL_ADDRESS IN (':emailAddress') "
				+ "AND HOTEL_ROOMS.ROOM_TYPE IN (':roomType') AND "
				+ "HOTEL_ROOMS.IS_AVAILABLE = true;";
		query = query.replace(":emailAddress", hotelEmailAddress);
		query = query.replace(":roomType", roomType);
		System.out.println(query);
		ResultSet resultSet = executeQuery(query);
		ModelAndView modelAndView = new ModelAndView();
		if(resultSet.next() == false) {
			System.out.println("No Rooms Available....");
			modelAndView.setViewName("customerHomePage.jsp");
			return modelAndView;
		}
		else {
			String hotelRoomName = resultSet.getString("HOTEL_ROOM_NAME");
			int id = resultSet.getInt("HOTEL_ROOMS_ID");
			int price = resultSet.getInt("PRICE");
			int cost = diff*price;
			System.out.println(price+" "+cost);
			query = "INSERT INTO "
					+ "BOOKING(CUSTOMER_EMAIL_ADDRESS, HOTEL_EMAIL_ADDRESS, HOTEL_ROOM_TYPE, HOTEL_ROOM_NAME, "
					+ "BOOKING_FROM, BOOKING_TO, COST) "
					+ "VALUES(':customerEmailAddress', ':hotelEmailAddress', ':hotelRoomType', "
					+ "':hotelRoomName', ':bookingFrom', ':bookingTo', ':cost');";
			query = query.replace(":customerEmailAddress", customer.getEmailAddress());
			query = query.replace(":hotelEmailAddress", hotelEmailAddress);
			query = query.replace(":hotelRoomType", roomType);
			query = query.replace(":hotelRoomName", hotelRoomName);
			query = query.replace(":bookingFrom", bookingFrom);
			query = query.replace(":bookingTo", bookingTo);
			query = query.replace(":cost", String.valueOf(cost));
			System.out.println(query);		
			int result = executeUpdate(query);
			query = "UPDATE HOTEL_ROOMS "
					+ "SET HOTEL_ROOMS.IS_AVAILABLE = false "
					+ "WHERE HOTEL_ROOMS.HOTEL_ROOMS_ID IN (:id);";
			query = query.replace(":id", String.valueOf(id));
			System.out.print(query);;
			result = executeUpdate(query);		
		}
		modelAndView.setViewName("customerHomePage.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/cancelBooking")
	public ModelAndView cancelBooking(String bookingId, HttpSession httpSession) throws SQLException {
		Customer customer = (Customer)httpSession.getAttribute("customer");
		String query = "SELECT * FROM BOOKING "
				+ "WHERE BOOKING.BOOKING_ID IN (:bookingId) AND "
				+ "BOOKING.CUSTOMER_EMAIL_ADDRESS IN (':emailAddress');";
		query = query.replace(":bookingId", bookingId);
		query = query.replace(":emailAddress", customer.getEmailAddress());
		ResultSet resultSet = executeQuery(query);
		if(resultSet.next() == false) {
			System.out.println("No Bookings of Id....");
		}
		else {
			query = "UPDATE HOTEL_ROOMS SET "
					+ "HOTEL_ROOMS.IS_AVAILABLE = true "
					+ "WHERE HOTEL_ROOMS.EMAIL_ADDRESS IN (':emailAddress') AND "
					+ "HOTEL_ROOMS.ROOM_TYPE IN (':roomType') AND "
					+ "HOTEL_ROOMS.HOTEL_ROOM_NAME IN (':roomName');";
			query = query.replace(":emailAddress", resultSet.getString("HOTEL_EMAIL_ADDRESS"));
			query = query.replace(":roomType", resultSet.getString("HOTEL_ROOM_TYPE"));
			query = query.replace(":roomName", resultSet.getString("HOTEL_ROOM_NAME"));
			int result = executeUpdate(query);
			query = "DELETE FROM BOOKING "
					+ "WHERE BOOKING.BOOKING_ID IN (:bookingId) AND "
					+ "BOOKING.CUSTOMER_EMAIL_ADDRESS IN (':emailAddress');";
			query = query.replace(":bookingId", bookingId);
			query = query.replace(":emailAddress", customer.getEmailAddress());
			System.out.println(query);
			result = executeUpdate(query);
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("customerHomePage.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/showAllBookingsCustomer")
	public ModelAndView showAllBookingsCustomer(HttpSession httpSession) throws SQLException {
		Customer customer = (Customer)httpSession.getAttribute("customer");
		String query = "SELECT * FROM BOOKING "
				+ "WHERE BOOKING.CUSTOMER_EMAIL_ADDRESS IN (':customerEmailAddress');";
		query = query.replace(":customerEmailAddress", customer.getEmailAddress());
		ResultSet resultSet = executeQuery(query);
		ModelAndView modelAndView = new ModelAndView();
		if(resultSet.next() == false) {
			System.out.println("No Bookings....");
			modelAndView.setViewName("customerHomePage.jsp");
			return modelAndView;
		}
		else {
			List<HotelBooking> hotelBookingList = new ArrayList<HotelBooking>();
			do {
				HotelBooking hotelBooking = new HotelBooking();
				hotelBooking.setHotelBookingId(resultSet.getInt("BOOKING_ID"));
				hotelBooking.setCustomerEmailAddress(resultSet.getString("CUSTOMER_EMAIL_ADDRESS"));
				hotelBooking.setHotelEmailAddress(resultSet.getString("HOTEL_EMAIL_ADDRESS"));
				hotelBooking.setRoomType(resultSet.getString("HOTEL_ROOM_TYPE"));
				hotelBooking.setHotelRoomName(resultSet.getString("HOTEL_ROOM_NAME"));
				hotelBooking.setBookingFrom(resultSet.getDate("BOOKING_FROM"));
				hotelBooking.setBookingTo(resultSet.getDate("BOOKING_TO"));
				hotelBooking.setCost(resultSet.getInt("COST"));
				hotelBookingList.add(hotelBooking);
			}while(resultSet.next());
			modelAndView.addObject("booking", hotelBookingList);
			modelAndView.setViewName("showAllBookings.jsp");
			return modelAndView;
		}
	}
	
	public int executeUpdate(String query) {
		System.out.println("Inside executeUpdate....");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, pass);
			Statement statement = connection.createStatement();
			int resultSet = statement.executeUpdate(query);
			System.out.println("ResultSet is "+resultSet);
			return resultSet;
		}
		catch(Exception E) {
			System.out.println("Error in executing statement....");
			E.printStackTrace();
		}
		
		return -1;
	}
	
	public ResultSet executeQuery(String query) {
		System.out.println("Inside executeQuery....");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, pass);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			System.out.println("ResultSet is "+resultSet);
			return resultSet;
		}
		catch(Exception E) {
			System.out.println("Error in executing statement....");
			E.printStackTrace();
		}
		
		return null;
	}

}
