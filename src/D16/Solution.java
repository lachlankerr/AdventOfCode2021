package D16;

import java.util.HashMap;

import Utils.Benchmark;
import Utils.Input;

public class Solution {
    public int part1() {
        var input = Input.getAsStringList(this);
        var binary = convertToBinaryString(input.get(0));
        int i = 0;
        int version = Integer.parseInt(binary, i, i += 3, 2);
        int type = Integer.parseInt(binary, i, i += 3, 2);
        if (type == 4) { //literal
            // split into chunks of 5 bits
            // first bit of chunk is 1 if there are more chunks afterwards, 0 if none
            // any leftover bits is extra and should be ignored
            // for the remaining 4 bits in each chunk, create new String literalBinary and concat onto that, then convert to decimal
        }
        else {
            int lengthType = Integer.parseInt(binary, i, i += 1, 2);
            if (lengthType == 0) {
                int lengthBits = 15;
                int totalLength = Integer.parseInt(binary, i, i += lengthBits, 2);
                String subPackets = binary.substring(i, i += totalLength);
                // processPacket(subPackets)
                if (i == binary.length()) {

                }
            }
        }
        

        return 0;
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
    
    public int part2() {
        return 0;
    }

    public static void main(String[] args) {
        var day16 = new Solution();
        Benchmark.Run(() -> day16.part1());
        Benchmark.Run(() -> day16.part2());
    }
}
