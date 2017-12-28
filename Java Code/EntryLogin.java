
import javax.swing.JOptionPane;

public class EntryLogin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String userInput = "Enter Your Choice:" + "\n" + "Enter 1 for Doctor Login" + "\n"
				+ "Enter 2 for Patient Login";
		String enteredValue;

		enteredValue = JOptionPane.showInputDialog(userInput);

		int choice = Integer.parseInt(enteredValue);

		if (choice == 1) {

			DoctorOptions dLogin = new DoctorOptions();
			dLogin.setVisible(true);
			dLogin.setSize(400, 400);

		}

		else if (choice == 2) {

			PatientOptions pLogin = new PatientOptions();
			pLogin.setVisible(true);
			pLogin.setSize(500, 500);

		}

		else {
			JOptionPane.showMessageDialog(null, "Please enter correct choice", "Login Error", JOptionPane.PLAIN_MESSAGE);
		}

	}

}
