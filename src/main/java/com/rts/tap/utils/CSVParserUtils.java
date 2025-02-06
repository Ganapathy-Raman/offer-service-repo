//package com.rts.tap.utils;
//
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVRecord;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.rts.tap.model.Candidate;
//
//public class CSVParserUtils {
//
//    public static List<Candidate> parseCSV(MultipartFile csvFile) throws Exception {
//        List<Candidate> candidates = new ArrayList<>();
//
//        // Parse the CSV file
//        try (InputStreamReader reader = new InputStreamReader(csvFile.getInputStream())) {
//            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(reader);
//
//            for (CSVRecord record : records) {
//                Candidate candidate = new Candidate();
//                candidate.setFirstName(record.get("firstName"));
//                candidate.setLastName(record.get("lastName"));
//                candidate.setMobileNumber(record.get("mobileNumber"));
//                candidate.setEmail(record.get("email"));
//                candidate.setExperience(Integer.parseInt(record.get("experience")));
//                candidate.setResume(record.get("resume"));
//                candidate.setSource(record.get("source"));
//                candidate.setSourceId(Long.parseLong(record.get("sourceId")));
//                candidate.setSkill(record.get("skill"));
//                candidate.setLocation(record.get("location"));
//                candidate.setPanNumber(record.get("panNumber"));
//
//
//                candidates.add(candidate);
//            }
//        }
//
//        return candidates;
//    }
//}
