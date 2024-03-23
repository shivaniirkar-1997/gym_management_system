package model;

public class Trainer {
	private int tid;
	private String tname;
	private String tcontact;
	private String tgender;
	private String tProfile;
	public Trainer() {
		super();
	}
	
	
	

	public Trainer(String tname, String tcontact) {
		super();
		this.tname = tname;
		this.tcontact = tcontact;
	}


	public Trainer(int tid, String tname, String tcontact, String tgender, String tProfile) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.tcontact = tcontact;
		this.tgender = tgender;
		this.tProfile = tProfile;
	}

	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTcontact() {
		return tcontact;
	}
	public void setTcontact(String tcontact) {
		this.tcontact = tcontact;
	}
	public String getTgender() {
		return tgender;
	}
	public void setTgender(String tgender) {
		this.tgender = tgender;
	}
	public String gettProfile() {
		return tProfile;
	}
	public void settProfile(String tProfile) {
		this.tProfile = tProfile;
	}
	
	

}
