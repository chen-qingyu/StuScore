package stuscore.entity;

/**
 *	考试类型实体类
 */
public class EType {
	
	private int id;

	private String name;

	private String comm;

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

	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	@Override
	public String toString() {
		return "EType [id=" + id + ", name=" + name + ", comm=" + comm + "]";
	}
}
