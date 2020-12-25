//一头雾水，涉及位操作，脑经急转弯
public class Solution16_01 {
    public int[] swapNumbers(int[] numbers) {
        numbers[0]=numbers[0]+numbers[1];
        numbers[1]=numbers[0]-numbers[1];
        numbers[0]=numbers[0]-numbers[1];
        return numbers;
    }
}