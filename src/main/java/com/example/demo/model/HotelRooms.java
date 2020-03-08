package com.example.demo.model;

public class HotelRooms {
	
	private int hotelRoomsId;
	private String hotelRoomName;
	private String emailAddress;
	private String roomType;
	private boolean isAvailable;
	private int price;
	
	public int getHotelRoomsId() {
		return hotelRoomsId;
	}
	public void setHotelRoomsId(int hotelRoomsId) {
		this.hotelRoomsId = hotelRoomsId;
	}
	public String getHotelRoomName() {
		return hotelRoomName;
	}
	public void setHotelRoomName(String hotelRoomName) {
		this.hotelRoomName = hotelRoomName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Hotel Rooms is hotelRoomsId: "+hotelRoomsId
				+" hotelRoomName: "+hotelRoomName
				+" emailAddress: "+emailAddress
				+" roomType: "+roomType
				+" price: "+price;
	}

}
