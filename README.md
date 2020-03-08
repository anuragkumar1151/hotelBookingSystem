# hotelBookingSystem
Hi this is the project for the Hotel Booking System.

First Talking about the Tables:

1. Customer - CREATE TABLE `customer` (
  `FIRST_NAME` varchar(30) NOT NULL,
  `LAST_NAME` varchar(30) NOT NULL,
  `EMAIL_ADDRESS` varchar(100) NOT NULL,
  `MOBILE_NUMBER` varchar(10) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  PRIMARY KEY (`EMAIL_ADDRESS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

2. Hotel - CREATE TABLE `hotel` (
  `HOTEL_NAME` varchar(50) NOT NULL,
  `HOTEL_ADDRESS` varchar(100) NOT NULL,
  `EMAIL_ADDRESS` varchar(100) NOT NULL,
  `MOBILE_NUMBER` varchar(10) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  PRIMARY KEY (`EMAIL_ADDRESS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

3. HOTEL_ROOMS - CREATE TABLE `hotel_rooms` (
  `HOTEL_ROOMS_ID` int NOT NULL AUTO_INCREMENT,
  `HOTEL_ROOM_NAME` varchar(10) NOT NULL,
  `EMAIL_ADDRESS` varchar(100) NOT NULL,
  `ROOM_TYPE` varchar(100) NOT NULL,
  `IS_AVAILABLE` tinyint(1) NOT NULL,
  `PRICE` int NOT NULL,
  PRIMARY KEY (`HOTEL_ROOMS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

4. BOOKING - CREATE TABLE `booking` (
  `BOOKING_ID` int NOT NULL AUTO_INCREMENT,
  `CUSTOMER_EMAIL_ADDRESS` varchar(100) NOT NULL,
  `HOTEL_EMAIL_ADDRESS` varchar(100) NOT NULL,
  `HOTEL_ROOM_TYPE` varchar(100) NOT NULL,
  `HOTEL_ROOM_NAME` varchar(10) NOT NULL,
  `BOOKING_FROM` date NOT NULL,
  `BOOKING_TO` date NOT NULL,
  `COST` int NOT NULL,
  PRIMARY KEY (`BOOKING_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

For the webpages go into /src/main/webapp/:

1. createNewCustomer.html - Gets the information to create a new Customer.
2. createNewHotel.html - Similar to createNewCustomer but for the creation of a new hotel.
3. customerHomePage.jsp - A page where customer can book a room, cancel a booking and show all the present bookings by the customer.
4. hotelHomePage.jsp - A page where a hotel manager can add a room, update price of a room for surge pricing, remove a room, get
                        information about all the rooms and all the bookings done at the hotel.
5. showAllBookings.jsp - To display information of all the bookings for hotel and customer.
6. showAllRooms.jsp - To display all the rooms at the hotel.
7. welcome.html - Initial page for sign in and creation of customer and hotel.

Objects can be found at /src/main/java/com/example/demo/model/:

1. Customer.java - Object for customer.
2. Hotel.java - Object for Hotel.
3. HotelBooking.java - Object for Hotel Booking.
4. HotelRooms.java - Object for Hotel Rooms.

The controller can be found at /src/main/java/com/example/demo/Controller/HotelController.java which contains all the methods
for updating, delete, create, select and calculate (cost) various parameters.
