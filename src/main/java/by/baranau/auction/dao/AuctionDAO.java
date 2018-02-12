package by.baranau.auction.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import by.baranau.auction.data.Auction;
import by.baranau.auction.data.AuctionType;
import by.baranau.auction.data.User;
import by.baranau.auction.pool.ConnectionPool;
import by.baranau.auction.pool.ProxyConnection;

public class AuctionDAO extends AbstractDAO<Integer, Auction> {
	
	private static final String SQL_CREATE_AUCTION = 
			"INSERT INTO AUCTIONS VALUES(NULL, ?, ?, ?, ?, ?, NULL, NULL, ?, ?)";
	
	private static final String SQL_DELETE_AUCTION = 
			"DELETE FROM AUCTIONS WHERE AUCTION_ID=?";
	
	private static final String SQL_FIND_AUCTION_BY_ID =
			"SELECT AUCTION_ID, LOT_NAME, LOT_DESCRIPTION, START_DATE, END_DATE, "
			+ "INITIAL_PRICE, SELLING_PRICE, CLIENT_ID, OWNER_ID, AUCTION_TYPE "
			+ "FROM AUCTIONS "
			+ "WHERE AUCTION_ID=?";
	
	private static final String SQL_FIND_ALL_AUCTIONS =
			"SELECT AUCTION_ID, LOT_NAME, LOT_DESCRIPTION, START_DATE, END_DATE, "
			+ "INITIAL_PRICE, SELLING_PRICE, CLIENT_ID, OWNER_ID, AUCTION_TYPE FROM AUCTIONS";
	
	private static final String SQL_FIND_AUCTIONS_BY_OWNER_ID =
	        "SELECT AUCTION_ID, LOT_NAME, LOT_DESCRIPTION, START_DATE, END_DATE, "
            + "INITIAL_PRICE, SELLING_PRICE, CLIENT_ID, OWNER_ID, AUCTION_TYPE FROM AUCTIONS "
            + "WHERE OWNER_ID=?";
	
	private static final String SQL_SELECT_BID_OPTIONS =
	        "SELECT SELLING_PRICE, CLIENT_ID "
	        + "FROM AUCTIONS "
	        + "WHERE AUCTION_ID=? "
	        + "FOR UPDATE";
	
	private static final String SQL_AUCTIONS_MAKE_BID =
	        "UPDATE AUCTIONS "
	        + "SET SELLING_PRICE=?, CLIENT_ID=? "
	        + "WHERE AUCTION_ID=?";
	
	private ProxyConnection connection;
	
	private UserDAO userDao;
	
	public AuctionDAO(ProxyConnection connection) {
		this.connection = connection;
		userDao = new UserDAO(connection);
	}
	
