package org.craftedsw;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.exceptions.UserNotLoggedInException;
import org.craftedsw.trip.Trip;
import org.craftedsw.trip.TripDAO;
import org.craftedsw.user.User;
import org.craftedsw.user.UserSession;



public class TripService_Original {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
		User loggedUser = UserSession.getInstance().getLoggedUser();
		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				tripList = TripDAO.findTripsByUser(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}
	
}
