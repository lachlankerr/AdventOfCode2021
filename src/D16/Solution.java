package D16;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Utils.Benchmark;
import Utils.Input;

public class Solution {
    List<Packet> packets = new ArrayList<Packet>();
    
    public int part1() {
        var input = Input.getAsStringList(this);
        var binary = convertToBinaryString(input.get(0));
        processPacket(binary);
        

        return 0;
    }

    public void processPacket(String binary) {
        int i = 0;
        while (i != binary.length()) {
            if (new BigInteger(binary.substring(i, binary.length() - i), 2).signum() == 0) {
            //if (Long.parseLong(binary, i, binary.length() - i, 2) == 0) { // only zeros mean its not a packet
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
                packet.data = Integer.parseInt(literalBinary, 2);
            }
            else {
                int lengthType = Integer.parseInt(binary, i, i += 1, 2);
                if (lengthType == 0) {
                    int lengthBits = 15;
                    int totalLength = Integer.parseInt(binary, i, i += lengthBits, 2);
                    String subPackets = binary.substring(i, i += totalLength);
                    processPacket(subPackets);
                }
                else {
                    int lengthBits = 15;
                    int numSubPackets = Integer.parseInt(binary, i, i += lengthBits, 2); //what is the point of this
                    String subPackets = binary.substring(i, binary.length() - 1);
                    i = binary.length();

                    processPacket(subPackets);
                }
            }
            packets.add(packet);
        }
    }

    /*public int processSinglePacket(String binary) {
        return 0;
    }

    public void processNPackets(String binary, int n) {
        int i = 0;
        for (int j = 0; j < n; j++) {
            i = processSinglePacket(binary.substring(i));
        }
    }*/

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
    
    public int part2() {
        return 0;
    }

    public static void main(String[] args) {
        var day16 = new Solution();
        Benchmark.Run(() -> day16.part1());
        Benchmark.Run(() -> day16.part2());
    }

    public class Packet {
        public int version;
        public Type type;
        public int data;
        public List<Packet> subpackets = new ArrayList<Packet>();

        public Packet(int version, int type) {
            this.version = version;
            switch (type) {
                case 4: this.type = Type.Literal; break;
                default: this.type = Type.Operator; break;
            }
        }

        
    }

    public enum Type {
        Literal(4),
        Operator(-1);

        private int value;    

        private Type(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
