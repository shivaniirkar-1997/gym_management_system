package model;

public class Member {
 private int mid;
 private String mfname;
 private String mlname;
 private String mgender;
 private String mcontact;
 private double mweight;
 private double mheight;
 private String mprogram;
 private String mtrainer;
 private String msession;
 private String mmembership;
 
public Member() {
	
}

public Member(int mid, String mfname, String mlname, String mgender, String mcontact, double mweight, double mheight,
		String mprogram, String mtrainer, String msession, String mmembership) {
	
	this.mid = mid;
	this.mfname = mfname;
	this.mlname = mlname;
	this.mgender = mgender;
	this.mcontact = mcontact;
	this.mweight = mweight;
	this.mheight = mheight;
	this.mprogram = mprogram;
	this.mtrainer = mtrainer;
	this.msession = msession;
	this.mmembership = mmembership;
}

public Member(int mid, String mfname, String mlname, String mgender, String mcontact, double mweight, double mheight,
		String mprogram, String msession) {
	super();
	this.mid = mid;
	this.mfname = mfname;
	this.mlname = mlname;
	this.mgender = mgender;
	this.mcontact = mcontact;
	this.mweight = mweight;
	this.mheight = mheight;
	this.mprogram = mprogram;
	this.msession = msession;
}

public int getMid() {
	return mid;
}

public void setMid(int mid) {
	this.mid = mid;
}

public String getMfname() {
	return mfname;
}

public void setMfname(String mfname) {
	this.mfname = mfname;
}

public String getMlname() {
	return mlname;
}

public void setMlname(String mlname) {
	this.mlname = mlname;
}

public String getMgender() {
	return mgender;
}

public void setMgender(String mgender) {
	this.mgender = mgender;
}

public String getMcontact() {
	return mcontact;
}

public void setMcontact(String mcontact) {
	this.mcontact = mcontact;
}

public double getMweight() {
	return mweight;
}

public void setMweight(double mweight) {
	this.mweight = mweight;
}

public double getMheight() {
	return mheight;
}

public void setMheight(double mheight) {
	this.mheight = mheight;
}

public String getMprogram() {
	return mprogram;
}

public void setMprogram(String mprogram) {
	this.mprogram = mprogram;
}

public String getMtrainer() {
	return mtrainer;
}

public void setMtrainer(String mtrainer) {
	this.mtrainer = mtrainer;
}

public String getMsession() {
	return msession;
}

public void setMsession(String msession) {
	this.msession = msession;
}

public String getMmembership() {
	return mmembership;
}

public void setMmembership(String mmembership) {
	this.mmembership = mmembership;
}


 
 
 
}
