//
//

package com.kurdcoin.core;
//


/**
 * Object class structure of blocks .
 */
public class Block {
    // private static final long serialVersionUID = 1111L;

    // Fields defined as part of the protocol format.
    private long version;
//    private Sha256Hash prevBlockHash;
//    private Sha256Hash merkleRoot, witnessRoot;
    private long time;
    private long difficultyTarget; // "nBits"
    private long nonce;
    private String datas;

//    // If null, it means this object holds only the headers.
//    @VisibleForTesting
//    @Nullable List<Transaction.java> transactions;
//
//    /** Stores the hash of the block. If null, getHash() will recalculate it. */
//    private Sha256Hash hash;
//

    /**
     * Calculates the block hash by serializing the block and hashing the
     * resulting bytes.
     */
//    private Sha256Hash calculateHash() {
//        try {
//            ByteArrayOutputStream bos = new UnsafeByteArrayOutputStream(HEADER_SIZE);
//            writeHeader(bos);
//            return Sha256Hash.wrapReversed(Sha256Hash.hashTwice(bos.toByteArray()));
//        } catch (IOException e) {
//            throw new RuntimeException(e); // Cannot happen.
//        }
//    }
//
//    /**
//     * Returns the hash of the block (which for a valid, solved block should be below the target) in the form seen on
//     * the block explorer. If you call this on block 1 in the mainnet chain
//     * you will get "00000000839a8e6886ab5951d76f411475428afc90947ee320161bbf18eb6048".
//     */
//    public String getHashAsString() {
//        return getHash().toString();
//    }
//
//    /**
//     * Returns the hash of the block (which for a valid, solved block should be
//     * below the target). Big endian.
//     */
//    @Override
//    public Sha256Hash getHash() {
//        if (hash == null)
//            hash = calculateHash();
//        return hash;
//    }
//
//    /**
//     * Returns a multi-line string containing a description of the contents of
//     * the block. Use for debugging purposes only.
//     */
//    @Override
//    public String toString() {
//        StringBuilder s = new StringBuilder();
//        s.append(" block: \n");
//        s.append("   hash: ").append(getHashAsString()).append('\n');
//        s.append("   version: ").append(version);
//        String bips = Joiner.on(", ").skipNulls().join(isBIP34() ? "BIP34" : null, isBIP66() ? "BIP66" : null,
//                isBIP65() ? "BIP65" : null);
//        if (!bips.isEmpty())
//            s.append(" (").append(bips).append(')');
//        s.append('\n');
//        s.append("   previous block: ").append(getPrevBlockHash()).append("\n");
//        s.append("   time: ").append(time).append(" (").append(Utils.dateTimeFormat(time * 1000)).append(")\n");
//        s.append("   difficulty target (nBits): ").append(difficultyTarget).append("\n");
//        s.append("   nonce: ").append(nonce).append("\n");
//        if (transactions != null && transactions.size() > 0) {
//            s.append("   merkle root: ").append(getMerkleRoot()).append("\n");
//            s.append("   witness root: ").append(getWitnessRoot()).append("\n");
//            s.append("   with ").append(transactions.size()).append(" transaction(s):\n");
//            for (Transaction.java tx : transactions) {
//                s.append(tx).append('\n');
//            }
//        }
//        return s.toString();
//    }
//
//
//    private void checkMerkleRoot() throws VerificationException {
//        Sha256Hash calculatedRoot = calculateMerkleRoot();
//        if (!calculatedRoot.equals(merkleRoot)) {
//            log.error("Merkle tree did not verify");
//            throw new VerificationException("Merkle hashes do not match: " + calculatedRoot + " vs " + merkleRoot);
//        }
//    }
//
//
//    private List<byte[]> buildMerkleTree(boolean useWTxId) {
//        // The Merkle root is based on a tree of hashes calculated from the transactions:
//        //
//        //     root
//        //      / \
//        //   A      B
//        //  / \    / \
//        // t1 t2 t3 t4
//        //
//        // The tree is represented as a list: t1,t2,t3,t4,A,B,root where each
//        // entry is a hash.
//        //
//        // The hashing algorithm is double SHA-256. The leaves are a hash of the serialized contents of the transaction.
//        // The interior nodes are hashes of the concatenation of the two child hashes.
//        //
//        // This structure allows the creation of proof that a transaction was included into a block without having to
//        // provide the full block contents. Instead, you can provide only a Merkle branch. For example to prove tx2 was
//        // in a block you can just provide tx2, the hash(tx1) and B. Now the other party has everything they need to
//        // derive the root, which can be checked against the block header. These proofs aren't used right now but
//        // will be helpful later when we want to download partial block contents.
//        //
//        // Note that if the number of transactions is not even the last tx is repeated to make it so (see
//        // tx3 above). A tree with 5 transactions would look like this:
//        //
//        //         root
//        //        /     \
//        //       1        5
//        //     /   \     / \
//        //    2     3    4  4
//        //  / \   / \   / \
//        // t1 t2 t3 t4 t5 t5
//        ArrayList<byte[]> tree = new ArrayList<>(transactions.size());
//        // Start by adding all the hashes of the transactions as leaves of the tree.
//        for (Transaction.java tx : transactions) {
//            final Sha256Hash id;
//            if (useWTxId && tx.isCoinBase())
//                id = Sha256Hash.ZERO_HASH;
//            else
//                id = useWTxId ? tx.getWTxId() : tx.getTxId();
//            tree.add(id.getBytes());
//        }
//        int levelOffset = 0; // Offset in the list where the currently processed level starts.
//        // Step through each level, stopping when we reach the root (levelSize == 1).
//        for (int levelSize = transactions.size(); levelSize > 1; levelSize = (levelSize + 1) / 2) {
//            // For each pair of nodes on that level:
//            for (int left = 0; left < levelSize; left += 2) {
//                // The right hand node can be the same as the left hand, in the case where we don't have enough
//                // transactions.
//                int right = Math.min(left + 1, levelSize - 1);
//                byte[] leftBytes = Utils.reverseBytes(tree.get(levelOffset + left));
//                byte[] rightBytes = Utils.reverseBytes(tree.get(levelOffset + right));
//                tree.add(Utils.reverseBytes(hashTwice(leftBytes, rightBytes)));
//            }
//            // Move to the next level.
//            levelOffset += levelSize;
//        }
//        return tree;
//    }
//
//
//
//    @Override
//    public int hashCode() {
//        return getHash().hashCode();
//    }
//
//    /**
//     * Returns the merkle root in big endian form, calculating it from transactions if necessary.
//     */
//    public Sha256Hash getMerkleRoot() {
//        if (merkleRoot == null) {
//            //TODO check if this is really necessary.
//            unCacheHeader();
//            merkleRoot = calculateMerkleRoot();
//        }
//        return merkleRoot;
//    }
//
//    /** Exists only for unit testing. */
//    @VisibleForTesting
//    void setMerkleRoot(Sha256Hash value) {
//        unCacheHeader();
//        merkleRoot = value;
//        hash = null;
//    }
//
//    /**
//     * Returns the witness root in big endian form, calculating it from transactions if necessary.
//     */
//    public Sha256Hash getWitnessRoot() {
//        if (witnessRoot == null)
//            witnessRoot = calculateWitnessRoot();
//        return witnessRoot;
//    }
//
//    /** Adds a transaction to this block. The nonce and merkle root are invalid after this. */
//    public void addTransaction(Transaction.java t) {
//        addTransaction(t, true);
//    }
//
//    /** Adds a transaction to this block, with or without checking the sanity of doing so */
//    void addTransaction(Transaction.java t, boolean runSanityChecks) {
//        unCacheTransactions();
//        if (transactions == null) {
//            transactions = new ArrayList<>();
//        }
//        t.setParent(this);
//        if (runSanityChecks && transactions.size() == 0 && !t.isCoinBase())
//            throw new RuntimeException("Attempted to add a non-coinbase transaction as the first transaction: " + t);
//        else if (runSanityChecks && transactions.size() > 0 && t.isCoinBase())
//            throw new RuntimeException("Attempted to add a coinbase transaction when there already is one: " + t);
//        transactions.add(t);
//        adjustLength(transactions.size(), t.length);
//        // Force a recalculation next time the values are needed.
//        merkleRoot = null;
//        hash = null;
//    }
//
//    /** Returns an immutable list of transactions held in this block, or null if this object represents just a header. */
//    @Nullable
//    public List<Transaction.java> getTransactions() {
//        return transactions == null ? null : ImmutableList.copyOf(transactions);
//    }
//
//    // ///////////////////////////////////////////////////////////////////////////////////////////////
//    // Unit testing related methods.
//
//    // Used to make transactions unique.
//    private static int txCounter;
//
//    /** Adds a coinbase transaction to the block. This exists for unit tests.
//     *
//     * @param height block height, if known, or -1 otherwise.
//     */
//    @VisibleForTesting
//    void addCoinbaseTransaction(byte[] pubKeyTo, Coin value, final int height) {
//        unCacheTransactions();
//        transactions = new ArrayList<>();
//        Transaction.java coinbase = new Transaction.java(params);
//        final ScriptBuilder inputBuilder = new ScriptBuilder();
//
//        if (height >= Block.BLOCK_HEIGHT_GENESIS) {
//            inputBuilder.number(height);
//        }
//        inputBuilder.data(new byte[]{(byte) txCounter, (byte) (txCounter++ >> 8)});
//
//        // A real coinbase transaction has some stuff in the scriptSig like the extraNonce and difficulty. The
//        // transactions are distinguished by every TX output going to a different key.
//        //
//        // Here we will do things a bit differently so a new address isn't needed every time. We'll put a simple
//        // counter in the scriptSig so every transaction has a different hash.
//        coinbase.addInput(new TransactionInput(params, coinbase,
//                inputBuilder.build().getProgram()));
//        coinbase.addOutput(new TransactionOutput(params, coinbase, value,
//                ScriptBuilder.createP2PKOutputScript(ECKey.fromPublicOnly(pubKeyTo)).getProgram()));
//        transactions.add(coinbase);
//        coinbase.setParent(this);
//        coinbase.length = coinbase.unsafeBitcoinSerialize().length;
//        adjustLength(transactions.size(), coinbase.length);
//    }
//
//    private static final byte[] EMPTY_BYTES = new byte[32];
//
//    // It's pretty weak to have this around at runtime: fix later.
//    private static final byte[] pubkeyForTesting = new ECKey().getPubKey();
//
//    /**
//     * Returns a solved block that builds on top of this one. This exists for unit tests.
//     */
//    @VisibleForTesting
//    public Block createNextBlock(Address to, long version, long time, int blockHeight) {
//        return createNextBlock(to, version, null, time, pubkeyForTesting, FIFTY_COINS, blockHeight);
//    }
//
//    /**
//     * Returns a solved block that builds on top of this one. This exists for unit tests.
//     * In this variant you can specify a public key (pubkey) for use in generating coinbase blocks.
//     *
//     * @param height block height, if known, or -1 otherwise.
//     */
//    @VisibleForTesting
//    Block createNextBlock(@Nullable final Address to, final long version,
//                          @Nullable TransactionOutPoint prevOut, final long time,
//                          final byte[] pubKey, final Coin coinbaseValue,
//                          final int height) {
//        Block b = new Block(params, version);
//        b.setDifficultyTarget(difficultyTarget);
//        b.addCoinbaseTransaction(pubKey, coinbaseValue, height);
//
//        if (to != null) {
//            // Add a transaction paying 50 coins to the "to" address.
//            Transaction.java t = new Transaction.java(params);
//            t.addOutput(new TransactionOutput(params, t, FIFTY_COINS, to));
//            // The input does not really need to be a valid signature, as long as it has the right general form.
//            TransactionInput input;
//            if (prevOut == null) {
//                input = new TransactionInput(params, t, Script.createInputScript(EMPTY_BYTES, EMPTY_BYTES));
//                // Importantly the outpoint hash cannot be zero as that's how we detect a coinbase transaction in isolation
//                // but it must be unique to avoid 'different' transactions looking the same.
//                byte[] counter = new byte[32];
//                counter[0] = (byte) txCounter;
//                counter[1] = (byte) (txCounter++ >> 8);
//                input.getOutpoint().setHash(Sha256Hash.wrap(counter));
//            } else {
//                input = new TransactionInput(params, t, Script.createInputScript(EMPTY_BYTES, EMPTY_BYTES), prevOut);
//            }
//            t.addInput(input);
//            b.addTransaction(t);
//        }
//
//        b.setPrevBlockHash(getHash());
//        // Don't let timestamp go backwards
//        if (getTimeSeconds() >= time)
//            b.setTime(getTimeSeconds() + 1);
//        else
//            b.setTime(time);
//        b.solve();
//        try {
//            b.verifyHeader();
//        } catch (VerificationException e) {
//            throw new RuntimeException(e); // Cannot happen.
//        }
//        if (b.getVersion() != version) {
//            throw new RuntimeException();
//        }
//        return b;
//    }
//
//    @VisibleForTesting
//    public Block createNextBlock(@Nullable Address to, TransactionOutPoint prevOut) {
//        return createNextBlock(to, BLOCK_VERSION_GENESIS, prevOut, getTimeSeconds() + 5, pubkeyForTesting, FIFTY_COINS, BLOCK_HEIGHT_UNKNOWN);
//    }
//
//    @VisibleForTesting
//    public Block createNextBlock(@Nullable Address to, Coin value) {
//        return createNextBlock(to, BLOCK_VERSION_GENESIS, null, getTimeSeconds() + 5, pubkeyForTesting, value, BLOCK_HEIGHT_UNKNOWN);
//    }
//
//    @VisibleForTesting
//    public Block createNextBlock(@Nullable Address to) {
//        return createNextBlock(to, FIFTY_COINS);
//    }
//
//    @VisibleForTesting
//    public Block createNextBlockWithCoinbase(long version, byte[] pubKey, Coin coinbaseValue, final int height) {
//        return createNextBlock(null, version, (TransactionOutPoint) null,
//                Utils.currentTimeSeconds(), pubKey, coinbaseValue, height);
//    }
//
//    /**
//     * Create a block sending 50BTC as a coinbase transaction to the public key specified.
//     * This method is intended for test use only.
//     */
//    @VisibleForTesting
//    Block createNextBlockWithCoinbase(long version, byte[] pubKey, final int height) {
//        return createNextBlock(null, version, (TransactionOutPoint) null,
//                Utils.currentTimeSeconds(), pubKey, FIFTY_COINS, height);
//    }
//
//    public static byte[] calcHash(Block block) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        byte[] hashBytes = md.digest(toString(block).getBytes(StandardCharsets.UTF_8));
//        return hashBytes;
//    }
//
//    public static String toString(Block block) {
//        return Integer.toString(block.getIndex()) + block.getCurrentHash() + block.getPreviousHash()
//                + block.getTimestamp() + block.getDatas() + Integer.toString(block.getDifficulty()) + Integer.toString(block.getNonce());
//    }
//
//    public static String toStringHash(Block block) throws NoSuchAlgorithmException {
//        byte[] hasher = Block.calcHash(block);
//        String a = bytesToHex(hasher);
//
//        return a;
//    }


}