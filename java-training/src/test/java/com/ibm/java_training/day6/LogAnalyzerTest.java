package com.ibm.java_training.day6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogAnalyzerTest {
	
    @TempDir
    Path tempDir;
    Path summaryFile;
    
    /**
     * Creates summary file path before each test.
     */
    @BeforeEach
    void setup() {
        summaryFile = tempDir.resolve("summary.txt");
    }
    
    /**
     * Testing valid log entries with INFO, WARN, and ERROR levels.
     */
    @Test
    void exec001() throws Exception {
        Path logFile = tempDir.resolve("server.log");
        Files.writeString(logFile,
                "[2024-05-10 10:00:00] INFO: Server started\n" +
                "[2024-05-10 10:05:00] WARN: High memory usage\n" +
                "[2024-05-10 10:10:00] ERROR: Database connection failed\n" +
                "[2024-05-10 10:15:00] INFO: Request processed\n"
        );
        LogAnalyzer.main(new String[]{ logFile.toString(), summaryFile.toString() });
        String content = Files.readString(summaryFile);
        assertTrue(content.contains("Total Entries: 4"));
        assertTrue(content.contains("INFO: 2"));
        assertTrue(content.contains("WARN: 1"));
        assertTrue(content.contains("ERROR: 1"));
        assertTrue(content.contains("Database connection failed"));
    }
    
    /**
     * Testing log entries missing timestamp brackets.
     */
    @Test
    void exec002() throws Exception {
        Path logFile = tempDir.resolve("missing_brackets.log");
        Files.writeString(logFile,
                "2024-05-10 10:00:00 INFO: Missing brackets\n" +
                "[2024-05-10 10:05:00] INFO: Valid entry\n"
        );
        LogAnalyzer.main(new String[]{ logFile.toString(), summaryFile.toString() });
        String content = Files.readString(summaryFile);
        assertTrue(content.contains("Total Entries: 1"));
        assertTrue(content.contains("INFO: 1"));
    }
    
    /**
     * Testing log entries missing message content.
     */
    @Test
    void exec003() throws Exception {
        Path logFile = tempDir.resolve("missing_message.log");
        Files.writeString(logFile,
                "[2024-05-10 10:00:00] INFO\n" +
                "[2024-05-10 10:05:00] ERROR: Disk failure\n"
        );
        LogAnalyzer.main(new String[]{ logFile.toString(),  summaryFile.toString() });
        String content = Files.readString(summaryFile);
        assertTrue(content.contains("Total Entries: 1"));
        assertTrue(content.contains("INFO: 0"));
        assertTrue(content.contains("ERROR: 1"));
    }
    
    /**
     * Testing invalid log levels such as DEBUG and TRACE.
     */
    @Test
    void exec004() throws Exception {
        Path logFile = tempDir.resolve("invalid_level.log");
        Files.writeString(logFile,
                "[2024-05-10 10:00:00] DEBUG: Debugging info\n" +
                "[2024-05-10 10:05:00] TRACE: Trace information\n" +
                "[2024-05-10 10:10:00] ERROR: Actual error\n"
        );
        LogAnalyzer.main(new String[]{ logFile.toString(), summaryFile.toString() });
        String content = Files.readString(summaryFile);
        assertTrue(content.contains("Total Entries: 1"));
        assertTrue(content.contains("ERROR: 1"));
    }
    
    /**
     * Testing malformed timestamps with invalid date formats.
     */
    @Test
    void exec005() throws Exception {
        Path logFile = tempDir.resolve("bad_timestamp.log");
        Files.writeString(logFile,
                "[10-05-2024 10:00:00] INFO: Wrong timestamp\n"
        );
        try {
        	LogAnalyzer.main(new String[]{ logFile.toString(), summaryFile.toString() });
        } catch (Exception ignored) {}
    }
    
    /**
     * Testing empty log files with zero entries.
     */
    @Test
    void exec006() throws Exception {
        Path logFile = tempDir.resolve("empty.log");
        Files.writeString(logFile, "");
        LogAnalyzer.main(new String[]{ logFile.toString(), summaryFile.toString() });
        String content = Files.readString(summaryFile);
        assertTrue(content.contains("Total Entries: 0"));
        assertTrue(content.contains("INFO: 0"));
        assertTrue(content.contains("WARN: 0"));
        assertTrue(content.contains("ERROR: 0"));
    }
}