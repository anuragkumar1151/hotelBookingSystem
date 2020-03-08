<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-color:grey">
	<p>WELCOME ${hotel.hotelName}</p>
	<br>
	<p>ADD A ROOM</p>
	<br>
	<form action="addRoomForHotel">
		Room Name:<br>
		<input type="text" name="roomName"><br>
		<br>
		Room Type:<br>
		<input type="text" name="roomType"><br>
		<br>
		Price<br>
		<input type="text" name="price"><br>
		<br>
		<button name="Add Room Type" type="submit" value="Add Room Type">ADD ROOM</button>
	</form>
	<p>UPDATE PRICE OF ROOM</p>
	<form action="updatePriceOfRoom">
		Room Name:<br>
		<input type="text" name="roomName"><br>
		<br>
		Room Type:<br>
		<input type="text" name="roomType"><br>
		<br>
		Price<br>
		<input type="text" name="price"><br>
		<br>
		<button name="Update Room Price" type="submit" value="Update Room Price">UPDATE PRICE</button>
	</form>
	<p>DELETE ROOM</p>
	<form action="deleteRoom">
		Room Name:<br>
		<input type="text" name="roomName"><br>
		<br>
		Room Type:<br>
		<input type="text" name="roomType"><br>
		<br>
		<button name="Delete Room" type="submit" value="Delete Room">REMOVE ROOM</button>
	</form>
	<br>
	<form action="showAllRooms">
		<button name="Show All Rooms" type="submit" value="Show All Rooms">SHOW ALL ROOMS</button>
	</form>
	<br>
	<form action="showAllBookingsHotel">
		<button name="Show All Bookings" type="submit" value="Show All Bookings">SHOW ALL BOOKINGS</button>
	</form>
</body>
</html>