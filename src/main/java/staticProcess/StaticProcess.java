package staticProcess;//import ui.main.HomePage;
//import ui.main.Login_GUI;

import model.Employee;
import ui.main.HomePage;
import ui.main.LoginGUI;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Properties;

public class StaticProcess {
    public static DecimalFormat df = new DecimalFormat("#,##0.00 VND");
    public static NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
    public static DecimalFormat dff = new DecimalFormat("#,##0.00");
    public static boolean loginSuccess = false;
    public static LoginGUI login;
    public static String userlogin = "";
    public static Employee empLogin;
    public static HomePage homePage;
    public static Properties properties = new Properties();
}
