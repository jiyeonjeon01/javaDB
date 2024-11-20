package studentTest_2;

import java.sql.Date;

public class Students {
	private int id;
	private String name;
	private Date birth;
	private String addr;
	
	public Students (int id, String name, Date birth, String addr) {
		super();
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.addr = addr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@Override
	public String toString() { 
		return "Students [ id = " + id + ", name = " + name + 
				", birth = " + birth + ", addr = " + addr + " ]";
	}
}
