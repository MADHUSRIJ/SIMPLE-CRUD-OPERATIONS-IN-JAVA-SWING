import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DetailsCheck {

	private String FullName = "";
	private String UserName = "";
	private String DOB = "";
	private String Age = "";
	private String Gender = "";
	private String Marital = "";
	private String Address = "";
	private String State = "";
	private String Pincode = "";
	private String PhnNumber = "";
	private String AltPhnNumber = "";
	private String PassWord = "";
	
	//*************************************************//
	
	public void setFullName(String fullName) {
		if(fullName.length() < 26 && fullName.length() > 5 && !fullName.isEmpty()) {
			this.FullName = fullName;
		}
	}
	public String getFullName() {
		return FullName;
	}
	
	//*************************************************//
	
	public void setUserName(String userName) {
		if(userName.length()<31 && userName.length()>5 && userName.contains("@") && !userName.isEmpty() && !userName.equals("SELECT USERNAME")) {
			this.UserName = userName;
		}
	}
	
	public String getUserName() {
		return UserName;
	}
	
	//*************************************************//
	
	public void setDOB(String dOB) {
		boolean bool = false;
		if(!dOB.isEmpty()) {
			bool = true;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        dateFormat.setLenient(false);
	        try {
	            dateFormat.parse(dOB.trim());
	        } catch (ParseException pe) {
	            bool = false;
	        }
		}
		if(bool) {
			this.DOB = dOB;
		}
		
	}
	
	public String getDOB() {
		return DOB;
	}
	
	//*************************************************//
	
	public void setAge(String age) {
		if(!age.isEmpty()) {
			int int_Age = 0;
			try {
				int_Age = Integer.parseInt(age);
			}
			catch(Exception e){
				int_Age = 0;
			}
			
			if(int_Age!=0) {
				this.Age = age;
			}
		}
	}
	
	public String getAge() {
		return Age;
	}
	
	//*************************************************//
	
	public void setGender(String gender) {
		if(!gender.isEmpty()) {
			this.Gender = gender;
		}
	}
	
	public String getGender() {
		return Gender;
	}
	
	//*************************************************//
	
	public void setMarital(String marital) {
		if(!(marital.equals("STATUS"))) {
			this.Marital = marital;
		}
	}
	
	public String getMarital() {
		return Marital;
	}
	
	//*************************************************//
	
	public void setAddress(String address) {
		if(address.length()<51 && !address.isEmpty()) {
			this.Address = address;
		}
	}
	
	public String getAddress() {
		return Address;
	}
	
	//*************************************************//
	
	public void setState(String state) {
		if(!(state == "SELECT STATE")) {
			this.State = state;
		}
	}
	
	public String getState() {
		return State;
	}
	
	//*************************************************//
	
	public void setPincode(String pincode) {
	    long _Pin = 0;
		if(pincode.length() == 6) {
			try {
				_Pin = Integer.parseInt(pincode);
			}
			catch(Exception e) {
				_Pin = 0;
			}
		}
		
		if(_Pin != 0) {
			this.Pincode = pincode;
		}
	}
	
	public String getPincode() {
		return Pincode;
	}
	
	//*************************************************//
	
	public void setPhnNumber(String phnNumber) {
		long _Phn = 0;
		if(phnNumber.length() == 10) {
			try {
				_Phn = Long.parseLong(phnNumber);
			}
			catch(Exception e) {
				_Phn = 0;
			}
		}
		
		if(_Phn != 0) {
			this.PhnNumber = phnNumber;
		}
		
	}
	
	public String getPhnNumber() {
		return PhnNumber;
	}
	
	//*************************************************//
	
	public void setAltPhnNumber(String altPhnNumber) {
		long _Phn = 0;
		if(altPhnNumber.length() == 10) {
			try {
				_Phn = Long.parseLong(altPhnNumber);
			}
			catch(Exception e) {
				_Phn = 0;
			}
		}
		
		if(_Phn != 0) {
			this.AltPhnNumber = altPhnNumber;
		}
	}
	
	public String getAltPhnNumber() {
		return AltPhnNumber;
	}
	
	//*************************************************//
	
	public void setPassWord(String passWord) {
		if(passWord.length() > 6) {
			this.PassWord = passWord;
		}
	}
	
	public String getPassWord() {
		return PassWord;
	}
	
	//*************************************************//
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
