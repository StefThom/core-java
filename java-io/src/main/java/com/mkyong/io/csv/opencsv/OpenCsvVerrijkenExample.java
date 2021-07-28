package com.mkyong.io.csv.opencsv;

import com.mkyong.io.csv.utils.CsvUtils;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class OpenCsvVerrijkenExample {

    private static final char PIPE_SEPARATOR = '|';

    public static void main(String[] args) throws IOException, CsvException, URISyntaxException {

        String fileName = "c:\\test\\csv\\A9TSTF1.csv";
        String fileNameVerrijkt = "c:\\test\\csv-verrijkt\\A9TSTF1.csv";

        List<String[]> resVerrijkt = CsvUtils.leesCsvEnVoegTrackAndTraceIdToe(fileName, PIPE_SEPARATOR, false);
        System.out.println("\nOverzicht verrijkte array\n");
        resVerrijkt.forEach(x -> System.out.println(Arrays.toString(x)));
        CsvUtils.schrijfCsvBestand(fileNameVerrijkt, PIPE_SEPARATOR, resVerrijkt);

        fileName = "c:\\test\\csv\\monitor.csv";
        fileNameVerrijkt = "c:\\test\\csv-verrijkt\\monitor.csv";

        resVerrijkt = CsvUtils.leesCsvEnVoegTrackAndTraceIdToe(fileName, PIPE_SEPARATOR, true);
        System.out.println("\nOverzicht verrijkte array\n");
        resVerrijkt.forEach(x -> System.out.println(Arrays.toString(x)));
        CsvUtils.schrijfCsvBestand(fileNameVerrijkt, PIPE_SEPARATOR, resVerrijkt);


    }
}
