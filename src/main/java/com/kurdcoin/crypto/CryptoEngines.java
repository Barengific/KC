package com.kurdcoin.crypto;

import com.kurdcoin.crypto.secp256k1.SecP256K1CryptoEngine;

/**
 * Static class that exposes crypto engines.
 */
public class CryptoEngines {

	private static final CryptoEngine SECP256K1_ENGINE;
	private static final CryptoEngine DEFAULT_ENGINE;

	static {
		SECP256K1_ENGINE = new SecP256K1CryptoEngine();
		DEFAULT_ENGINE = SECP256K1_ENGINE;
	}

	/**
	 * Gets the default crypto engine.
	 *
	 * @return The default crypto engine.
	 */
	public static CryptoEngine defaultEngine() {
		return DEFAULT_ENGINE;
	}

	/**
	 * Gets the SECP256K1 crypto engine.
	 *
	 * @return The SECP256K1 crypto engine.
	 */
	public static CryptoEngine secp256k1Engine() {
		return SECP256K1_ENGINE;
	}

}
