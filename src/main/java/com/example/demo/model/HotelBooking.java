package com.example.demo.model;

import java.util.Date;

public class HotelBooking {
	
	private int hotelBookingId;
	private String customerEmailAddress;
	private String hotelEmailAddress;
	private String roomType;
	private String hotelRoomName;
	private Date bookingFrom;
	private Date bookingTo;
	private int cost;
	
	public int getHotelBookingId() {
		return hotelBookingId;
	}
	public void setHotelBookingId(int hotelBookingId) {
		this.hotelBookingId = hotelBookingId;
	}
	public String getCustomerEmailAddress() {
		return customerEmailAddress;
	}
	public void setCustomerEmailAddress(String customerEmailAddress) {
		this.customerEmailAddress = customerEmailAddress;
	}
	public String getHotelEmailAddress() {
		return hotelEmailAddress;
	}
	public void setHotelEmailAddress(String hotelEmailAddress) {
		this.hotelEmailAddress = hotelEmailAddress;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getHotelRoomName() {
		return hotelRoomName;
	}
	public void setHotelRoomName(String hotelRoomName) {
		this.hotelRoomName = hotelRoomName;
	}
	public Date getBookingFrom() {
		return bookingFrom;
	}
	public void setBookingFrom(Date bookingFrom) {
		this.bookingFrom = bookingFrom;
	}
	public Date getBookingTo() {
		return bookingTo;
	}
	public void setBookingTo(Date bookingTo) {
		this.bookingTo = bookingTo;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		return "Booking is hotelBookingId: "+hotelBookingId
				+" customerEmailAddress: "+customerEmailAddress
				+" hotelEmailAddress: "+hotelEmailAddress
				+" roomType: "+roomType
				+" hotelRoomName: "+hotelRoomName
				+" bookingFrom: "+bookingFrom.toString()
				+" bookingTo: "+bookingTo.toString()
				+" cost: "+cost;
				
	}
}
