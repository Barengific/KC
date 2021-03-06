package com.kurdcoin.crypto.secp256k1;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.math.ec.ECPoint;
import com.kurdcoin.crypto.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implementation of the key generator for SECP256K1.
 */
public class SecP256K1KeyGenerator implements KeyGenerator {

	private static final SecureRandom RANDOM = new SecureRandom();

	@Override
	public KeyPair generateKeyPair() {
		final ECKeyPairGenerator generator = new ECKeyPairGenerator();
		final ECKeyGenerationParameters keyGenParams = new ECKeyGenerationParameters(SecP256K1Curve.secp256k1().getParams(), RANDOM);
		generator.init(keyGenParams);

		final AsymmetricCipherKeyPair keyPair = generator.generateKeyPair();
		final ECPrivateKeyParameters privateKeyParams = (ECPrivateKeyParameters) keyPair.getPrivate();
		final PrivateKey privateKey = new PrivateKey(privateKeyParams.getD());
		return new KeyPair(privateKey, CryptoEngines.secp256k1Engine());
	}

	@Override
	public PublicKey derivePublicKey(final PrivateKey privateKey) {
		final ECPoint point = SecP256K1Curve.secp256k1().getParams().getG().multiply(privateKey.getRaw());
		return new PublicKey(point.getEncoded(true));
	}

	public static ArrayList<String> wordslist() throws FileNotFoundException {
		ArrayList<String> result = new ArrayList<>();

		File filename = new File("src/main/resources/words_list.txt");
		Scanner myReader = new Scanner(filename);
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			result.add(data);
		}

		return result;
	}

	public static ArrayList<String> seed_phrase(String entropys) throws FileNotFoundException {
		ArrayList<String> arr_entropy = new ArrayList<>();
		ArrayList<Integer> decimalValue = new ArrayList<>();
		ArrayList<String> wordslist = wordslist();
		ArrayList<String> seed_words = new ArrayList<>();

		String entropy = convertStringToBinary(entropys);
		int min = 0;
		int max = 11;
		for (int i = 0; i < 12; i++) {
			arr_entropy.add(entropy.substring(min, max));
			min += 11;
			max += 11;
			decimalValue.add(Integer.parseInt(arr_entropy.get(i), 2));
			seed_words.add(wordslist.get(decimalValue.get(i)));
		}
		return seed_words;
	}

	public static String convertStringToBinary(String input) {

		StringBuilder result = new StringBuilder();
		char[] chars = input.toCharArray();
		for (char aChar : chars) {
			result.append(
					String.format("%8s", Integer.toBinaryString(aChar))   // char -> int, auto-cast
							.replaceAll(" ", "0")                         // zero pads
			);
		}
		return result.toString();

	}

//	/**
//	 * Test key generation, sign and verify.
//	 *
//	 * @param args The private key.
//	 * @return Nothing.
//	 */
//	public static void main(String[] args) { //throws NoSuchAlgorithmException {
//		SecP256K1KeyGenerator ss = new SecP256K1KeyGenerator();
//		KeyPair gg = ss.generateKeyPair();
//		PrivateKey privk = gg.getPrivateKey();
//		PublicKey pubk = ss.derivePublicKey(privk);
//
//		String aa = "This is a simple message";
//
//		SecP256K1DsaSigner sign = new SecP256K1DsaSigner(gg);
//
//		Signature signature = sign.sign(aa.getBytes());
//
//		boolean pp = sign.verify(aa.getBytes(), signature);
//		boolean pf = sign.verify("This is not the simple message".getBytes(), signature);
//
//	}
}
