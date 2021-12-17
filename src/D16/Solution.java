package D16;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import Utils.Benchmark;
import Utils.Input;

public class Solution {
    public int part1() {
        var input = Input.getAsStringList(this);
        var binary = convertToBinaryString(input.get(0));
        var packets = processPacket(binary, Integer.MAX_VALUE);
        return getVersionSum(packets);
    }

    public int getVersionSum(List<Packet> packets) {
        int sum = 0;
        for (Packet packet : packets) {
            sum += packet.version + getVersionSum(packet.subpackets);
        }
        return sum;
    }
    
    int numPacketsReachedI = -1;

    public List<Packet> processPacket(String binary, int numPackets) {
        var packets = new ArrayList<Packet>();
        int i = 0;
        while (i != binary.length() && numPackets > 0) {
            if (binary.length() - i < 11) { // smallest valid packet size is a literal with length 11
                break;
            }
            Packet packet = new Packet(Integer.parseInt(binary, i, i += 3, 2), Integer.parseInt(binary, i, i += 3, 2));
            if (packet.type == Type.Literal) { 
                String literalBinary = "";
                boolean keepGoing = true;
                while (keepGoing) {
                    if (Integer.parseInt(binary, i, i += 1, 2) == 0) {
                        keepGoing = false;
                    }
                    literalBinary += binary.substring(i, i += 4);
                }
                packet.data = Long.parseLong(literalBinary, 2);
            }
            else {
                int lengthType = Integer.parseInt(binary, i, i += 1, 2);
                if (lengthType == 0) {
                    int totalLength = Integer.parseInt(binary, i, i += 15, 2);
                    String subPackets = binary.substring(i, i += totalLength);
                    packet.subpackets = processPacket(subPackets, Integer.MAX_VALUE);
                }
                else {
                    int numSubPackets = Integer.parseInt(binary, i, i += 11, 2);
                    String subPackets = binary.substring(i, binary.length());
                    packet.subpackets = processPacket(subPackets, numSubPackets);
                    if (numPacketsReachedI != -1) {
                        i += numPacketsReachedI; 
                    }
                    //else {
                    //    i = binary.length();
                    //}
                }
            }
            packets.add(packet);
            numPackets--;
        }
        if (numPackets == 0) {
            numPacketsReachedI = i;
        }
        //else {
        //    numPacketsReachedI = -1;
        //}
        return packets;
    }

    public String convertToBinaryString(String line) {
        var hexToBinary = new HashMap<Character, String>();
        hexToBinary.put('0', "0000");
        hexToBinary.put('1', "0001");
        hexToBinary.put('2', "0010");
        hexToBinary.put('3', "0011");
        hexToBinary.put('4', "0100");
        hexToBinary.put('5', "0101");
        hexToBinary.put('6', "0110");
        hexToBinary.put('7', "0111");
        hexToBinary.put('8', "1000");
        hexToBinary.put('9', "1001");
        hexToBinary.put('A', "1010");
        hexToBinary.put('B', "1011");
        hexToBinary.put('C', "1100");
        hexToBinary.put('D', "1101");
        hexToBinary.put('E', "1110");
        hexToBinary.put('F', "1111");

        var binaryLine = "";

        for (int i = 0; i < line.length(); i++) {
            binaryLine += hexToBinary.get(line.charAt(i));
        }

        return binaryLine;
    }

    public List<BigInteger> getValueSum(List<Packet> packets) {
        var parts = new ArrayList<BigInteger>();
        for (Packet packet : packets) {
            switch (packet.type) {
                case Sum: 
                    var sumParts = getValueSum(packet.subpackets);
                    parts.add(sumParts.stream().reduce(BigInteger.valueOf(0), (a, b) -> a.add(b)));
                    break;
                case Product: 
                    var productParts = getValueSum(packet.subpackets);
                    parts.add(productParts.stream().reduce(BigInteger.valueOf(1), (a, b) -> a.multiply(b)));
                    break;
                case Minimum: 
                    var minimumParts = getValueSum(packet.subpackets);
                    parts.add(Collections.min(minimumParts));
                    break;
                case Maximum: 
                    var maximumParts = getValueSum(packet.subpackets);
                    parts.add(Collections.max(maximumParts));
                    break;
                case Literal: 
                    parts.add(BigInteger.valueOf(packet.data));
                    break;
                case GreaterThan: 
                    var greaterThanParts = getValueSum(packet.subpackets);
                    if (greaterThanParts.get(0).compareTo(greaterThanParts.get(1)) == 1) {
                        parts.add(BigInteger.valueOf(1));
                    }
                    else {
                        parts.add(BigInteger.valueOf(0));
                    }
                    break;
                case LessThan: 
                    var lessThanParts = getValueSum(packet.subpackets);
                    if (lessThanParts.get(0).compareTo(lessThanParts.get(1)) == -1) {
                        parts.add(BigInteger.valueOf(1));
                    }
                    else {
                        parts.add(BigInteger.valueOf(0));
                    }
                    break;
                case EqualTo: 
                    var equalParts = getValueSum(packet.subpackets);
                    if (equalParts.get(0).compareTo(equalParts.get(1)) == 0) {
                        parts.add(BigInteger.valueOf(1));
                    }
                    else {
                        parts.add(BigInteger.valueOf(0));
                    }
                    break;
            }
        }
        return parts;
    }
    
    public BigInteger part2() {
        var input = Input.getAsStringList(this);
        var binary = convertToBinaryString(input.get(0));
        var packets = processPacket(binary, Integer.MAX_VALUE);
        return getValueSum(packets).get(0); // 5390807764608 too low
    }

    public static void main(String[] args) {
        var day16 = new Solution();
        Benchmark.Run(() -> day16.part1());
        Benchmark.Run(() -> day16.part2());
    }

    public class Packet {
        public int version;
        public Type type;
        public long data;
        public List<Packet> subpackets = new ArrayList<Packet>();

        public Packet(int version, int type) {
            this.version = version;
            switch (type) {
                case 0: this.type = Type.Sum;           break;
                case 1: this.type = Type.Product;       break;
                case 2: this.type = Type.Minimum;       break;
                case 3: this.type = Type.Maximum;       break;
                case 4: this.type = Type.Literal;       break;
                case 5: this.type = Type.GreaterThan;   break;
                case 6: this.type = Type.LessThan;      break;
                case 7: this.type = Type.EqualTo;       break;
            }
        }
    }

    public enum Type {
        Sum,
        Product,
        Minimum,
        Maximum,
        Literal,
        GreaterThan,
        LessThan,
        EqualTo
    }
}
