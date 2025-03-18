package util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.JavaSchool.dao.SeqNumDAO;

@Component // ✅ Registers this as a Spring bean
public class ReferenceNoGenerator {
    
    private final SeqNumDAO seqNumDAO;

    @Autowired
    public ReferenceNoGenerator(SeqNumDAO seqNumDAO) {
        this.seqNumDAO = seqNumDAO;
    }
    
	public String generateReferenceNo(String keyType) {
        // Get current date in YYYYMM format
        String datePart = new SimpleDateFormat("yyyyMM").format(new Date());

        // Increment sequence number and get the new value
        int seqNo = seqNumDAO.incrementSeqNo(keyType);

        // Format sequence number as 6-digit (padded with zeros if necessary)
        String seqPart = String.format("%06d", seqNo);

        // Combine to form 12-byte reference number
        return datePart + seqPart;
    }
}