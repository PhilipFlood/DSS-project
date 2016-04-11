package entities;

import java.io.Serializable;

public class NetworkId implements Serializable{

	int mnc;
	int mcc;
	
	public NetworkId(){
		
	}
	
	public NetworkId(int mnc, int mcc){
		this.mnc = mnc;
		this.mcc = mcc;
	} 
	
	public int getMnc() {
		return mnc;
	}

	public void setMnc(int mnc) {
		this.mnc = mnc;
	}

	public int getMcc() {
		return mcc;
	}

	public void setMcc(int mcc) {
		this.mcc = mcc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mcc;
		result = prime * result + mnc;
		return result;
	}
 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NetworkId other = (NetworkId) obj;
		if(other.getMcc()!= mcc || other.getMnc()!= mnc){
			return false;
		}
		
		return true;
	}
 
}
