package by.baranau.auction.receiver;

import java.util.List;

import by.baranau.auction.dao.AuctionDAO;
import by.baranau.auction.data.Auction;
import by.baranau.auction.data.User;
import by.baranau.auction.data.UserType;
import by.baranau.auction.pool.ConnectionPool;
import by.baranau.auction.pool.ProxyConnection;

public class AuctionReceiver {
	
	public void createAuction(Auction auction) {
		ProxyConnection connection = ConnectionPool.getInstance().getConnection();
		AuctionDAO auctionDao = new AuctionDAO(connection);
		auctionDao.create(auction);
		auctionDao.closeConnection();
	}
	
	public boolean deleteAuction(Integer auctionId, User user) {
		ProxyConnection connection = ConnectionPool.getInstance().getConnection();
		AuctionDAO auctionDao = new AuctionDAO(connection);
		boolean result = false;
		Auction auction = auctionDao.findEntityById(auctionId);
		if (auction.getOwner().getId() == user.getId() 
		        || user.getUserType() == UserType.ADMIN) {
		    result = auctionDao.delete(auction);
		}
		 
		auctionDao.closeConnection();
		return result;
	}
	
	public Auction findAuction(Integer auctionId) {
		ProxyConnection connection = ConnectionPool.getInstance().getConnection();
		AuctionDAO auctionDao = new AuctionDAO(connection);
		Auction auction = auctionDao.findEntityById(auctionId);
		auctionDao.closeConnection();
		return auction;
	}
	
	public List<Auction> findAuctions() {
		ProxyConnection connection = ConnectionPool.getInstance().getConnection();
		AuctionDAO auctionDao = new AuctionDAO(connection);
		List<Auction> auctions = auctionDao.findAll();
		auctionDao.closeConnection();
		return auctions;
	}
	
	public List<Auction> findAuctionsByOwnerId(int id) {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        AuctionDAO auctionDao = new AuctionDAO(connection);
        List<Auction> auctions = auctionDao.findAuctionsByOwnerId(id);
        auctionDao.closeConnection();
        return auctions;
    }
	
	public boolean makeBid(double bidValue, int clientId, int auctionId) {
	    ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        AuctionDAO auctionDao = new AuctionDAO(connection);
        boolean result = auctionDao.makeBid(bidValue, clientId, auctionId);
        auctionDao.closeConnection();
        return result;
	}
}
