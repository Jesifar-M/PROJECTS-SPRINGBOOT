package com.example.BookPdfApp.controller;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RestController
public class BookPdfController {

    @GetMapping("/generate-book-pdf")
    public ResponseEntity<byte[]> generateBookPdf() {

        // Default book details

        String title = "The Secret Garden";

        String author = "Frances Hodgson Burnett";

        String description =
                "A classic novel about a young girl " +
                "who discovers a secret garden and " +
                "brings it back to life.";

        double price = 499.00;

        String publishedDate = "01-01-1911";


        // Create PDF

        ByteArrayOutputStream outputStream =
                new ByteArrayOutputStream();

        Document document = new Document();

        try {

            PdfWriter.getInstance(
                    document,
                    outputStream
            );

            document.open();

            document.add(
                    new Paragraph("BOOK DETAILS")
            );

            document.add(
                    new Paragraph(" ")
            );

            document.add(
                    new Paragraph(
                            "Title: " + title
                    )
            );

            document.add(
                    new Paragraph(
                            "Author: " + author
                    )
            );

            document.add(
                    new Paragraph(
                            "Description: " + description
                    )
            );

            document.add(
                    new Paragraph(
                            "Price: ₹" + price
                    )
            );

            document.add(
                    new Paragraph(
                            "Published Date: " +
                            publishedDate
                    )
            );

            document.close();

        } catch (Exception e) {

            return ResponseEntity
                    .internalServerError()
                    .build();
        }


        return ResponseEntity.ok()

                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=book-details.pdf"
                )

                .contentType(
                        MediaType.APPLICATION_PDF
                )

                .body(
                        outputStream.toByteArray()
                );
    }
}