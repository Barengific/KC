package com.kurdcoin.crypto.secp256k1;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.math.ec.ECPoint;
import com.kurdcoin.crypto.*;

import java.security.SecureRandom;

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

	/**
	 * Test key generation, sign and verify.
	 *
	 * @param args The private key.
	 * @return Nothing.
	 */
	public static void main(String[] args) { //throws NoSuchAlgorithmException {
		SecP256K1KeyGenerator ss = new SecP256K1KeyGenerator();
		KeyPair gg = ss.generateKeyPair();
		PrivateKey privk = gg.getPrivateKey();
		PublicKey pubk = ss.derivePublicKey(privk);

		String aa = "This is a simple message";

		SecP256K1DsaSigner sign = new SecP256K1DsaSigner(gg);

		Signature signature = sign.sign(aa.getBytes());

		boolean pp = sign.verify(aa.getBytes(), signature);
		boolean pf = sign.verify("This is not the simple message".getBytes(), signature);


	}
}
