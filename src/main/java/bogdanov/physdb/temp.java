package bogdanov.physdb;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class temp {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        File file = new File("tmp.txt");
        byte[] bytes = getRandomFile(file);
        FileInputStream input = new FileInputStream(file);
        MessageDigest md = MessageDigest.getInstance("MD5");
        InputStream in = new FileInputStream(file);
        DigestInputStream din = new DigestInputStream(in, md);
        MultipartFile multiFile = new MockMultipartFile("multitmp.txt", "tmp.txt", "text/plain", din);
        byte[] digest = md.digest();
//        System.out.println(md.digest().equals(bytes));
//        System.out.println(digest);
//        System.out.println(Arrays.toString(md.digest()));
//        System.out.println(bytes.equals(multiFile.getBytes()));
//        System.out.println(Arrays.toString(bytes));
//        System.out.println(Arrays.toString(multiFile.getBytes()));
//        in = new ByteArrayInputStream(multiFile.getBytes());
//        din = new DigestInputStream(in, md);
//        byte[] bytes2 = din.readAllBytes();
        System.out.println(digest.equals(md.digest(multiFile.getBytes())));
        System.out.println(Arrays.toString(digest));
        System.out.println(Arrays.toString(md.digest(bytes)));
        System.out.println(Arrays.toString(md.digest(multiFile.getBytes())));
        String chs1 = DatatypeConverter.printHexBinary(digest).toUpperCase();
        String chs2 = DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
        String chs3 = DatatypeConverter.printHexBinary(md.digest(bytes)).toUpperCase();
        System.out.println(chs1);
        System.out.println(chs2);
        System.out.println(chs3);
        FileOutputStream out = new FileOutputStream(file);
        out.write(multiFile.getBytes());
        byte[] tmp = new FileInputStream(file).readAllBytes();
        System.out.println(Arrays.toString(md.digest(tmp)));
        System.out.println(DatatypeConverter.printHexBinary(md.digest(tmp)).toUpperCase());
        System.out.println(Arrays.toString(md.digest(multiFile.getBytes())));
        System.out.println(DatatypeConverter.printHexBinary(md.digest(multiFile.getBytes())).toUpperCase());
        System.out.println(DatatypeConverter.printHexBinary(md.digest(multiFile.getBytes())).toUpperCase().length());
    }

    private static byte[] getRandomFile(File file) throws IOException {
        Random generator = new Random();
        byte[] byteArray = new byte[2048];
        generator.nextBytes(byteArray);
        FileOutputStream out = new FileOutputStream(file);
        out.write(byteArray);
        return byteArray;
    }
}
