import java.io.File;

import org.neo4j.driver.v1.*;

public class Connessione {

	public Session connettiti(String host, String[] credenziali ){
		
		Driver driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic("neo4j", "PorcoDio95"), Config.build()
		        .withEncryptionLevel( Config.EncryptionLevel.REQUIRED )
		        .withTrustStrategy( Config.TrustStrategy.trustOnFirstUse( new File( "/path/to/neo4j_known_hosts" ) ) )
		        .toConfig() );
		
		Session session = driver.session();
		session.run( "USING PERIODIC COMMIT LOAD CSV WITH HEADERS FROM 'file:///Prova.csv' as line "
				+ "CREATE (:Persona {ID: line.Id, Nome: line.Nome, Cognome: line.Cognome})");
				
					

		

		session.close();
		driver.close();
		return session;
		
	}
	
}
