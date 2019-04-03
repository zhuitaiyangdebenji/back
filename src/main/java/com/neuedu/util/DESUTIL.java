package com.neuedu.util;

import com.thoughtworks.xstream.core.util.Base64Encoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by zhao on 2019/4/1.
 */
public class DESUTIL {
    //图纸
    static Key key;
    //算法因子
    static  String KEYSTR="wy";
    static {
        try {
            //key的生成器
            KeyGenerator keyGenerator=KeyGenerator.getInstance("DES");
            SecureRandom secureRandom=SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(KEYSTR.getBytes());
            //初始化key,通过给定的算法因子初始化
            keyGenerator.init(secureRandom);
            //生成了一个key
             key=keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    //加密方法
    public  static  String encode(String password){
        BASE64Encoder base64Encoder=new BASE64Encoder();
        String result="";
        try {
            Cipher cipher=Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] b= cipher.doFinal(password.getBytes());
            result=base64Encoder.encode(b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return  result;
    }
    public  static  String decode(String str){
        String result="";
        Base64Encoder base64Encoder=new Base64Encoder();
        byte[] b=base64Encoder.decode(str);
        try {
            Cipher cipher=Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] pwd=cipher.doFinal(b);
            result=new String(pwd);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return  result;
    }
    public static void main(String[] args) {
        System.out.println(decode("k6kNZBRUISk="));
    }
}
