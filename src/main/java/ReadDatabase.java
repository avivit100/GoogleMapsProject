import com.aventstack.extentreports.Status;

import java.sql.*;

public class ReadDatabase {
    private static String strWebSite;
    private static String strCriteria;

    public static void GetDetailsFromDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException ex){}

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306", "sql12246863", "YTbV1drZU1");

            Statement stmt = con.createStatement();
            String statementToExecute = "SELECT * FROM data.SiteDetails;";
            ResultSet rs = stmt.executeQuery(statementToExecute);

            while (rs.next()) {
                //Retrieve by column name
                strCriteria = rs.getString("criteria");
                strWebSite = rs.getString("web_site");
            }
            rs.close();
            stmt.close();
            con.close();
        }
        catch(SQLException ex){
             LogFile.write(Status.FAIL, "FAIL in reading from the Database: " +ex.getMessage());
        }


//        strCriteria = "hotels+in+TelAviv";
//        strWebSite = "https://www.google.com/maps/";
        LogFile.write(Status.PASS, "The Test Details are read from the Database. Criteria: "+strCriteria+". Site:"+strWebSite);

    }

    protected static String GetWebSite(){
        return strWebSite;
    }

    protected static String GetCriteria(){ return strCriteria; }
}
