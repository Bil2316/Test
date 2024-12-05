package com.example;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVSearchService {

    private static final String FILE_PATH = "chuyen_khoan.csv";

    public PageResponse searchCSV(String attribute, String data, int page, int size) {
        List<String> results = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String headerLine = br.readLine();
            if (headerLine == null) {
                return new PageResponse(results, 0, 0);
            }
            String[] headers = headerLine.split(",");
            int columnIndex = -1;
            for (int i = 0; i < headers.length; i++) {
 if (headers[i].replace("\"", "").equalsIgnoreCase(attribute)) {
                    columnIndex = i;
                    break;
                }
            }

            if (columnIndex == -1) {
                return new PageResponse(results, 0, 0);
            }
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (columnIndex == 1 || columnIndex == 2) {
                    if (columnIndex < values.length) {
                        try {
                            int creditValue = Integer.parseInt(values[columnIndex].replace("\"", ""));
                            int dataValue = Integer.parseInt(data);
                            if (creditValue == dataValue) {
                                results.add(line);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                } else {
                    if (columnIndex < values.length && values[columnIndex].toLowerCase().contains(data.toLowerCase())) {
                        results.add(line);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        int totalRecords = results.size();
        int totalPages = (int) Math.ceil((double) totalRecords / size);
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, results.size());

        if (fromIndex >= results.size()) {
            return new PageResponse(new ArrayList<>(), totalPages, totalRecords);
        }
        return new PageResponse(results.subList(fromIndex, toIndex), totalPages, totalRecords);
    }
}
