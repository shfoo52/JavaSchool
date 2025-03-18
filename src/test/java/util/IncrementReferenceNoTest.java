package util;

import com.zaxxer.hikari.HikariDataSource;
import com.JavaSchool.dao.SeqNumDAO;
import com.JavaSchool.model.SeqNum;

//import util.ReferenceNoGenerator;

public class IncrementReferenceNoTest {
    public static void main(String[] args) {
        HikariDataSource dataSource = null;
        try {
            // Configure PostgreSQL connection
            dataSource = new HikariDataSource();
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/JavaSchoolProject");
            dataSource.setUsername("postgres");  // Change to your credentials
            dataSource.setPassword("123456");
            
            SeqNumDAO seqNumDAO = new SeqNumDAO(dataSource);
            ReferenceNoGenerator referenceNoGenerator = new ReferenceNoGenerator(seqNumDAO);
            // Insert a sample seqNum record for "RRNO" (if not exists)
            if (seqNumDAO.getSeqNum("TEST") == null) {
            	seqNumDAO.insertSeqNum(new SeqNum("TEST", 0, 6));
            }

            // Generate and print reference number	
            String referenceNo = referenceNoGenerator.generateReferenceNo("TEST");
            System.out.println("Generated Reference No: " + referenceNo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	dataSource.close();
        }
    }
}
