package com.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CSVSearchController {

    @Autowired
    private CSVSearchService csvSearchService;

    @GetMapping("/search")
    public PageResponse searchCSV(
            @RequestParam String attribute,
            @RequestParam String data,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return csvSearchService.searchCSV(attribute, data, page, size);
    }
}
