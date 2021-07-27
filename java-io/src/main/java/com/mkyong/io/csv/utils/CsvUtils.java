package com.mkyong.io.csv.utils;


import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.opencsv.ICSVWriter.NO_QUOTE_CHARACTER;

public class CsvUtils {

    private CsvUtils() {
        // op verzoek van Sonar:
        // "Utility classes should not have a public constructor.
        // Add a private constructor to hide the implicit public one."
    }

    public static List<String[]> leesCsvBestand(String fileName, char separator) throws IOException, CsvException {

        List<String[]> csvBestand;

        // custom separator
        CSVParser csvParser = new CSVParserBuilder().withSeparator(separator).build();
        try (CSVReader reader = new CSVReaderBuilder(
                new FileReader(fileName))
                .withCSVParser(csvParser)   // custom CSV parser
//                .withSkipLines(1)           // skip the first line, header info
                .build()) {
            csvBestand = reader.readAll();
        }

        return csvBestand;
    }

    public static List<String[]> leesCsvEnVoegTrackAndTraceIdToe(String fileName, char separator) throws IOException, CsvException {

        List<String[]> csvBestand;

        // custom separator
        CSVParser csvParser = new CSVParserBuilder().withSeparator(separator).build();
        try (CSVReader reader = new CSVReaderBuilder(
                new FileReader(fileName))
                .withCSVParser(csvParser)   // custom CSV parser
//                .withSkipLines(1)           // skip the first line, header info
                .build()) {
            csvBestand = reader.readAll();
        }

        List<String[]> csvBestandMetTrackAndTraceId = new ArrayList<>();

        for (String[] csvRegel : csvBestand) {

            // Genereer een TrackAndTraceId
            String trackAndTraceId = UUID.randomUUID().toString();

            // converteer de array naar een arrayList
            List<String> csvRegelMetTrackAndTraceId
                    = new ArrayList<>(
                    Arrays.asList(csvRegel));

            // Voeg TrackAndTraceId toe aan de arrayList
            csvRegelMetTrackAndTraceId.add(trackAndTraceId);

            // Converteer de Arraylist terug naar een array
            csvRegel = csvRegelMetTrackAndTraceId.toArray(csvRegel);

            // Voeg regel toe aan csv-bestand met TrackAndTraceId
            csvBestandMetTrackAndTraceId.add(csvRegel);
        }

        return csvBestandMetTrackAndTraceId;
    }


    public static void schrijfCsvBestand(String fileName, char separator, List<String[]> csvData) throws IOException {

        // custom separator wordt gebruikt
        try (ICSVWriter writer = new CSVWriterBuilder(
                new FileWriter(fileName))
                .withSeparator(separator)
                .withQuoteChar(NO_QUOTE_CHARACTER)
                .build()) {
            writer.writeAll(csvData);
        }

    }

}
