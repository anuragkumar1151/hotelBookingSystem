<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-color:grey">
	<p>WELCOME ${customer.firstName} ${customer.lastName}</p>
	<p>Book a Hotel</p>
	<form action="booking">
		HotelEmailAddress:<br>
		<input type="text" name="hotelEmailAddress"><br>
		<br>
		Room Type:<br>
		<input type="text" name="roomType"><br>
		<br>
		Booking From:<br>
		<input type="date" name="bookingFrom"><br>
		<br>
		Booking To:<br>
		<input type="date" name="bookingTo"><br>
		<br>
		<button name="Booking" type="submit" value="Booking">BOOK HOTEL</button>
	</form>
	<br>
	<p>Cancel Booking</p>
	<form action="cancelBooking">
		Booking Id:<br>
		<input type = "text" name="bookingId"><br>
		<br>
		<button name="Cancel Booking" type="submit" value="Cancel Booking">CANCEL BOOKING</button>
	</form>
	<br>
	<form action="showAllBookingsCustomer">
		<button name="Show all Bookings" type="submit" value="Show all Bookings">SHOW BOOKINGS</button>
	</form>
</body>
</html>