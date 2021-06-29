package org.example;
import java.sql.*;
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/Sales_detail","root","root");
        System.out.println("connection stablised");

//---------deleting all data inserted before in all table---------------------
        Statement statement=connection.createStatement();
        statement.executeUpdate("delete from invoice");
        statement.executeUpdate("delete from consumer");
        statement.executeUpdate("delete from sales_Rep");

//-----------Adding value to sales representative table--------------------------------------
        int[] id=new int[]{1001,1002,1003,1005,1006,1007};
        String[] name=new String[]{"Anthony G","Rejina R","Santhosh","Jaya Prasad","Diptish","Abbas"};
        String[] city=new String[]{"New Delhi","Bangalore","Mumbai","Channai","Kolkatta","Hydrabad"};
        double[] commision=new double[]{.25,.15,.12,.11,.12,.1};


        PreparedStatement pstm=connection.prepareStatement("Insert into sales_Rep values(?,?,?,?)");
        for(int i=0;i<6;i++){
            pstm.setInt(1,id[i]);
            pstm.setString(2,name[i]);
            pstm.setString(3,city[i]);
            pstm.setDouble(4,commision[i]);
            pstm.executeUpdate();
        }
//-----------print inserted value----------------------------------------------
        ResultSet rs=statement.executeQuery("select * from sales_Rep");
        while(rs.next()){
            int Id=rs.getInt("rep_Id");
            String Name=rs.getString("name");
            String City=rs.getString("city");
            double Commi=rs.getDouble("commision");
            System.out.println("ID: "+Id+" Name: "+Name+" City: "+City+" Commision: "+Commi );
        }
    }
}
