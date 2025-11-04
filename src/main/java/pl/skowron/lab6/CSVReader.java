package pl.skowron.lab6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {

    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    String[] current;

    List<String> columnLabels = new ArrayList<>();
    Map<String, Integer> columnLabelsToInt = new HashMap<>();

    public CSVReader(String filename) throws IOException {
        this(filename, ",", true);
    }

    public CSVReader(String filename, String delimiter) throws IOException {
        this(filename, delimiter, true);
    }

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        this(new FileReader(filename), delimiter, hasHeader);
    }

    public CSVReader(String filename, String delimiter, boolean hasHeader, Charset charset) throws IOException {
        this(new InputStreamReader(new FileInputStream(filename), charset), delimiter, hasHeader);
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) parseHeader();
    }

    void parseHeader() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        String[] header = parseLine(line);
        for (int i = 0; i < header.length; i++) {
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i], i);
        }
    }

    private String[] parseLine(String line) {
        String regex = delimiter + "(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        String[] fields = line.split(regex, -1);

        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
            if (fields[i].startsWith("\"") && fields[i].endsWith("\"") && fields[i].length() > 1) {
                fields[i] = fields[i].substring(1, fields[i].length() - 1);
            }
        }
        return fields;
    }

    public boolean next() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return false;
        }
        current = parseLine(line);
        return true;
    }

    public List<String> getColumnLabels() {
        return columnLabels;
    }

    public int getRecordLength() {
        return current != null ? current.length : 0;
    }

    private int getColumnIndex(String columnLabel) {
        Integer index = columnLabelsToInt.get(columnLabel);
        if (index == null) {
            throw new IllegalArgumentException("Column '" + columnLabel + "' does not exist");
        }
        return index;
    }

    public boolean isMissing(int columnIndex) {
        return columnIndex < 0 || columnIndex >= getRecordLength() || current[columnIndex].isEmpty();
    }

    public boolean isMissing(String columnLabel) {
        return isMissing(getColumnIndex(columnLabel));
    }

    public String get(int columnIndex) {
        return isMissing(columnIndex) ? "" : current[columnIndex];
    }

    public String get(String columnLabel) {
        return get(getColumnIndex(columnLabel));
    }

    public int getInt(int columnIndex) {
        return Integer.parseInt(get(columnIndex));
    }

    public int getInt(String columnLabel) {
        return getInt(getColumnIndex(columnLabel));
    }

    public long getLong(int columnIndex) {
        return Long.parseLong(get(columnIndex));
    }

    public long getLong(String columnLabel) {
        return getLong(getColumnIndex(columnLabel));
    }

    public double getDouble(int columnIndex) {
        return Double.parseDouble(get(columnIndex));
    }

    public double getDouble(String columnLabel) {
        return getDouble(getColumnIndex(columnLabel));
    }

    public int getInt(int columnIndex, int defaultValue) {
        try {
            if (isMissing(columnIndex)) return defaultValue;
            return Integer.parseInt(current[columnIndex]);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public int getInt(String columnLabel, int defaultValue) {
        return getInt(getColumnIndex(columnLabel), defaultValue);
    }

    public long getLong(int columnIndex, long defaultValue) {
        try {
            if (isMissing(columnIndex)) return defaultValue;
            return Long.parseLong(current[columnIndex]);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public long getLong(String columnLabel, long defaultValue) {
        return getLong(getColumnIndex(columnLabel), defaultValue);
    }

    public double getDouble(int columnIndex, double defaultValue) {
        try {
            if (isMissing(columnIndex)) return defaultValue;
            return Double.parseDouble(current[columnIndex]);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public double getDouble(String columnLabel, double defaultValue) {
        return getDouble(getColumnIndex(columnLabel), defaultValue);
    }

    public LocalTime getTime(int columnIndex, String format) {
        return LocalTime.parse(get(columnIndex), DateTimeFormatter.ofPattern(format));
    }

    public LocalTime getTime(String columnLabel, String format) {
        return getTime(getColumnIndex(columnLabel), format);
    }

    public LocalDate getDate(int columnIndex, String format) {
        return LocalDate.parse(get(columnIndex), DateTimeFormatter.ofPattern(format));
    }

    public LocalDate getDate(String columnLabel, String format) {
        return getDate(getColumnIndex(columnLabel), format);
    }

    public LocalDateTime getDateTime(int columnIndex, String format) {
        return LocalDateTime.parse(get(columnIndex), DateTimeFormatter.ofPattern(format));
    }

    public LocalDateTime getDateTime(String columnLabel, String format) {
        return getDateTime(getColumnIndex(columnLabel), format);
    }
}