package com.stealth.yash.FaceRecognition.model;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;

import java.util.List;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.PostConstruct;
import javax.naming.Binding;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Getter
@Setter
@Service
public class AWSClient {

    private AmazonS3 client;
    String collectionId = "myfaces";
    String bucket = "uploaded-faces-stealth";
    String image = "";

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.client = new AmazonS3Client(credentials);
    }


    private File convertToFile(MultipartFile file) throws IOException {
           File convertFile = new File(file.getOriginalFilename());
           FileOutputStream fos = new FileOutputStream(convertFile);
           fos.write(file.getBytes());
           fos.close();

        return convertFile;

    }
    private void uploadtoS3(String fileName, File file) {
        client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public void removeFile(String fileString){
        String getFileName = fileString.substring(fileString.lastIndexOf("/") + 1);
        client.deleteObject(new DeleteObjectRequest(bucketName, getFileName));
    }

    public String uploadFile(MultipartFile multipartFile, String uuid) {

        Student student = new Student();
        String fileUrl = "";
        try {
            File file = convertToFile(multipartFile);
            String fobid = uuid + ".jpg";
            fileUrl = endpointUrl + "/" + bucketName + "/" + uuid ;
            uploadtoS3(fobid, file);
            file.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }


    //Method to add the image to the collection when student is added so it can be used to recognize
    // the user using raspberry pi
    public String addfacetoawscollection(String image){
        AWSCredentials rekcredentials = new BasicAWSCredentials("AKIAITCZKIVJA7XIAWZQ","pEwZF7jN3Kw8tSlTwSMTGOkA1+iff9FygBxJ9Jfm");
        AmazonRekognitionClient rekognitionClient = new AmazonRekognitionClient(rekcredentials)
                .withRegion(RegionUtils.getRegion("ca-central-1"));
        Image picture = new Image().withS3Object(new S3Object().withBucket(bucket).withName(image));
        IndexFacesRequest indexFacesRequest = new IndexFacesRequest().withImage(picture).withCollectionId(collectionId).withExternalImageId(image).withDetectionAttributes("DEFAULT");
        IndexFacesResult indexFacesResult = rekognitionClient.indexFaces(indexFacesRequest);
        List<FaceRecord> id = indexFacesResult.getFaceRecords();
        String userfaceid="";
        for(FaceRecord faceid : id){
            userfaceid = faceid.getFace().getFaceId();
        }
        return userfaceid;
    }


    public void deletefacefromawscollection(String faceid){
        AWSCredentials rekcredentials = new BasicAWSCredentials("AKIAITCZKIVJA7XIAWZQ","pEwZF7jN3Kw8tSlTwSMTGOkA1+iff9FygBxJ9Jfm");
        AmazonRekognitionClient rekognitionClient = new AmazonRekognitionClient(rekcredentials)
                .withRegion(RegionUtils.getRegion("ca-central-1"));
        DeleteFacesRequest deleteFacesRequest = new DeleteFacesRequest().withCollectionId(collectionId).withFaceIds(faceid);
    }

}
