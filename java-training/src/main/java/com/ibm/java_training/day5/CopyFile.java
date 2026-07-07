package com.ibm.java_training.day5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

record Student(String id, String name, String course) {}

public class CopyFile {
	
    public static void main(String[] args) {
    	
        String path = "src/main/resources/com/ibm/java_training/day5/";
        List<Student> students = new ArrayList<>();
        
        try (
            BufferedReader br = new BufferedReader(new FileReader(path + "student.csv"));
            BufferedWriter bw = new BufferedWriter(new FileWriter(path + "student.json"));
        ) {
            String line;
            
            // First row is column names
            String headerLine = br.readLine();
            if (headerLine == null || headerLine.trim().isEmpty()) {
                System.out.println("CSV file is empty");
                bw.write("CSV file was empty");
                return;
            }
            
            String[] headers = headerLine.split(",");
            while ((line = br.readLine()) != null) {
                // skip empty rows
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] data = line.split(",");
                // skip invalid rows
                if (data.length != 3) {
                	System.out.println("There is an invalid row: " + line);
                    continue;
                }
                // Create student record
                Student student = new Student(
                    data[0].trim(),
                    data[1].trim(),
                    data[2].trim()
                );
                students.add(student);
            }
            
            if (students.isEmpty()) {
                System.out.println("No valid student records found");
                bw.write("No valid student records found");
                return;
            }

            bw.write("[");
            bw.newLine();
            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);
                bw.write("  {");
                bw.newLine();
                bw.write("    \"" + headers[0].trim() + "\":\"" + s.id() + "\",");
                bw.newLine();
                bw.write("    \"" + headers[1].trim() + "\":" + s.name() + ",");
                bw.newLine();
                bw.write("    \"" + headers[2].trim() + "\":" + s.course());
                bw.newLine();
                bw.write("  }");
                if (i != students.size() - 1) { bw.write(","); }
                bw.newLine();
            }
            bw.write("]");
            System.out.println("CSV converted to JSON successfully");
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}