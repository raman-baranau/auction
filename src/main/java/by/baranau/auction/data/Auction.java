package by.baranau.auction.data;

import java.time.LocalDateTime;

public class Auction extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String lotName;
	private String lotDescription;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private double initialPrice;
	private double sellingPrice;
	private int clientId;
	private int ownerId;
	private AuctionType auctionType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLotName() {
		return lotName;
	}
	public void setLotName(String lotName) {
		this.lotName = lotName;
	}
	public String getLotDescription() {
		return lotDescription;
	}
	public void setLotDescription(String lotDescription) {
		this.lotDescription = lotDescription;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public double getInitialPrice() {
		return initialPrice;
	}
	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public AuctionType getAuctionType() {
		return auctionType;
	}
	public void setAuctionType(AuctionType auctionType) {
		this.auctionType = auctionType;
	}
}
