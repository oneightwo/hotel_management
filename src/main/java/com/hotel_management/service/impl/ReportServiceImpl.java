package com.hotel_management.service.impl;

import com.hotel_management.dto.BookingDto;
import com.hotel_management.dto.RoomDto;
import com.hotel_management.model.History;
import com.hotel_management.repository.HistoryRepository;
import com.hotel_management.service.ReportService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final HistoryRepository historyRepository;

    private static Font FONT_HEADER;

    private static Font FONT_NORMAL;

    static {
        try {
            BaseFont baseFont = BaseFont.createFont("asset/times-roman.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            FONT_HEADER = new Font(baseFont, 14, Font.BOLD, BaseColor.BLACK);
            FONT_NORMAL = new Font(baseFont, 10, Font.NORMAL, BaseColor.BLACK);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public ByteArrayInputStream getReport() {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = null;

        try {
            writer = PdfWriter.getInstance(document, out);
            document.open();
            document.setPageSize(PageSize.A4.rotate());
            document.newPage();

            Paragraph title = new Paragraph("Отчет по бронированию", FONT_HEADER);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Общая выручка: " + getTotalRevenue(), FONT_HEADER));
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);
            Stream.of("Название", "Бронь с", "Бронь до", "Цена")
                    .forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(headerTitle, FONT_NORMAL));
                        table.addCell(header);
                    });

            for (History history : historyRepository.findAll()) {
                table.addCell(new Phrase(history.getBooking().getRoom().getName(), FONT_NORMAL));
                table.addCell(new Phrase(history.getBooking().getFromDate().toString(), FONT_NORMAL));
                table.addCell(new Phrase(history.getBooking().getEndDate().toString(), FONT_NORMAL));
                table.addCell(new Phrase(history.getBooking().getTotalPrice().toString(), FONT_NORMAL));
            }

            document.add(table);

            JFreeChart chart = createChart(historyRepository.findAll());
            BufferedImage bufferedImage = chart.createBufferedImage(500, 500);
            Image image = Image.getInstance(writer, bufferedImage, 1.0f);
            document.add(image);
            document.add(Chunk.NEWLINE);
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    private JFreeChart createChart(List<History> histories) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        histories.forEach(history -> {
            RoomDto room = history.getBooking().getRoom();
            String category = room.getName() + ": Этаж(" + room.getFloor() + ") / Номер(" + room.getNumber() + ")";
            int count = 0;
            try {
                count = dataset.getValue(category).intValue();
            } catch (Exception ignored) {
            }
            dataset.setValue(category, count + 1);
        });

        return ChartFactory.createPieChart("Востребованность номера", dataset, true, true, false);
    }

    private String getTotalRevenue() {
        return historyRepository.findAll().stream()
                .map(History::getBooking)
                .map(BookingDto::getTotalPrice)
                .reduce(BigDecimal::add)
                .map(BigDecimal::toString)
                .orElse("0");
    }
}
