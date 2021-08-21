package bogdanov.physdb;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class temp {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        int iterations = 100;
        int n = 20000000;

        Map<Integer, Long> time = new TreeMap<>();
        int[] array = new int[n];
        Random generator = new Random(System.nanoTime());
        for (int i = 0; i < array.length; i++) {
            array[i] = generator.nextInt();
        }

        Set<Integer> ks = new TreeSet<>();
        while (ks.size() < iterations) {
            ks.add(generator.nextInt(array.length - 100) + 100);
        }

        Iterator<Integer> iterator = ks.iterator();
        int k = 0;
        for (int i = 0; i < iterations; i++) {
            k = iterator.next();
            time.put(k, findRandomElements(k, array, i));
            System.out.println(k + " : " + time.get(k));
        }
        System.out.println("\n\t_______k : _______________time");
        for (Map.Entry<Integer, Long> e : time.entrySet()) {
            long t = e.getValue();
            System.out.printf("\t%8d : %6dms_%3dmcs_%3dns%n", e.getKey(), t / 1_000_000, t / 1_000 % 1_000, t % 1000);
        }

    }

    private static long findRandomElements(int k, int[] array, int index) {
        long time = System.nanoTime();
        int[] randomElements = new int[k];

        time = System.nanoTime();

        Random generator = new Random(time);

        block: {
            if (k > (array.length >> 1)) {
                int l = array.length;
                int pos;
                int tmp;
                for (int i = 0; i < array.length-k; i++) {
                    pos = generator.nextInt(l--);
                    randomElements[i] = array[pos];
                    tmp = array[pos];
                    array[pos] = array[l];
                    array[l] = tmp;
                }
                randomElements = Arrays.copyOfRange(array, 0, l);
//                System.arraycopy(array, 0, randomElements, 0, k);
                break block;
            }

            int l = array.length;
            int pos;
            int tmp;
            for (int i = 0; i < k; i++) {
                pos = generator.nextInt(l--);
                randomElements[i] = array[pos];
                tmp = array[pos];
                array[pos] = array[l-1];
                array[l-1] = tmp;
            }
//            System.arraycopy(array, l, randomElements, 0, k);

        }

        time = System.nanoTime() - time;

        System.out.println(index + ") " + Arrays.hashCode(randomElements));

        return time;
    }


    private static void testBCryptUpgrade() {
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder(11);
        String raw = "AFgJ^!5vd@6QTpLt";
        System.out.println(raw);
        String psw = enc.encode(raw);
        System.out.println(psw);
        enc = new BCryptPasswordEncoder(5);
        Boolean m, u = null;
        long time = System.nanoTime();
        if (m = enc.matches(raw, psw)) {
            if (u = enc.upgradeEncoding(psw)) {
                psw = enc.encode(raw);
            }
        }
        time = System.nanoTime() - time;
        System.out.println(m);
        System.out.println(u);
        System.out.println(psw);
        System.out.println(time);
        System.out.println(enc.matches(raw, psw));
    }

    private static void testBCryptTime(int number, Integer strength) {
        BCryptPasswordEncoder bCryptPasswordEncoder;
        if (strength == null) {
            bCryptPasswordEncoder = new BCryptPasswordEncoder();
        } else {
            bCryptPasswordEncoder = new BCryptPasswordEncoder(strength);
        }
        long[] time = new long[number];
        String[] password = getRandomStrings(number);
        String[] encoded = new String[password.length];
        for (int i = 0; i < number; i++) {
            time[i] = System.nanoTime();
            encoded[i] = bCryptPasswordEncoder.encode(password[i]);
            time[i] = System.nanoTime() - time[i];
        }
        time = Arrays.stream(time).sorted().toArray();
//        Arrays.stream(time).forEach(System.out::println);
        long average = (long) Arrays.stream(time).average().getAsDouble();
        System.out.printf(
                "%s\n\nENCODE :\n\tstr : %d\n\tsize : %d\n\ttime :\n\t\tmin : %d.%d ms\n\t\tmax : %d.%d ms\n\t\tavg : %d.%d ms\n\n",
                "---------------------------",
                strength,
                time.length,
                time[0] / 1_000_000,
                time[0] / 1_000 % 1_000,
                time[time.length - 1] / 1_000_000,
                time[time.length - 1] / 1_000 % 1_000,
                average / 1_000_000,
                average / 1_000 % 1_000
        );
        boolean[] matches = new boolean[number];
        for (int i = 0; i < number; i++) {
            time[i] = System.nanoTime();
            matches[i] = bCryptPasswordEncoder.matches(password[i], encoded[i]);
            time[i] = System.nanoTime() - time[i];
        }
        time = Arrays.stream(time).sorted().toArray();
//        Arrays.stream(time).forEach(System.out::println);
        average = (long) Arrays.stream(time).average().getAsDouble();
        boolean result = true;
        for (boolean m : matches) {
            result &= m;
        }
        System.out.printf(
                "MATCHES : %s\n\tstr : %d\n\tsize : %d\n\ttime :\n\t\tmin : %d.%d ms\n\t\tmax : %d.%d ms\n\t\tavg : %d.%d ms\n\n",
                result,
                strength,
                time.length,
                time[0] / 1_000_000,
                time[0] / 1_000 % 1_000,
                time[time.length - 1] / 1_000_000,
                time[time.length - 1] / 1_000 % 1_000,
                average / 1_000_000,
                average / 1_000 % 1_000
        );
    }

    private static String[] getRandomStrings(int number) {
        String[] strings = new String[number];
        Random gen = new Random(System.nanoTime());
        StringBuilder sb;
        int i, j;
        for (i = 0; i < strings.length; i++) {
            sb = new StringBuilder();
            for (j = 0; j < (8 + gen.nextInt(9)); j++) {
                sb.append((char) (33 + gen.nextInt(94)));
            }
            strings[i] = sb.toString();
        }
//        Arrays.stream(strings).forEach(System.out::println);
        return strings;
    }

    private static void testBCrypt() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String plainPassword = "dfoJnejf83j21!A";
        String encodedPassword;
        Long time = System.nanoTime();
        encodedPassword = bCryptPasswordEncoder.encode(plainPassword);
        time = System.nanoTime() - time;
        System.out.printf(
                "time : %d ms %d mcs %d ns --- %d ns\n",
                time / 1_000_000,
                time / 1_000 % 1_000,
                time % 1_000,
                time
        );
        System.out.println(encodedPassword.length());
        System.out.println(encodedPassword);
        String encodedPassword2;
        time = System.nanoTime();
        encodedPassword2 = bCryptPasswordEncoder.encode(plainPassword);
        time = System.nanoTime() - time;
        System.out.printf(
                "time : %d ms %d mcs %d ns --- %d ns\n",
                time / 1_000_000 % 1_000,
                time / 1_000 % 1_000,
                time % 1_000,
                time
        );
        System.out.println(encodedPassword2.length());
        System.out.println(encodedPassword2);
        System.out.println("encoded2 == encoded1 : " + encodedPassword2.equals(encodedPassword));
        boolean matches;
        time = System.nanoTime();
        matches = bCryptPasswordEncoder.matches(plainPassword, encodedPassword);
        time = System.nanoTime() - time;
        System.out.printf(
                "time : %d ms %d mcs %d ns --- %d ns\n",
                time / 1_000_000 % 1_000,
                time / 1_000 % 1_000,
                time % 1_000,
                time
        );
        System.out.println("plain matches encoded1 : " + matches);
        time = System.nanoTime();
        matches = bCryptPasswordEncoder.matches(plainPassword, encodedPassword2);
        time = System.nanoTime() - time;
        System.out.printf(
                "time : %d ms %d mcs %d ns --- %d ns\n",
                time / 1_000_000 % 1_000,
                time / 1_000 % 1_000,
                time % 1_000,
                time
        );
        System.out.println("plain matches encoded2 : " + matches);
        System.out.println("sdfsdf matches encoded : " + bCryptPasswordEncoder.matches("sdfsdf", encodedPassword));
    }

    private static void testMd5() throws IOException, NoSuchAlgorithmException {
        File file = new File("tmp.txt");
        byte[] bytes = getRandomFile(file);
        FileInputStream input = new FileInputStream(file);
        MessageDigest md = MessageDigest.getInstance("MD5");
        InputStream in = new FileInputStream(file);
        DigestInputStream din = new DigestInputStream(in, md);
        MultipartFile multiFile = new MockMultipartFile("multitmp.txt", "tmp.txt", "text/plain", din);
        byte[] digest = md.digest();
        System.out.println(md.digest().equals(bytes));
        System.out.println(digest);
        System.out.println(Arrays.toString(md.digest()));
        System.out.println(bytes.equals(multiFile.getBytes()));
        System.out.println(Arrays.toString(bytes));
        System.out.println(Arrays.toString(multiFile.getBytes()));
        in = new ByteArrayInputStream(multiFile.getBytes());
        din = new DigestInputStream(in, md);
        byte[] bytes2 = din.readAllBytes();
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
