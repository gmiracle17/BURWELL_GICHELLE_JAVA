package com.ibm.java_training.day6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for LogAnalyzer class that reads a server log file,
 * validates log entries, counts log levels,
 * extracts error messages, and generates a summary report.
 * Handles malformed entries and tracks earliest and latest timestamps.
 */
public class LogAnalyzerTest {
    
	private static final String FILE_DIR = "src/main/resources/com/ibm/java_training/day6/";
    private static final String TEST_DIR = "src/test/resources/";
    
    Path summaryFile;
    
    /**
     * Removes old summary file before each test.
     */
    @BeforeEach
    void setup() throws Exception {
        summaryFile = Path.of(FILE_DIR.concat("summary.txt"));
        Files.deleteIfExists(summaryFile);
    }
    
    /**
     * should_ReturnEqual_IfSummaryOutputIsEquivalent
     * Tests valid log entries with INFO, WARN, and ERROR levels.
     */
    @Test
    void exec001() throws Exception {
        String expectedFile = Files.readString(Path.of(TEST_DIR.concat("exec001/summary.txt")));
        Path logPath = Path.of(TEST_DIR.concat("exec001/server.log")); 
        
        LogAnalyzer.main(new String[]{ logPath.toString(), summaryFile.toString() });
        
        String actualFile = Files.readString(summaryFile);
        assertEquals(expectedFile, actualFile);
    }
    
    /**
     * should_SkipMalformedEntries_IfTimestampBracketsAreMissing
     * Tests log entries missing timestamp brackets.
     */
    @Test
    void exec002() throws Exception {
        String expectedFile = Files.readString(Path.of(TEST_DIR.concat("exec002/summary.txt")));
        Path logPath = Path.of(TEST_DIR.concat("exec002/server.log"));
        
        LogAnalyzer.main(new String[]{ logPath.toString(), summaryFile.toString() });
        
        String actualFile = Files.readString(summaryFile);
        assertEquals(expectedFile, actualFile);
    }
    
    /**
     * should_SkipMalformedEntries_IfMessageContentIsMissing
     * Tests log entries missing message content.
     */
    @Test
    void exec003() throws Exception {
        String expectedFile = Files.readString(Path.of(TEST_DIR.concat("exec003/summary.txt")));
        Path logPath = Path.of(TEST_DIR.concat("exec003/server.log"));

        LogAnalyzer.main(new String[]{ logPath.toString(), summaryFile.toString() });
        
        String actualFile = Files.readString(summaryFile);

	    String normalizedExpected = expectedFile.replace("\r\n", "\n").strip();
	    String normalizedActual = actualFile.replace("\r\n", "\n").strip();
	
	    assertEquals(normalizedExpected, normalizedActual);
    }
    
    /**
     * should_SkipEntries_IfLogLevelIsInvalid
     * Tests invalid log levels such as DEBUG and TRACE.
     */
    @Test
    void exec004() throws Exception {
        String expectedFile = Files.readString(Path.of(TEST_DIR.concat("exec004/summary.txt")));
        Path logPath = Path.of(TEST_DIR.concat("exec004/server.log"));

        LogAnalyzer.main(new String[]{ logPath.toString(), summaryFile.toString() });
        
        String actualFile = Files.readString(summaryFile);

	    String normalizedExpected = expectedFile.replace("\r\n", "\n").strip();
	    String normalizedActual = actualFile.replace("\r\n", "\n").strip();
	
	    assertEquals(normalizedExpected, normalizedActual);
    }
    
    /**
     * should_ThrowDateTimeParseException_IfTimestampFormatIsInvalid
     * Tests malformed timestamps with invalid date formats.
     */
    @Test
    void exec005() {
        Path logPath = Path.of(TEST_DIR.concat("exec005/server.log"));
        assertThrows(java.time.format.DateTimeParseException.class,
                () -> LogAnalyzer.main(new String[]{ logPath.toString(), summaryFile.toString() })
        );
    }
    
    /**
     * should_ReturnEmptySummary_IfLogFileIsEmpty
     * Tests empty log files with zero entries.
     */
    @Test
    void exec006() throws Exception {
        String expectedFile = Files.readString(Path.of(TEST_DIR.concat("exec006/summary.txt")));
        Path logPath = Path.of(TEST_DIR.concat("exec006/server.log"));

        LogAnalyzer.main(new String[]{ logPath.toString(), summaryFile.toString() });
        
        String actualFile = Files.readString(summaryFile);

	    String normalizedExpected = expectedFile.replace("\r\n", "\n").strip();
	    String normalizedActual = actualFile.replace("\r\n", "\n").strip();
	
	    assertEquals(normalizedExpected, normalizedActual);
    }
    
    /**
     * should_NotCreateSummaryFile_IfLogFileDoesNotExist
     * Tests behavior when input log file does not exist.
     */
    @Test
    void exec007() {
        Path logPath = Path.of(TEST_DIR.concat("exec007/server.log"));
        
        LogAnalyzer.main(new String[]{ logPath.toString(), summaryFile.toString() });
        
        assertFalse(Files.exists(summaryFile));
    }
}