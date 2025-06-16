package com.orderservice.app.services;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.orderservice.app.dao.OrderDao;
import com.orderservice.app.dao.S3Storage;
import com.orderservice.app.dto.OrderRequest;
import com.orderservice.app.kafka.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class FileStorageService {

    private final S3Storage s3Storage;

    private final OrderProducer orderProducer;

    private final OrderDao orderDao;

    @Autowired
    public FileStorageService(S3Storage s3Storage,OrderProducer orderProducer, OrderDao orderDao){
        this.s3Storage = s3Storage;
        this.orderProducer = orderProducer;
        this.orderDao = orderDao;
    }

    public void pushInvoce(OrderRequest request){
        byte[] pdfFile = this.generatedInvocePDF(request);
        s3Storage.uploadFile("invoce-storage","invoce:" + request.buyerId(), pdfFile);
    }

    private byte[] generatedInvocePDF(OrderRequest orderRequest){
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        document.add(new Paragraph("BuyerInvoice"));
        document.add(new Paragraph("BuyerId: " + orderRequest.buyerId()));
        document.add(new Paragraph("ProductId: " + orderRequest.productId()));
        document.add(new Paragraph("Quantity: " + orderRequest.quantity()));
        document.add(new Paragraph("Email: " + orderRequest.userEmail()));
        document.close();
        return outputStream.toByteArray();
    }

    public void pushCreatedOrderKafka(OrderRequest order){
        orderProducer.sendMessage(order);
    }

    public void pushInDataBase(OrderRequest order){
        orderDao.addOrder(order);
    }

    public OrderRequest getOrderById(String id){
        return orderDao.getOrderRequest(id);
    }

}
