package com.example.urban_crew_extended;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.Manifest.permission;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PaymentSelection extends AppCompatActivity {

    Button button_create_pdf;
    Button button_pay_later;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_selection);

        button_pay_later = findViewById(R.id.alto_card);

        button_pay_later.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PaymentSelection.this,PaymentCard.class);
                startActivity(intent);
            }
        });

        button_create_pdf = findViewById(R.id.alto_payLater);

        Dexter.withActivity(this)
                .withPermission(permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                        button_create_pdf.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                createPDFFILE(Common.getAppPath(PaymentSelection.this)+"test_pdf.pdf");
                            }
                        });
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .check();
    }

    private void createPDFFILE(String path) {

        if (new File(path).exists())
            new File(path).delete();


        try {


            Document document = new Document();

            //saving
            PdfWriter.getInstance(document,new FileOutputStream(path));

            //open to write
            document.open();

            //setting
            document.setPageSize(PageSize.A4);
            document.addCreationDate();
            document.addAuthor("Urban Crew");
            document.addCreator("Urban Crew Team");

            //font
            BaseColor colorAccent = new BaseColor(0,153,204,255);
            float fontSize = 20.0f;
            float valueFontSize = 26.0f;

            //custom font
            BaseFont fontName = BaseFont.createFont("assets/fonts/brandon_medium.otf","UTF-8", BaseFont.EMBEDDED);

            //create title of document
            Font titleFont = new Font(fontName,36.0f,Font.NORMAL,BaseColor.BLACK);
            addNewItem(document,"Payment Invoice", Element.ALIGN_CENTER,titleFont);

            //Add more
            Font orderNumberFont = new Font(fontName,fontSize,Font.NORMAL,colorAccent);
            addNewItem(document,"User Name",Element.ALIGN_LEFT,orderNumberFont);

            Font orderNumberValueFont = new Font(fontName,valueFontSize,Font.NORMAL,BaseColor.BLACK);
            addNewItem(document,"username",Element.ALIGN_LEFT,orderNumberValueFont);

            addLineSeparator(document);

            addNewItem(document, "Email", Element.ALIGN_LEFT, orderNumberFont);
            addNewItem(document, "email", Element.ALIGN_LEFT, orderNumberValueFont);

            addLineSeparator(document);

            addNewItem(document, "Phone", Element.ALIGN_LEFT, orderNumberFont);
            addNewItem(document, "phone", Element.ALIGN_LEFT, orderNumberValueFont);

            addLineSeparator(document);

            addNewItem(document, "Address", Element.ALIGN_LEFT, orderNumberFont);
            addNewItem(document, "abc", Element.ALIGN_LEFT, orderNumberValueFont);

            addLineSeparator(document);


            //Add Booking details
            addLineSpace(document);
            addNewItem(document, "Booking Information", Element.ALIGN_CENTER, titleFont);
            addLineSeparator(document);

            //Item 1
            addNewItemWithLeftAndRight(document, "PickUp Location", "pickuplocation", orderNumberValueFont, orderNumberValueFont);
            //addNewItemWithLeftAndRight(document, "T20", "(1200.00", titleFont, orderNumberValueFont);

            addLineSeparator(document);

            //Item2
            addNewItemWithLeftAndRight(document, "Date", "date", orderNumberValueFont, orderNumberValueFont);
            //addNewItemWithLeftAndRight(document, "T20", "1200.00", titleFont, orderNumberValueFont);

            addLineSeparator(document);

            //item3
            addNewItemWithLeftAndRight(document, "Time", "time", orderNumberValueFont, orderNumberValueFont);
            addLineSeparator(document);

            //Add booking option details
            addLineSpace(document);
            addNewItem(document, "Booking Options", Element.ALIGN_CENTER, titleFont);
            addLineSeparator(document);

            //Item 1
            addNewItemWithLeftAndRight(document, "Booking Price", "bookprice", orderNumberValueFont, orderNumberValueFont);
            //addNewItemWithLeftAndRight(document, "T20", "(1200.00", titleFont, orderNumberValueFont);

            addLineSeparator(document);

            //Item2
            addNewItemWithLeftAndRight(document, "Rental Extra Options", "rental extra", orderNumberValueFont, orderNumberValueFont);
            //addNewItemWithLeftAndRight(document, "T20", "1200.00", titleFont, orderNumberValueFont);

            //Total
            addLineSpace(document);
            addLineSpace(document);
            addLineSeparator(document);

            addNewItemWithLeftAndRight(document, "Total", "12000.00", titleFont, orderNumberValueFont);

            document.close();

            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

            printPDF();



        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (DocumentException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printPDF() {

        PrintManager printManager = (PrintManager)getSystemService(Context.PRINT_SERVICE);

        try {

            PrintDocumentAdapter printDocumentAdapter = new pdfDocumentAdapter(PaymentSelection.this,Common.getAppPath(PaymentSelection.this)+"test_pdf.pdf");
            printManager.print("Document",printDocumentAdapter,new PrintAttributes.Builder().build());
        } catch (Exception ex){

            Log.e("UrbanCrew",""+ex.getMessage());
        }
    }


    private void addNewItemWithLeftAndRight(Document document, String textLeft, String textRight,
                                            Font textLeftFont, Font textRightFont) throws DocumentException {

        Chunk chunkTextLeft = new Chunk(textLeft, textLeftFont);
        Chunk chunkTextRight = new Chunk(textRight, textRightFont);
        Paragraph p = new Paragraph(chunkTextLeft);
        p.add(new Chunk(new VerticalPositionMark()));
        p.add(chunkTextRight);
        document.add(p);
    }

    private void addLineSeparator(Document document) throws DocumentException {

        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineColor(new BaseColor(0,0,0,68));
        addLineSpace(document);
        document.add(new Chunk(lineSeparator));
        addLineSpace(document);
    }

    private void addLineSpace(Document document) throws DocumentException {

        document.add(new Paragraph(""));
    }

    private void addNewItem(Document document, String text , int align, Font font) throws DocumentException {

        Chunk chunk = new Chunk(text,font);
        Paragraph paragraph = new Paragraph(chunk);
        paragraph.setAlignment(align);
        document.add(paragraph);
    }
}
