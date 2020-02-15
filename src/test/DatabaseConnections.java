package test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import org.junit.jupiter.api.Test;
import breakingPointTool.connection.DatabaseConnection;
import breakingPointTool.connection.SonarDatabaseConnection;

class DatabaseConnections 
{
	/*
	 * Test for classes in package connection
	 */
	  @Test
	  public void testMetricsDBConnection() throws Exception 
	  {
		  // Return connection not null
		 Connection conn = DatabaseConnection.getConnection();
	     assertNotNull(conn);

	  }
	  
	  @Test
	  public void testSonarDBConnection() throws Exception 
	  {
		  // Return connection not null
		 Connection conn = SonarDatabaseConnection.getConnection();
	     assertNotNull(conn);

	  }

}
