package com.kurdcoin.crypto.secp256k1;

import com.kurdcoin.crypto.*;

/**
 * Implementation of the key analyzer for SECP256K1.
 */
public class SecP256K1KeyAnalyzer implements KeyAnalyzer {

	private static final int COMPRESSED_KEY_SIZE = 33;

	@Override
	public boolean isKeyCompressed(final PublicKey publicKey) {
		if (COMPRESSED_KEY_SIZE != publicKey.getRaw().length) {
			return false;
		}

		switch (publicKey.getRaw()[0]) {
			case 0x02:
			case 0x03:
				return true;
		}

		return false;
	}
}
