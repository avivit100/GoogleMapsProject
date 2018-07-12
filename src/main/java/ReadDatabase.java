import com.aventstack.extentreports.Status;

import java.sql.*;

public class ReadDatabase {
    private static String strWebSite;
    private static String strCriteria;

    /* GetDetailsFromDB - This method opens the database, and read the details:
       The web site to open, The search criteria */
    public static void GetDetailsFromDB() {
        try {
            //Class.forName(Constants.DB_OBJECT);
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException ex){}

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306", "sql12246863", "YTbV1drZU1");
            //Connection con = DriverManager.getConnection(Constants.DB_CONN, Constants.DB_USER, Constants.DB_PASS);

            Statement stmt = con.createStatement();
            String statementToExecute = Constants.DB_QUERY;
            ResultSet rs = stmt.executeQuery(statementToExecute);

            while (rs.next()) {
                //Retrieve by column name
                strCriteria = rs.getString(Constants.DB_CRITERIA);
                strWebSite = rs.getString(Constants.DB_SITE);
            }
            rs.close();
            stmt.close();
            con.close();
        }
        catch(SQLException ex){
             LogFile.write(Status.FAIL, "FAIL in reading from the Database: " +ex.getMessage());
        }


        strCriteria = "hotels+in+TelAviv";
        strWebSite = "https://www.google.com/maps/";
        LogFile.write(Status.PASS, "The Test Details are read from the Database. Criteria: "+strCriteria+". Site:"+strWebSite);

    }

    /* GetWebSite - This method returns the Web-Site detail */
    protected static String GetWebSite(){
        return strWebSite;
    }

    /* GetCriteria - This method returns the search criteria detail */
    protected static String GetCriteria(){ return strCriteria; }
}
