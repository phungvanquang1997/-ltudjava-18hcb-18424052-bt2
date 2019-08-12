package ImportCsv;

public class RoleManagement {
	public int access = 0; // for students
	String roleName;
	
	public RoleManagement(int access, String roleName) {
		this.access = access;
		this.roleName = roleName;
	}

	public int isAccessable()
	{
		return this.access;
	}
}
