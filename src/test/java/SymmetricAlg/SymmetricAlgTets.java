package SymmetricAlg;

import com.google.gson.Gson;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.sql.Array;
import java.util.ArrayList;

/**
 * @author: RyuZUSUNC
 * @create: 2021-12-03 09:05
 **/

public class SymmetricAlgTets {
    public static String[] ALG_b = {"AES", "ARIA", "Blowfish","CAST5","CAST6","Camellia","DES","DESEDE","DSTU7624","IDEA","RC2","RC5","RC6","SEED","SM4","Serpent","Skipjack","TEA","Threefish-1024","Threefish-256","Threefish-512","Twofish","XTEA"};
    public static String[] ALG_s = {};
    public static String[] ALG_PADDING = {"PKCS5Padding", "PKCS7Padding", "ISO10126Padding", "ZeroBytePadding", "NoPadding", "TBCPadding", "X923Padding", "ISO7816d4Padding", "ISO10126d2Padding"};
    public static String[] ALG_MODE = {"CBC", "ECB", "CFB", "OFB", "CTR", "GCM", "CCM", "EAX", "OCB"};

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());
        ArrayList<algName> algs = new ArrayList();

        for (String ALG : ALG_b) {
            algName algName = new algName();
            algName.setAlgName(ALG);
            for (String MODE : ALG_MODE) {
                algWorkMode algWorkMode = new algWorkMode();
                for (String PADDING : ALG_PADDING) {
                    String tmp = ALG + "/" + MODE + "/" + PADDING;
                    if (getAlgMode(tmp)) {
                        algWorkMode.setModeName(MODE);
//                        System.out.println(tmp);
                        algWorkMode.getModePaddingName().add(PADDING);
                    }
                }
                if (algWorkMode.getModeName() != null) {
                    algName.getAlgWorkModes().add(algWorkMode);
                }
            }
            algs.add(algName);
        }

//        for (algName alg : algs) {
//            System.out.print(alg.getAlgName() + " ");
//            for (algWorkMode algWorkMode : alg.getAlgWorkModes()) {
//                System.out.print("{");
//                System.out.print(algWorkMode.getModeName() + " / ");
//                for (String s : algWorkMode.getModePaddingName()) {
//                    System.out.print(s + " ");
//                }
//                System.out.print("}");
//            }
//            System.out.println("\n");
//        }

        System.out.println(new Gson().toJson(algs));
    }

    public static Boolean getAlgMode(String ALGORITHM) {
        try {
            Cipher.getInstance(ALGORITHM);
            return true;
        } catch (NoSuchAlgorithmException e) {
//            System.out.println("Error Algorithm");
//            e.printStackTrace();
            return false;
        } catch (NoSuchPaddingException e) {
//            System.out.println("Error Padding");
//            e.printStackTrace();
            return false;
        }
    }

    public static void getmessage() {

        for (String message : Security.getAlgorithms("MessageDigest")) {
//            System.out.println(message);
        }

        System.out.println("----------------------------");

        for (String s : Security.getAlgorithms("Signature")) {
//            System.out.println(s);
        }

        System.out.println("----------------------------");

        for (String s : Security.getAlgorithms("Cipher")) {
            System.out.println(s);
//            if (s.contains("AES_128")){
//                System.out.println(s);
//            }
        }

        System.out.println("----------------------------");

        for (String s : Security.getAlgorithms("Mac")) {
//            System.out.println(s);
        }

//        System.out.println("----------------------------");

        for (String s : Security.getAlgorithms("KeyStore")) {
//            System.out.println(s);
        }
    }
}