	@Override
	public List<Auction> findAll() {
		List<Auction> auctions = new ArrayList<Auction>();
		PreparedStatement st = null;
		
		try {
			st = connection.prepareStatement(SQL_FIND_ALL_AUCTIONS);
			ResultSet resultSet = st.executeQuery();
			buildAuctionsList(resultSet, auctions);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return auctions;
	}
	
	public List<Auction> findAuctionsByOwnerId(int id) {
	    List<Auction> auctions = new ArrayList<>();
	    PreparedStatement statement = null;
	    
	    try {
	        statement = connection.prepareStatement(SQL_FIND_AUCTIONS_BY_OWNER_ID);
	        statement.setInt(1, id);
	        ResultSet resultSet = statement.executeQuery();
	        buildAuctionsList(resultSet, auctions);
	    } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
	        try {
                statement.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	    }
	    return auctions;
	}
	
	@Override
	public Auction findEntityById(Integer id) {
		PreparedStatement st = null;
		Auction auction = null;
		
		try{
			st = connection.prepareStatement(SQL_FIND_AUCTION_BY_ID);
			st.setInt(1, id);
			ResultSet resultSet = st.executeQuery();
			resultSet.next();
			auction = new Auction();
			auction.setId(resultSet.getInt(1));
			auction.setLotName(resultSet.getString(2));
			auction.setLotDescription(resultSet.getString(3));
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
			auction.setStartDate(LocalDateTime.parse(resultSet.getString(4), dtf));
			auction.setEndDate(LocalDateTime.parse(resultSet.getString(5), dtf));
			auction.setInitialPrice(resultSet.getDouble(6));
			auction.setSellingPrice(resultSet.getDouble(7));
			
			int entityId = resultSet.getInt(8);
            User client = null;
            if (entityId != 0) {
                client = userDao.findEntityById(resultSet.getInt(8));
            }               
			
            auction.setClient(client);
            User owner = userDao.findEntityById(resultSet.getInt(9));
            auction.setOwner(owner);
			auction.setAuctionType(AuctionType.valueOf(resultSet.getString(10)));
		} catch (SQLException e) {
			
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return auction;
	}

	@Override
	public boolean delete(Integer id) {
		PreparedStatement st = null;
		boolean result = false;
		
		try{
			st = connection.prepareStatement(SQL_DELETE_AUCTION);
			st.setInt(1, id);
			result = (st.executeUpdate() > 0) ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean delete(Auction entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Auction entity) {
		PreparedStatement st = null;
		
		try{
			st = connection.prepareStatement(SQL_CREATE_AUCTION);
			st.setString(1, entity.getLotName());
			st.setString(2, entity.getLotDescription());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			st.setString(3, entity.getStartDate().format(formatter));
			st.setString(4, entity.getEndDate().format(formatter));
			st.setDouble(5, entity.getInitialPrice());
			st.setInt(6, entity.getOwner().getId());
			st.setString(7, entity.getAuctionType().toString());
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		return true;
	}

	@Override
	public Auction update(Auction entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean makeBid(double bidValue, int clientId, int auctionId) {
	    boolean result = false;
	    
	    PreparedStatement statement = null;
	    PreparedStatement updateStatement = null;
	    
	    try {
	        connection.setAutoCommit(false);
	        statement = connection.prepareStatement(SQL_SELECT_BID_OPTIONS);
	        statement.setInt(1, auctionId);
	        ResultSet resultSet = statement.executeQuery();
	        resultSet.next();
	        double lastBid = resultSet.getDouble("SELLING_PRICE");
	        int oldClientId = resultSet.getInt("CLIENT_ID");
	        
	        if (clientId == oldClientId) {
	            return false;
	        }
	        
	        updateStatement = connection.prepareStatement(SQL_AUCTIONS_MAKE_BID);
	        if (bidValue > lastBid) {
	            updateStatement.setDouble(1, bidValue);
	            updateStatement.setInt(2, clientId);
	            updateStatement.setInt(3, auctionId);
	            updateStatement.executeUpdate();
	        }
	        connection.commit();
	    } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
	        try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	        try {
                statement.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	        try {
                updateStatement.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	    }
	    
	    return result;
	}
	
	public void closeConnection() {
		ConnectionPool.getInstance().closeConnection(connection);
	}
	
	private void buildAuctionsList(ResultSet resultSet, List<Auction> auctions) 
	        throws SQLException {
	    while (resultSet.next()) {
    	    Auction auction = new Auction();
            auction.setId(resultSet.getInt(1));
            auction.setLotName(resultSet.getString(2));
            auction.setLotDescription(resultSet.getString(3));
            auction.setStartDate(LocalDateTime.ofInstant(resultSet.getTimestamp(4).toInstant(),
                    ZoneId.systemDefault()));
            auction.setEndDate(LocalDateTime.ofInstant(resultSet.getTimestamp(5).toInstant(),
                    ZoneId.systemDefault()));
            auction.setInitialPrice(resultSet.getDouble(6));
            auction.setSellingPrice(resultSet.getDouble(7));
            
            int entityId = resultSet.getInt(8);
            User client = null;
            if (entityId != 0) {
                client = userDao.findEntityById(resultSet.getInt(8));
            }               
            auction.setClient(client);
            
            User owner = userDao.findEntityById(resultSet.getInt(9));
            auction.setOwner(owner);
            auction.setAuctionType(AuctionType.valueOf(resultSet.getString(10)));
            auctions.add(auction);
	    }
	}
}
