package com.kurdcoin.core;

import java.math.BigInteger;
import java.util.ArrayList;

public class Sha256 {
    // take msg
    // breakdown msg for each char in int
    // convert for each char to 8 bit binary
    static int[] rt2 = {2,3,5,7,11,13,17,19};
    static int[] rt3 = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,
            101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,
            193,197,199,211,223,227,229,233,239,241,251,257,263,269,271,277,281,283,
            293,307,311};

    public static ArrayList<String> rt2s(){
        //2^1/2 - 2^1/2 * 2^32
        ArrayList<String> rt = new ArrayList<>();
        for (int i = 0; i < rt2.length; i++){
            rt.add(addZeros(String.format(Long.toBinaryString((long)((Math.sqrt(rt2[i]) - (int)Math.sqrt(rt2[i]))*Math.pow(2, 32)))),32));
        }
        //System.out.println("rt2: " + rt.toString());
        return rt;
    }
//    h0 := 0x6a09e667
//    h1 := 0xbb67ae85
//    h2 := 0x3c6ef372
//    h3 := 0xa54ff53a
//    h4 := 0x510e527f
//    h5 := 0x9b05688c
//    h6 := 0x1f83d9ab
//    h7 := 0x5be0cd19
//              01101010000010011110011001100111    01101010000010011110011001100111
////            10111011011001111010111010000101    10111011011001111010111010000101
////            00111100011011101111001101110010    00111100011011101111001101110010
////            10100101010011111111010100111010    10100101010011111111010100111010
////            01010001000011100101001001111111    01010001000011100101001001111111
////            10011011000001010110100010001100    10011011000001010110100010001100
////            00011111100000111101100110101011
////            01011011111000001100110100011001
//    rt2: [01101010000010011110011001100111, 10111011011001111010111010000101, 00111100011011101111001101110010, 10100101010011111111010100111010, 01010001000011100101001001111111, 10011011000001010110100010001100, 00011111100000111101100110101011, 01011011111000001100110100011001]


    public static ArrayList<String> rt3s(){
        //2^1/3 - 2^1/3 * 2^32
        ArrayList<String> rt = new ArrayList<>();
        for (int i = 0; i < rt3.length; i++){
            rt.add(addZeros(String.format(Long.toBinaryString((long)((Math.cbrt(rt3[i]) - (int)Math.cbrt(rt3[i]))*Math.pow(2, 32)))),32));
      //    rt.add(addZeros(String.format(Long.toBinaryString((long)((Math.sqrt(rt2[i]) - (int)Math.sqrt(rt2[i]))*Math.pow(2, 32)))),32));
        }
       // System.out.println("rt3: " + rt.toString());
        return rt;
    }

