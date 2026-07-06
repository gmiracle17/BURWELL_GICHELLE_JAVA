package com.ibm.java_training.day6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LogAnalyzerTest {

    private static final String FILE_DIR = "src/main/resources/com/ibm/java_training/day6/";
    Path summaryFile;

    @BeforeEach
    void setup() throws Exception {
        summaryFile = Path.of(FILE_DIR.concat("summary.txt"));
        Files.deleteIfExists(summaryFile);
    }

    /**
     * Testing valid log entries with INFO, WARN, and ERROR levels.
     */
    @Test
    void exec001() throws Exception {
        Path logPath = Path.of(FILE_DIR.concat("exec001/server.log"));

        LogAnalyzer.main(new String[]{ logPath.toString(), summaryFile.toString() });

        String content = Files.readString(summaryFile);
        assertTrue(content.contains("Total Entries: 87"));
        assertTrue(content.contains("INFO: 68"));
        assertTrue(content.contains("WARN: 10"));
        assertTrue(content.contains("ERROR: 9"));
        assertTrue(content.contains("Failed to connect to external API"));
        assertTrue(content.contains("NullPointerException in UserService.java line 87"));
        assertTrue(content.contains("Timeout while reading data from database"));
        assertTrue(content.contains("FileNotFoundException in ConfigLoader.java line 45"));
        assertTrue(content.contains("Unauthorized access attempt detected"));
        assertTrue(content.contains("Assertion failed in TestCase #45"));
        assertTrue(content.contains("Unable to parse JSON response from API"));
        assertTrue(content.contains("SMTP server not responding"));
        assertTrue(content.contains("Audit log write failure"));
        assertTrue(content.contains("Earliest Timestamp: 2024-05-10T09:00"));
        assertTrue(content.contains("Latest Timestamp: 2024-05-10T09:04:18"));
    }

    /**
     * Testing log entries missing timestamp brackets.
     */
    @Test
    void exec002() throws Exception {
        Path logPath = Path.of(FILE_DIR.concat("exec002/server.log"));

        LogAnalyzer.main(new String[]{ logPath.toString(), summaryFile.toString() });

        String content = Files.readString(summaryFile);
        assertTrue(content.contains("Total Entries: 2"));
        assertTrue(content.contains("INFO: 1"));
        assertTrue(content.contains("WARN: 1"));
        assertTrue(content.contains("ERROR: 0"));
    }

    /**
     * Testing log entries missing message content.
     */
    @Test
    void exec003() throws Exception {
        Path logPath = Path.of(FILE_DIR.concat("exec003/server.log"));

        LogAnalyzer.main(new String[]{ logPath.toString(), summaryFile.toString() });

        String content = Files.readString(summaryFile);
        assertTrue(content.contains("Total Entries: 2"));
        assertTrue(content.contains("INFO: 1"));
        assertTrue(content.contains("WARN: 1"));
        assertTrue(content.contains("ERROR: 0"));
    }

    /**
     * Testing invalid log levels such as DEBUG and TRACE.
     */
    @Test
    void exec004() throws Exception {
        Path logPath = Path.of(FILE_DIR.concat("exec004/server.log"));

        LogAnalyzer.main(new String[]{ logPath.toString(), summaryFile.toString() });

        String content = Files.readString(summaryFile);
        assertTrue(content.contains("Total Entries: 2"));
        assertTrue(content.contains("INFO: 0"));
        assertTrue(content.contains("WARN: 1"));
        assertTrue(content.contains("ERROR: 1"));
        assertTrue(content.contains("Failed to connect to external API"));
    }

    /**
     * Testing malformed timestamps with invalid date formats.
     */
    @Test
    void exec005() {
        Path logPath = Path.of(FILE_DIR.concat("exec005/server.log"));

        org.junit.jupiter.api.Assertions.assertThrows(java.time.format.DateTimeParseException.class,
                () -> LogAnalyzer.main(new String[]{ logPath.toString(), summaryFile.toString() })
        );
    }

    /**
     * Testing empty log files with zero entries.
     */
    @Test
    void exec006() throws Exception {
        Path logPath = Path.of(FILE_DIR.concat("exec006/server.log"));

        LogAnalyzer.main(new String[]{ logPath.toString(), summaryFile.toString() });

        String content = Files.readString(summaryFile);
        assertTrue(content.contains("Total Entries: 0"));
        assertTrue(content.contains("INFO: 0"));
        assertTrue(content.contains("WARN: 0"));
        assertTrue(content.contains("ERROR: 0"));
        assertTrue(content.contains("Earliest Timestamp: null"));
        assertTrue(content.contains("Latest Timestamp: null"));
    }

    /**
     * Testing when log file does not exist.
     */
    @Test
    void exec007() {
        Path logPath = Path.of(FILE_DIR.concat("exec007/server.log"));

        LogAnalyzer.main(new String[]{ logPath.toString(), summaryFile.toString() });

        assertFalse(Files.exists(summaryFile));
    }
}