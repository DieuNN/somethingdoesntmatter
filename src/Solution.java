import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count += 1;
                    callDFS(grid, i, j);
                }
            }
        }
        return count;
    }

    private void callDFS(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        callDFS(grid, i - 1, j);
        callDFS(grid, i + 1, j);
        callDFS(grid, i, j - 1);
        callDFS(grid, i, j + 1);
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if (numRows == 0) return triangle;
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        triangle.add(firstRow);
        for (int i = 1; i < numRows; i++) {
            List<Integer> prevRow = triangle.get(i - 1);
            List<Integer> row = new ArrayList<>();

            row.add(1);
            for (int j = 1; j < i; j++) {
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            row.add(1);
            triangle.add(row);
        }
        return triangle;
    }

    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) return true;
        }
        return false;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public int[] twoSum(int[] numbers, int target) {
        int aPointer = 0;
        int bPointer = numbers.length - 1;

        while (aPointer <= bPointer) {
            int sum = numbers[aPointer] + numbers[bPointer];

            if (sum < target) {
                bPointer -= 1;
            } else if (sum > target) {
                aPointer += 1;
            } else {
                return new int[]{aPointer, bPointer};
            }
        }
        return new int[]{aPointer, bPointer};
    }

    public int reverse(int x) {
        int reversed = 0;
        int pop;

        while (x != 0) {
            pop = x % 10;
            x /= 10;

            if (reversed > Integer.MAX_VALUE / 10 || reversed == Integer.MAX_VALUE / 10 && pop > 7) {
                return 0;
            }
            if (reversed < Integer.MAX_VALUE / 10 || reversed == Integer.MAX_VALUE / 10 && pop < -8) {
                return 0;
            }

            reversed = (reversed * 10) + pop;


        }
        return reversed;
    }

    public String greatestLetter(String s) {
        char[] chars = s.toCharArray();
        List<Character> list = new ArrayList<>();

        for (char c : chars) {
            list.add(c);
        }
        int max = 32;
        int current = 0;

        for (Character c : list) {
            if (Character.isUpperCase(c) && list.contains(Character.toLowerCase(c))) {
                current = (int) Character.toUpperCase(c);
                max = Math.max(current, max);
            } else if (Character.isLowerCase(c) && list.contains(Character.toUpperCase(c))) {
                current = (int) Character.toLowerCase(c);
                max = Math.max(current, max);
            }
        }
        return Character.toString(max).toUpperCase().trim();
    }

    public int divide(int dividend, int divisor) {
        int result = 1;
        if (dividend == divisor) return 1;
        if (dividend == -divisor) return -1;
        int sub = Math.abs(dividend) - Math.abs(divisor);
        while (sub > 0) {
            if (dividend > 0) {
                if (sub - 2 * divisor >= 0) {
                    result += 2;
                } else {
                    result++;
                }
                dividend -= Math.abs(divisor);
            } else {
                if (sub - 2 * divisor >= 0) {
                    result += 2;
                } else {
                    result++;
                }

                dividend += Math.abs(divisor);
            }
            sub = Math.abs(dividend) - Math.abs(divisor);
        }
        if (dividend * divisor < 0) result = -result;
        return result;
    }

    public int threeSumMulti(int[] arr, int target) {
        int mod = (int) Math.pow(10, 9) + 7;
        long result = 0;
        for (int i = 0; i < arr.length; i++) {
            int[] count = new int[101];
            for (int j = i + 1; j < arr.length; j++) {
                int k = target - arr[i] - arr[j];
                if (k >= 0 && k <= 100 && count[k] > 0) {
                    result += count[k];
                    result %= mod;
                }
                count[arr[j]]++;
            }
        }
        return (int) result;
    }

    public void sortColors(int[] nums) {
        Arrays.sort(nums);
    }

    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        long sum = 0;
        for (int i = (int) Math.round(n * 0.05); i < n - 0.05 * n; i++) {
            sum += arr[i];
        }
        return sum / (n - n * 0.1);

    }

    public int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = (int) Math.pow(Math.abs(nums[i]), 2);
        }
        Arrays.sort(result);
        return result;
    }

    public int maxArea(int[] height) {
        int maxArea = 0;
        int aPointer = 0;
        int bPointer = height.length - 1;
        while (aPointer < bPointer) {
            if (height[aPointer] < height[bPointer]) {
//                maxArea = Math.max(maxArea, height[aPointer]) * (bPointer - aPointer);
                int currentArea = height[aPointer] * (bPointer - aPointer);
                maxArea = Math.max(currentArea, maxArea);
                aPointer++;
            } else {
                int currentArea = height[bPointer] * (bPointer - aPointer);
                maxArea = Math.max(currentArea, maxArea);
                bPointer--;
            }
        }
        return maxArea;
    }

    public boolean isNStraightHand(int[] hand, int groupSize) {
        TreeMap<Integer, Integer> cardCount = new TreeMap<>();
        for (int card : hand) {
            if (!cardCount.containsKey(card)) {
                cardCount.put(card, 1);
            } else {
                cardCount.replace(card, cardCount.get(card) + 1);
            }
        }

        while (cardCount.size() > 0) {
            int firstCard = cardCount.firstKey();
            for (int i = firstCard; i < firstCard + groupSize; i++) {
                if (!cardCount.containsKey(i)) return false;
                int count = cardCount.get(i);
                if (count == 1) {
                    cardCount.remove(i);
                } else {
                    cardCount.replace(i, cardCount.get(i) - 1);
                }
            }
        }
        return true;
    }

    public int numJewelsInStones(String jewels, String stones) {
        int result = 0;
        for (int i = 0; i < stones.length(); i++) {
            if (jewels.indexOf(stones.charAt(i)) > -1) {
                result++;
            }
        }
        return result;
    }

    public int peakIndexInMountainArray(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) return i;
        }
        return 0;
    }

    public String reverseOnlyLetters(String s) {
        Stack<Character> letters = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                letters.push(s.charAt(i));
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                stringBuilder.append(letters.pop());
            } else {
                stringBuilder.append(s.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    public int heightChecker(int[] heights) {
        int[] sortedArray = Arrays.copyOf(heights, heights.length);


        for (int i = 0; i < sortedArray.length; i++) {
            for (int j = 0; j < sortedArray.length - i - 1; j++) {
                if (sortedArray[j] > sortedArray[j + 1]) {
                    int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(sortedArray));


        int count = 0;
        for (int i = 0; i < sortedArray.length; i++) {
            if (heights[i] != sortedArray[i]) {
                count++;
            }
        }
        return count;

    }

    public int bitwiseComplement(int n) {
        String bitString = Integer.toBinaryString(n);
        char[] bits = bitString.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] == '0') {
                bits[i] = '1';
            } else {
                bits[i] = '0';
            }
            stringBuilder.append(bits[i]);
        }
        return Integer.valueOf(stringBuilder.toString(), 2);
    }

    public boolean canThreePartsEqualSum(int[] arr) {
        int total = 0;
        for (int element : arr) {
            total += element;
        }

        if (total % 3 != 0) return false;

        int average = total / 3;
        int result = 0;
        int currentSum = 0;

        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            if (currentSum == average) {
                currentSum = 0;
                result++;
            }
        }

        return result >= 3;

    }

    public int maxScore(String s) {
        int zeros = 0, ones = 0, max = Integer.MIN_VALUE;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ones++;
            } else {
                zeros++;
            }
            if (i != s.length() - 1) {
                max = Math.max(zeros - ones, max);
            }
        }

        return max + ones;

    }

    public int[][] construct2DArray(int[] original, int m, int n) {
        if (m * n != original.length) return new int[][]{};
        int[][] result = new int[n][m];

        int index = 0;
        while (index < original.length) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    result[i][j] = original[index];
                    index++;
                }
            }

        }
        return result;
    }

    public boolean areNumbersAscending(String s) {
        StringTokenizer tokenizer = new StringTokenizer(s);
        String[] split = s.split(" ");
        List<Integer> numbers = new ArrayList<>();

        for (String element : split) {
            System.out.println(element);
            try {
                numbers.add(Integer.parseInt(element));
            } catch (Exception ignored) {

            }
        }

        System.out.println(numbers);
        boolean isIncreasing = true;
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) >= numbers.get(i + 1)) {
                isIncreasing = false;
                break;
            }
        }
        return isIncreasing;
    }

    public List<String> commonChars(String[] words) {
        List<String> commonChar = new ArrayList<>();

        int[] minFrequencies = new int[26];
        Arrays.fill(minFrequencies, Integer.MAX_VALUE);

        for (String element : words) {
            int[] charFrequencies = new int[26];
            for (char c : element.toCharArray()) {
                charFrequencies[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                minFrequencies[i] = Math.min(minFrequencies[i], charFrequencies[i]);
            }
        }

        for (int i = 0; i < 26; i++) {
            while (minFrequencies[i] > 0) {
                commonChar.add("" + (char) (i + 'a'));
                minFrequencies[i]--;
            }
        }
        return commonChar;
    }

    public int numberOfSteps(int num) {
        int count = 0;
        while (num != 0) {
            if (num % 2 == 0) {
                num = num / 2;
                count++;
            } else {
                num--;
                count++;
            }
        }
        return count;
    }

    public int titleToNumber(String s) {
        if (s == null) return -1;
        int sum = 0;
        for (char c : s.toUpperCase().toCharArray()) {
            sum *= 26;
            sum += c - 'A' + 1;
        }
        return sum;
    }

    public int maximumDifference(int[] nums) {
        int diff = -1;
        for (int i = 1, min = nums[0]; i < nums.length; ++i) {
            if (nums[i] > min) {
                diff = Math.max(diff, nums[i] - min);
            }
            min = Math.min(min, nums[i]);
        }
        return diff;
    }

    public int tribonacci(int n) {
        int[] arr = new int[38];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 1;


        for (int i = 3; i < 38; i++) {
            arr[i] = arr[i - 1] + arr[i - 2] + arr[i + 3];
        }

        return arr[n];

    }

    public int findCenter(int[][] edges) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < edges[i].length; j++) {
                row.add(edges[i][j]);
            }
            list.add(row);
        }
        boolean shouldGetFirst = true;
        int result = 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(list.get(0).get(0))) {
                shouldGetFirst = true;
            } else if (list.get(i).contains(list.get(0).get(1))) {
                shouldGetFirst = false;
            }
        }

        return shouldGetFirst ? list.get(0).get(0) : list.get(0).get(1);
    }

    private Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }
        return counts;
    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> counts = countNums(nums);

        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }

        return majorityEntry.getKey();
    }

    public int numDifferentIntegers(String word) {
        char[] chars = word.toCharArray();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            if (!Character.isDigit(chars[i])) {
                chars[i] = ' ';
            }
            stringBuilder.append(chars[i]);
        }

        Set<BigDecimal> set = new HashSet<>();

        StringTokenizer tokenizer = new StringTokenizer(stringBuilder.toString());

        while (tokenizer.hasMoreTokens()) {
            set.add(new BigDecimal(tokenizer.nextToken()));
        }

        return set.size();

    }

    public int countCharacters(String[] words, String chars) {
        int cnt[] = new int[26], ans = 0;
        chars.chars().forEach(c -> ++cnt[c - 'a']); // count chars.
        outer:
        for (String w : words) {
            int[] count = cnt.clone();
            for (char c : w.toCharArray())
                if (--count[c - 'a'] < 0) // not enough, continue next word in words.
                    continue outer;
            ans += w.length();
        }
        return ans;
    }

    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        return Arrays.equals(target, arr);
    }

    public boolean isPalindrome(int x) {
        int rev_num = 0;
        int clone = x;
        while (x > 0) {
            rev_num = rev_num * 10 + x % 10;
            x = x / 10;
        }

        return clone == rev_num;
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> dictionary = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (!String.valueOf(i).contains("0")) {
                if (isSelfDividingNumber(i)) {
                    dictionary.add(i);
                }
            }
        }
        return dictionary;
    }

    public boolean isSelfDividingNumber(int number) {
        char[] arr = String.valueOf(number).toCharArray();
        for (char c : arr) {
            if (number % Integer.parseInt(c + "") != 0) {
                return false;
            }
        }
        return true;
    }

    public int[] sortArrayByParity(int[] nums) {
        int[] result = new int[nums.length];
        int counter = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                result[counter] = num;
                counter++;
            }
        }

        for (int num : nums) {
            if (num % 2 != 0) {
                result[counter] = num;
                counter++;
            }
        }

        return result;


    }

    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int num : nums) {
            if (!hashMap.containsKey(num)) {
                hashMap.put(num, 1);
            } else {
                hashMap.put(num, hashMap.get(num) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> value : hashMap.entrySet()) {
            if (value.getValue() == 1) return value.getKey();
        }

        return 0;
    }

    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int[] result = new int[queries.length];
        int sum = 0;
        for (int element : nums) {
            if (element % 2 == 0) {
                sum += element;
            }
        }

        int counter = 0;

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][1];
            int value = queries[i][0];


            if (nums[index] % 2 == 0) {
                sum -= nums[index];
            }
            nums[index] += value;
            if (nums[index] % 2 == 0) {
                sum += nums[index];
            }

            result[i] = sum;
        }

        return result;
    }

    public int[] sortArrayByParityII(int[] nums) {
        int[] result = new int[nums.length];
        int odd = 1;
        int even = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                result[even] = nums[i];
                even += 2;
            } else {
                result[odd] = nums[i];
                odd += 2;
            }
        }
        return result;
    }

    public int maxProfit(int[] prices) {
        int minValue = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minValue) {
                minValue = price;
            } else if (price - minValue > maxProfit) {
                maxProfit = price - minValue;
            }
        }
        return maxProfit;
    }

    public boolean validPalindrome(String s) {
        char[] arr = s.toCharArray();
        int aPointer = 0;
        int bPointer = arr.length - 1;

        while (aPointer < bPointer) {
            if (arr[aPointer] != arr[bPointer]) {
                return validPalindromeHelper(s, aPointer + 1, bPointer) || validPalindromeHelper(s, aPointer, bPointer - 1);
            }
            aPointer++;
            bPointer--;
        }

        return true;
    }

    public boolean validPalindromeHelper(String s, int i, int j) {
        int aPointer = i;
        int bPointer = j;

        while (aPointer < bPointer) {
            if (s.charAt(aPointer) != s.charAt(bPointer)) {
                return false;
            }
            aPointer++;
            bPointer--;
        }

        return true;

    }

    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == target) return true;
            }
        }

        return false;
    }



}