//    Initialize array of round constants:
//            (first 32 bits of the fractional parts of the cube roots of the first 64 primes 2..311):
//    k[0..63] :=
//            0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
//            0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
//            0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
//            0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
//            0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
//            0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
//            0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
//            0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
      //01000010100010100010111110011000
    //['01000010100010100010111110011000', '01110001001101110100010010010001', '10110101110000001111101111001111', '11101001101101011101101110100101', '00111001010101101100001001011011', '01011001111100010001000111110001', '10010010001111111000001010100100', '10101011000111000101111011010101', '11011000000001111010101010011000', '00010010100000110101101100000001', '00100100001100011000010110111110', '01010101000011000111110111000011', '01110010101111100101110101110100', '10000000110111101011000111111110', '10011011110111000000011010100111', '11000001100110111111000101110100', '11100100100110110110100111000001', '11101111101111100100011110000110', '00001111110000011001110111000110', '00100100000011001010000111001100', '00101101111010010010110001101111', '01001010011101001000010010101010', '01011100101100001010100111011100', '01110110111110011000100011011010', '10011000001111100101000101010010', '10101000001100011100011001101101', '10110000000000110010011111001000', '10111111010110010111111111000111', '11000110111000000000101111110011', '11010101101001111001000101000111', '00000110110010100110001101010001', '00010100001010010010100101100111', '00100111101101110000101010000101', '00101110000110110010000100111000', '01001101001011000110110111111100', '01010011001110000000110100010011', '01100101000010100111001101010100', '01110110011010100000101010111011', '10000001110000101100100100101110', '10010010011100100010110010000101', '10100010101111111110100010100001', '10101000000110100110011001001011', '11000010010010111000101101110000', '11000111011011000101000110100011', '11010001100100101110100000011001', '11010110100110010000011000100100', '11110100000011100011010110000101', '00010000011010101010000001110000', '00011001101001001100000100010110', '00011110001101110110110000001000', '00100111010010000111011101001100', '00110100101100001011110010110101', '00111001000111000000110010110011', '01001110110110001010101001001010', '01011011100111001100101001001111', '01101000001011100110111111110011', '01110100100011111000001011101110', '01111000101001010110001101101111', '10000100110010000111100000010100', '10001100110001110000001000001000', '10010000101111101111111111111010', '10100100010100000110110011101011', '10111110111110011010001111110111', '11000110011100010111100011110010']
 // 3: [01000010100010100010111110011000,   01110001001101110100010010010001,   10110101110000001111101111001111,   11101001101101011101101110100101,   00111001010101101100001001011011,   01011001111100010001000111110001, 10010010001111111000001010100100, 10101011000111000101111011010101, 11011000000001111010101010011000, 00010010100000110101101100000001, 00100100001100011000010110111110, 01010101000011000111110111000011, 01110010101111100101110101110100, 10000000110111101011000111111110, 10011011110111000000011010100111, 11000001100110111111000101110100, 11100100100110110110100111000001, 11101111101111100100011110000110, 00001111110000011001110111000110, 00100100000011001010000111001100, 00101101111010010010110001101111, 01001010011101001000010010101010, 01011100101100001010100111011100, 01110110111110011000100011011010, 10011000001111100101000101010010, 10101000001100011100011001101101, 10110000000000110010011111001000, 10111111010110010111111111000111, 11000110111000000000101111110011, 11010101101001111001000101000111, 00000110110010100110001101010001, 00010100001010010010100101100111, 00100111101101110000101010000101, 00101110000110110010000100111000, 01001101001011000110110111111100, 01010011001110000000110100010011, 01100101000010100111001101010100, 01110110011010100000101010111011, 10000001110000101100100100101110, 10010010011100100010110010000101, 10100010101111111110100010100001, 10101000000110100110011001001011, 11000010010010111000101101110000, 11000111011011000101000110100011, 11010001100100101110100000011001, 11010110100110010000011000100100, 11110100000011100011010110000101, 00010000011010101010000001110000, 00011001101001001100000100010110, 00011110001101110110110000001000, 00100111010010000111011101001100, 00110100101100001011110010110101, 00111001000111000000110010110011, 01001110110110001010101001001010, 01011011100111001100101001001111, 01101000001011100110111111110011, 01110100100011111000001011101110, 01111000101001010110001101101111, 10000100110010000111100000010100, 10001100110001110000001000001000, 10010000101111101111111111111010, 10100100010100000110110011101011, 10111110111110011010001111110111, 11000110011100010111100011110010]

    public static void main(String[] args) {
        hashes("");
        hashes("123");
        hashes("abc");

    }

    public static void hashes(String msg){
        ArrayList<String> rt22 = rt2s();
        ArrayList<String> rt33 = rt3s();

        String message = "";
        for (int i = 0; i < msg.length(); i++){
            message += String.format("%08d", Integer.parseInt(Integer.toBinaryString((int) msg.charAt(i)))); //msg to binary
        }
        String msgLen = String.format("%064d", Integer.parseInt(Integer.toBinaryString((int) message.length()))); //msg length in binary
        int chunkno = chunkNo(msg); //chuncks required

        message += "1";
        int padding = chunkno*512-(message.length()+64);

        for (int i = 0; i < padding; i++){
            message += "0";
        }
        message += msgLen;

        int min = 0;
        int max = 512;
        ArrayList<String> chunks = new ArrayList<>();
        for (int i = 0; i < chunkno; i++){
            chunks.add(message.substring(min,max)); //split into chunks of 512bit
            min += 512;
            max += 512;
        }

        //process the message in successive 512bit chunks:
        for (int i = 0; i < chunkno; i++){
            String newMsg = chunks.get(i);
            min = 0;
            max = 32;

            ArrayList<String> msgChunks = new ArrayList<>();
            for (int j = 0; j < 16; j++){
                msgChunks.add(newMsg.substring(min,max));
                min = 0;
                max = 32;
            }

            //
            //
            //
            for (int j = 0; j < 16; j++){

            }
            //
            //
            //

            for (int j = 16; j < 64; j++){
                //int mlen = msgChunks.size();
                String s0 = sig0(msgChunks.get(msgChunks.size()-15));
                String s1 = sig1(msgChunks.get(msgChunks.size()-2));
                String addup = adder(msgChunks.get(msgChunks.size()-16), s0, msgChunks.get(msgChunks.size()-7), s1);
                msgChunks.add(addup);

            }
            String a = rt22.get(0);
            String b = rt22.get(1);
            String c = rt22.get(2);
            String d = rt22.get(3);
            String e = rt22.get(4);
            String f = rt22.get(5);
            String g = rt22.get(6);
            String h = rt22.get(7);

            for (int j = 0; j < 64; j++){
                String S1 = sigma1(e);
                String ch = cho(e,f,g);
                String temp1 = adders(Long.parseLong(h, 2) + Long.parseLong(S1, 2) +
                        Long.parseLong(ch, 2) + Long.parseLong(rt33.get(j), 2) +
                        Long.parseLong(msgChunks.get(j), 2));

                String S0 = sigma0(a);
                String maj = mj(a,b,c);
                String temp2 = adders(Long.parseLong(S0, 2) + Long.parseLong(maj, 2));

                h = g;
                g = f;
                f = e;
                e = adders(Long.parseLong(d, 2) + Long.parseLong(temp1, 2));
                d = c;
                c = b;
                b = a;
                a = adders(Long.parseLong(temp1, 2) + Long.parseLong(temp2, 2));

            }
            rt22.set(0, adders(Long.parseLong(rt22.get(0), 2) + Long.parseLong(a, 2)));
            rt22.set(1, adders(Long.parseLong(rt22.get(1), 2) + Long.parseLong(b, 2)));
            rt22.set(2, adders(Long.parseLong(rt22.get(2), 2) + Long.parseLong(c, 2)));
            rt22.set(3, adders(Long.parseLong(rt22.get(3), 2) + Long.parseLong(d, 2)));
            rt22.set(4, adders(Long.parseLong(rt22.get(4), 2) + Long.parseLong(e, 2)));
            rt22.set(5, adders(Long.parseLong(rt22.get(5), 2) + Long.parseLong(f, 2)));
            rt22.set(6, adders(Long.parseLong(rt22.get(6), 2) + Long.parseLong(g, 2)));
            rt22.set(7, adders(Long.parseLong(rt22.get(7), 2) + Long.parseLong(h, 2)));


        }
        String digest = "";
        String hexStr = "";
        for (int j = 0; j < 8; j++){
            digest += rt22.get(j);
            long decimal = Long.parseLong(rt22.get(j),2);
            hexStr += Long.toString(decimal,16);
        }
        //long decimal = Long.parseLong(digest,2);
        //String hexStr = Long.toString(decimal,16);
        String hexString = new BigInteger(digest, 2).toString(16);

        System.out.println(hexStr);
        System.out.println(digest);
        System.out.println(hexString);





        // digestRes = hex(int(digest, 2))
//        digestOb = digestRes[2:]
//
//        if len(digestOb) < 64:
//        digestOb = "0" + digestOb
//
//    # print(digest)
//    # print(len(digest))
//    # print(hex(int(digest, 2)))
//    #return hex(int(digest, 2))
//        return digestOb

    }

    public static int chunkNo(String msg){
        int chunks = 1;
        int bsize = 447;

        if(msg.length() <= bsize){
            chunks = 1;
        }else if(msg.length() >= bsize+1){
            while(true){
                bsize += 512;
                chunks += 1;
                if(msg.length() <= bsize){
                    break;
                }
            }
        }
        return chunks;
    }

    public static String sig0(String bits){
        String a = rotr(bits,7);
        String b = rotr(bits,18);
        String c = shr(bits,3);

        String res = xor(a,b,c);
        return res;
    }

    public static String sig1(String bits){
        String a = rotr(bits,17);
        String b = rotr(bits,19);
        String c = shr(bits,10);

        String res = xor(a,b,c);
        return res;
    }

    public static String sigma0(String bits){
        String a = rotr(bits,2);
        String b = rotr(bits,13);
        String c = rotr(bits,22);

        String res = xor(a,b,c);
        return res;
    }

    public static String sigma1(String bits){
        String a = rotr(bits,6);
        String b = rotr(bits,11);
        String c = rotr(bits,25);

        String res = xor(a,b,c);
        return res;
    }

    public static String xor(String a, String b, String c){
        String res = "";
        for(int i = 0; i < a.length(); i++){
            if(a.substring(i).equals("1") && b.substring(i).equals("1")){
                res += "1";
            }else if(b.substring(i).equals("1") && c.substring(i).equals("1")){
                res += "1";
            }else if(a.substring(i).equals("1") && c.substring(i).equals("1")){
                res += "1";
            }else{
                res += "0";
            }
        }
        return res;
    }

    public static String adder(String a, String b, String c, String d){
        String res = "";
        long aint = Long.parseLong(a, 2);
        long bint = Long.parseLong(b, 2);
        long cint = Long.parseLong(c, 2);
        long dint = Long.parseLong(d, 2);
        long r = aint + bint + cint + dint;

        String binr = Long.toBinaryString((long) r);
        if(binr.length() == 32){
            res = binr;
        }else if(binr.length() >= 32){
            res = Long.toBinaryString((long) (r % Math.pow(2,32)));
        }

        if(res.length() < 32){
            res = addZeros(res,32);
        }
        return res;
    }

    public static String adders(long a){
        String res = "";
        long r = a;

        //String binr = String.format("%032d", Long.parseLong(Long.toBinaryString((long) r)));
        String binr = Long.toBinaryString((long) r);
        if(binr.length() == 32){
            res = binr;
        }else if(binr.length() >= 32){
            res = Long.toBinaryString((long) (r % Math.pow(2,32)));
        }

        if(res.length() < 32){
            res = addZeros(res,32);
        }
        return res;
    }

    public static String rotr(String a, int rotnumber){
        for(int i = 0; i < rotnumber; i++){
            String last_char = a.substring(a.length()-1);
            a = a.substring(0,a.length()-1);
            a = last_char + a;
        }
        return a;
    }

    public static String shr(String a, int rotnumber){
        for(int i = 0; i < rotnumber; i++){
            a = a.substring(0,a.length()-1);
            a = "0" + a;
        }
        return a;
    }

    public static String cho(String a, String b, String c){
//    #use 'a' input to determine whether to take 'b' or 'c'
        String res = "";
        for(int i = 0; i < a.length(); i++){
            if(a.substring(i).equals("1")){
                res += b.substring(i);
            }else if(a.substring(i).equals("0")){
                res += c.substring(i);
            }
        }
        return res;
    }

    public static String mj(String a, String b, String c){
//    #take majority input value
        String res = "";
        for(int i = 0; i < a.length(); i++){
            if((a.substring(i).equals("1") && b.substring(i).equals("1")) ^
                    (a.substring(i).equals("1") && c.substring(i).equals("1")) ^
                    (b.substring(i).equals("1") && c.substring(i).equals("1"))){
                res += "1";
            }else{
                res += "0";
            }
        }
        return res;
    }

    public static String addZeros(String msg, int newLen){
        for(int i = msg.length(); i < newLen; i++){
            msg = "0" + msg;
        }
        return msg;
    }

}
