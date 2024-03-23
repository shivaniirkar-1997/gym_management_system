package model;

public class Machines {
	private int mid;
	private String mname;
	private String memname;
	private String bsession;
	private int booking;
	
	public Machines() {
		
	}
	

	public Machines(String mname) {
		super();
		this.mname = mname;
	}


	public Machines(int mid, String mname) {
	
		this.mid = mid;
		this.mname = mname;
	}
	
	public Machines(String memname, String bsession) {
		super();
		this.memname = memname;
		this.bsession = bsession;
	}
	

	public Machines(String mname, String memname, String bsession) {
		super();
		this.mname = mname;
		this.memname = memname;
		this.bsession = bsession;
	}

	public Machines(int mid, String mname, int booking) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.booking = booking;
	}

	public Machines(int mid, String mname, String memname, String bsession, int booking) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.memname = memname;
		this.bsession = bsession;
		this.booking = booking;
	}

	public Machines(int mid, String mname, String memname, String bsession) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.memname = memname;
		this.bsession = bsession;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMemname() {
		return memname;
	}

	public void setMemname(String memname) {
		this.memname = memname;
	}

	public String getBsession() {
		return bsession;
	}

	public void setBsession(String bsession) {
		this.bsession = bsession;
	}

	public int getBooking() {
		return booking;
	}

	public void setBooking(int booking) {
		this.booking = booking;
	}

	

}
