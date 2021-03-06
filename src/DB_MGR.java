import java.sql.*;

public class DB_MGR {


    public static void DB_UPDATEIP_STATUS(String IP, Double Ver, Boolean status){
        try{
            //Create mysql connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String URL = "jdbc:mysql://undeadinc.ca/u433204257_IPMGR";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(URL, Settings.DB_USR, Settings.DB_PWS);

            String query = "update Nodes " + "SET ISALIVE = ? "+ "WHERE IP = ?";//Create a Query for the DB

            // create the java statement
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setBoolean(1,status);
            preparedStatement.setString(2, IP);

            preparedStatement.execute();

            conn.close();


            return;

        }catch (Exception ex){
            System.out.println("EX: "+ ex);
        }
    }

    public static Double DB_GETIP_VER(String IP){
        try{
            Double Ver = 0.0;
            //Create mysql connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String URL = "jdbc:mysql://undeadinc.ca/u433204257_IPMGR";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(URL, Settings.DB_USR, Settings.DB_PWS);

            String query = "SELECT * FROM Nodes";//Create a Query for the DB

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                String IP1 = rs.getString("IP");
                Double Ver1 = rs.getDouble("Ver");
                if(IP1.matches(IP)){
                    Ver += Ver1;
                }
            }
            st.close();
            conn.close();


            return Ver;

        }catch (Exception ex){
            System.out.println("EX: "+ ex);
        }
        return 0.0;
    }


    public static void DB_SETIP(String IP, Double Ver){
        try{
            //Create mysql connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String URL = "jdbc:mysql://undeadinc.ca/u433204257_IPMGR";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(URL, Settings.DB_USR, Settings.DB_PWS);

            String query = "insert into Nodes (IP, Ver, ISALIVE)" + "values (?,?,?)";//Create a Query for the DB

            // create the java statement
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, IP);
            preparedStatement.setDouble(2, Ver);
            preparedStatement.setBoolean(3, false);

            preparedStatement.execute();

            conn.close();
            conn.isClosed();


            return;

        }catch (Exception ex){
            System.out.println("EX: "+ ex);
        }
    }

    public static void DB_GETIP(){
        try{
//            Net.IPs.clear();
//            Net.TEMP_IPs.clear();
            //Create mysql connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String URL = "jdbc:mysql://undeadinc.ca/u433204257_IPMGR";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(URL, Settings.DB_USR, Settings.DB_PWS);

            String query = "SELECT * FROM Nodes";//Create a Query for the DB

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                String IP = rs.getString("IP");
                Double Ver = rs.getDouble("Ver");
                Net.IPs.add(IP);
                // print the results
                //System.out.format("%s, %s, \n", IP, Ver);
            }
            st.close();
            conn.close();


            return;

        }catch (Exception ex){
            System.out.println("EX: "+ ex);
        }
    }

    public static Boolean DB_GETIP_STATUS(String ip) {
        Boolean isalive = false;
        try {
            //Create mysql connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String URL = "jdbc:mysql://undeadinc.ca/u433204257_IPMGR";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(URL, Settings.DB_USR, Settings.DB_PWS);

            String query = "SELECT * FROM Nodes";//Create a Query for the DB

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                String IP = rs.getString("IP");
                if(IP.matches(ip)){
                    Double Ver = rs.getDouble("Ver");
                    Boolean ISALIVE = rs.getBoolean("ISALIVE");
                    isalive = ISALIVE;
                }

            }
            st.close();
            conn.close();




        } catch (Exception ex) {
            System.out.println("EX: " + ex);
        }
        return isalive;
    }
}
