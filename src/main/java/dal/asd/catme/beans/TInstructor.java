package dal.asd.catme.beans;

import java.util.List;

public class TInstructor extends User
{
    List<Course> coursesHandled;

	public List<Course> getCoursesHandled() {
		return coursesHandled;
	}

	public void setCoursesHandled(List<Course> coursesHandled) {
		this.coursesHandled = coursesHandled;
	}
	
	 @Override
		public String getBannerId() {
			return super.getBannerId();
		}

		@Override
		public void setBannerId(String bannerId) {
			super.setBannerId(bannerId);
		}

		@Override
		public String getLastName() {
			return super.getLastName();
		}

		@Override
		public void setLastName(String lastName) {
			super.setLastName(lastName);
		}

		@Override
		public String getFirstName() {
			return super.getFirstName();
		}

		@Override
		public void setFirstName(String firstName) {
			super.setFirstName(firstName);
		}

		@Override
		public String getEmail() {
			return super.getEmail();
		}

		@Override
		public void setEmail(String email) {
			super.setEmail(email);
		}

		@Override
		public String getPassword() {
			return super.getPassword();
		}

		@Override
		public void setPassword(String password) {
			super.setPassword(password);
		}

		@Override
		public List<Role> getRole() {
			return super.getRole();
		}

		@Override
		public void setRole(List<Role> role) {
			super.setRole(role);
		}

		@Override
		public String toString() {
			return super.toString();
		}
}
