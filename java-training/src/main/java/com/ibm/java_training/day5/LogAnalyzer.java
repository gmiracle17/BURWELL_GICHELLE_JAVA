package com.ibm.java_training.day5;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class MalformedLogEntryException extends Exception {
    public MalformedLogEntryException(String message) {
        super(message);
    }
}

// Record for storing log data
record LogEntry(LocalDateTime timestamp, String level, String message) {}

public class LogAnalyzer {
    
	public static void validateLine(String line) throws MalformedLogEntryException {
		// Empty line
        if (line == null || line.trim().isEmpty()) { throw new MalformedLogEntryException("Empty line"); }
        
        // Must start with [
        if (!line.startsWith("[")) { throw new MalformedLogEntryException("Line does not start with '['"); }
        
        // Must contain ]
        if (!line.contains("]")) { throw new MalformedLogEntryException("Missing closing ']'"); }
        
        int closeBracketIndex = line.indexOf("]");
        String remaining = line.substring(closeBracketIndex + 1).trim();
        
        // Must contain :
        if (!remaining.contains(":")) { throw new MalformedLogEntryException("Missing ':' after log level"); }
    }
	
	public static void main(String[] args) {
        
		String path = "src/main/resources/com/ibm/java_training/day6/";
        
		// Count log levels
        Map<String, Integer> logCounts = new HashMap<>();
        
        // Store error messages
        List<String> errorMessages = new ArrayList<>();
        
        // Store parsed log entries
        List<LogEntry> logEntries = new ArrayList<>();
        LocalDateTime earliest = null;
        LocalDateTime latest = null;
        
        int totalEntries = 0;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        try (
        		BufferedReader br = new BufferedReader(new FileReader(path + "server.log"));
                BufferedWriter bw = new BufferedWriter(new FileWriter(path + "summary.txt"));
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    validateLine(line);
                    
                    int closeBracketIndex = line.indexOf("]");
                    String timestampText = line.substring(1, closeBracketIndex);
                    
                    LocalDateTime timestamp = LocalDateTime.parse(timestampText, formatter);
                    String remaining = line.substring(closeBracketIndex + 1).trim();
                    
                    int colonIndex = remaining.indexOf(":");
                    String level = remaining.substring(0, colonIndex).trim();
                    String message = remaining.substring(colonIndex + 1).trim();
                    
                    // Create record object
                    LogEntry entry = new LogEntry(timestamp, level, message);
                    logEntries.add(entry);
                    
                    // Count log levels
                    logCounts.put(entry.level(), logCounts.getOrDefault(entry.level(), 0) + 1);
                    
                    // Store error messages
                    if (entry.level().equalsIgnoreCase("ERROR")) { errorMessages.add(entry.message()); }
                    
                    // Find earliest timestamp
                    if (earliest == null || entry.timestamp().isBefore(earliest)) { earliest = entry.timestamp(); }
                    
                    // Find latest timestamp
                    if (latest == null || entry.timestamp().isAfter(latest)) { latest = entry.timestamp(); }
                    totalEntries++;
                    
                } catch (MalformedLogEntryException e) {}
            }
            
            bw.write("Log Summary Report:\n");
            bw.write("Total Entries: " + totalEntries + "\n");
            bw.write("INFO: " + logCounts.getOrDefault("INFO", 0) + "\n");
            bw.write("WARN: " + logCounts.getOrDefault("WARN", 0) + "\n");
            bw.write("ERROR: " + logCounts.getOrDefault("ERROR", 0) + "\n");
            bw.write("\nError Messages:\n");
            
            for (String msg : errorMessages) { bw.write("- " + msg + "\n"); }
            bw.write("\nEarliest Timestamp: " + earliest.format(formatter) + "\n");
            bw.write("Latest Timestamp: " + latest.format(formatter) + "\n");
            
            System.out.println("Summary report is successfully saved.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }
    }
}