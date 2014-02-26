package uk.ac.dundee.computing.mhairi.storage;

public class LoginStorage {

	private String UserN;
	private String PassW;
	private boolean auth;
	private String Type;

	public String getUserN() {
		return UserN;
	}

	public void setUserN(String u) {
		UserN = u;
	}

	public String getPassW() {
		return PassW;
	}

	public void setPassW(String p) {
		PassW = p;
	}

	public boolean getAuth() {
		return auth;
	}

	public void setAuth(boolean a) {
		auth = a;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	
}
