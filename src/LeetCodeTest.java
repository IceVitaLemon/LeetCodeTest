import java.util.*;

public class LeetCodeTest {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append('a');
        sb.append('b');
        System.out.println(sb.length());
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    /***
     * HashMap的扩容大小，2的幂次方
     * @param cap
     * @return
     */
    public static int toMaxPower(int cap){
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n < 0 ? 1 : n + 1;
    }

//    数值的整数次方
//    实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
//
//             
//
//    示例 1:
//
//    输入: 2.00000, 10
//    输出: 1024.00000
//    示例 2:
//
//    输入: 2.10000, 3
//    输出: 9.26100
//    示例 3:
//
//    输入: 2.00000, -2
//    输出: 0.25000
//    解释: 2-2 = 1/22 = 1/4 = 0.25
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public double myPow(double x, int n) {
        if(n == 0){
            return 1;
        }

        int a = n > 0 ? n/2 : -(n/2);
        double tmp = myPow(x, a);
        tmp = (n & 1) == 1 ? tmp * tmp * x : tmp * tmp;
        return n > 0 ? tmp : 1 / tmp;
    }

//    子集
//    给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
//
//    说明：解集不能包含重复的子集。
//
//    示例:
//
//    输入: nums = [1,2,3]
//    输出:
//            [
//            [3],
//              [1],
//              [2],
//              [1,2,3],
//              [1,3],
//              [2,3],
//              [1,2],
//              []
//            ]
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/subsets
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        subsets(nums, 0, new ArrayList<>(), res);
        return res;
    }

    public void subsets(int[] nums, int pos, List<Integer> tmp, List<List<Integer>> res){
        res.add(new ArrayList<>(tmp));
        for(int i = pos; i < nums.length; ++i){
            tmp.add(nums[i]);
            //这里注意是i + 1，全排列是pos + 1
            subsets(nums, i + 1, tmp, res);
            tmp.remove(tmp.size() - 1);
        }
    }

//    子集2
//    给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
//
//    说明：解集不能包含重复的子集。
//
//    示例:
//
//    输入: [1,2,2]
//    输出:
//            [
//            [2],
//            [1],
//            [1,2,2],
//            [2,2],
//            [1,2],
//            []
//            ]
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/subsets-ii
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDup(nums, 0, new ArrayList<>(), res);
        return res;
    }

    public void subsetsWithDup(int[] nums, int pos, List<Integer> tmp, List<List<Integer>> res){
        res.add(new ArrayList<>(tmp));
        for(int i = pos; i < nums.length; ++i){
            if(i > pos && nums[i] == nums[i - 1]){
                continue;
            }
            tmp.add(nums[i]);
            subsetsWithDup(nums, i + 1, tmp, res);
            tmp.remove(tmp.size() - 1);
        }
    }

//    组合总和
//    给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
//    candidates 中的数字可以无限制重复被选取。
//
//    说明：
//
//    所有数字（包括 target）都是正整数。
//    解集不能包含重复的组合。 
//    示例 1：
//
//    输入：candidates = [2,3,6,7], target = 7,
//    所求解集为：
//            [
//            [7],
//            [2,2,3]
//            ]
//    示例 2：
//
//    输入：candidates = [2,3,5], target = 8,
//    所求解集为：
//            [
//              [2,2,2,2],
//              [2,3,3],
//              [3,5]
//            ]
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/combination-sum
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        combinationSum(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    public void combinationSum(int[] candidates, int pos, int target, List<Integer> tmp, List<List<Integer>> res){
        if(target == 0){
            res.add(new ArrayList<>(tmp));
            return;
        }

        for(int i = pos; i < candidates.length; ++i){
            if(target - candidates[i] < 0){
                continue;
            }
            tmp.add(candidates[i]);
            combinationSum(candidates, i, target - candidates[i], tmp, res);
            tmp.remove(tmp.size() - 1);
        }
    }


//    组合总和 II
//    给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
//    candidates 中的每个数字在每个组合中只能使用一次。
//
//    说明：
//
//    所有数字（包括目标数）都是正整数。
//    解集不能包含重复的组合。 
//    示例 1:
//
//    输入: candidates = [10,1,2,7,6,1,5], target = 8,
//    所求解集为:
//            [
//            [1, 7],
//            [1, 2, 5],
//            [2, 6],
//            [1, 1, 6]
//            ]
//    示例 2:
//
//    输入: candidates = [2,5,2,1,2], target = 5,
//    所求解集为:
//            [
//              [1,2,2],
//              [5]
//            ]
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/combination-sum-ii
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum2(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    public void combinationSum2(int[] candidates, int pos, int target, List<Integer> tmp, List<List<Integer>> res){
        if(target == 0){
            res.add(new ArrayList<>(tmp));
            return;
        }

        for(int i = pos; i < candidates.length; ++i){
            if(target - candidates[i] < 0){
                continue;
            }else if(i > pos && candidates[i] == candidates[i - 1]){
                continue;
            }
            tmp.add(candidates[i]);
            combinationSum2(candidates, i + 1, target - candidates[i], tmp, res);
            tmp.remove(tmp.size() - 1);
        }
    }

    //错排公式 D(n) = (n-1)(D(n-1) + D(n-2));
    //这里是用全排列做
    public int permuteAli(int[] nums, int[] posNums, int index){
        if(index == nums.length){
            return 1;
        }
        int count = 0;
        for(int i = index; i < nums.length; ++i){
            if(nums[i] == posNums[index]){
                continue;
            }
            swap(nums, i, index);
            count += permuteAli(nums, posNums, index + 1);
            swap(nums, i, index);
        }
        return count;
    }

//    全排列1
//    给定一个 没有重复 数字的序列，返回其所有可能的全排列。
//
//    示例:
//
//    输入: [1,2,3]
//    输出:
//            [
//            [1,2,3],
//            [1,3,2],
//            [2,1,3],
//            [2,3,1],
//            [3,1,2],
//            [3,2,1]
//            ]
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permute(nums, 0, new ArrayList<>(), res);
        return res;
    }

    public void permute(int[] nums, int index, List<Integer> tmp, List<List<Integer>> res){
        if(index == nums.length){
            res.add(new ArrayList<>(tmp));
            return;
        }

        for(int i = index; i < nums.length; ++i){
            swap(nums, i, index);
            tmp.add(nums[index]);
            permute(nums, index + 1, tmp, res);
            tmp.remove(index);
            swap(nums, i, index);
        }
    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

//    全排列2
//    给定一个可包含重复数字的序列，返回所有不重复的全排列。
//
//    示例:
//
//    输入: [1,1,2]
//    输出:
//            [
//            [1,1,2],
//            [1,2,1],
//            [2,1,1]
//            ]
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/permutations-ii
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permuteUnique(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void permuteUnique(int[] nums, int index, List<Integer> tmpList, List<List<Integer>> res){
        if(index == nums.length){
            res.add(new ArrayList<Integer>(tmpList));
            return;
        }

        HashSet<Integer> hashSet = new HashSet<>();
        for(int i = index; i < nums.length; ++i){
            if(hashSet.contains(nums[i])){
                continue;
            }
            hashSet.add(nums[i]);

            swap(nums, i, index);
            tmpList.add(nums[index]);
            permuteUnique(nums, index + 1, tmpList, res);
            tmpList.remove(index);
            swap(nums, i, index);
        }
    }

//    字符串的排列
//    输入一个字符串，打印出该字符串中字符的所有排列。
//
//             
//
//    你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
//
//             
//
//    示例:
//
//    输入：s = "abc"
//    输出：["abc","acb","bac","bca","cab","cba"]
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public String[] permutation(String s) {
        if(s == null || s.length() == 0){
            return null;
        }

        char[] chars = s.toCharArray();
        List<String> res = new ArrayList<>();
        permutation(chars, 0, res);
        return res.toArray(new String[0]);
    }

    public void permutation(char[] chars, int pos, List<String> res){
        if(pos == chars.length){
            res.add(new String(chars));
        }
        HashSet<Character> hashSet = new HashSet<>();
        for(int i = pos; i < chars.length; ++i){
            if(hashSet.contains(chars[i])){
                continue;
            }
            hashSet.add(chars[i]);
            swap(chars, i, pos);
            permutation(chars, pos + 1, res);
            swap(chars, i, pos);
        }
    }

    public void swap(char[] chars, int i, int j){
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }


//    字母大小写全排列
//    给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
//
//    示例:
//    输入: S = "a1b2"
//    输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
//
//    输入: S = "3z4"
//    输出: ["3z4", "3Z4"]
//
//    输入: S = "12345"
//    输出: ["12345"]
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/letter-case-permutation
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<String> letterCasePermutation(String S) {
        if(S == null || S.length() == 0){
            return new ArrayList<>();
        }

        char[] chars = S.toCharArray();
        List<String> res = new ArrayList<>();
        letterCasePermutation(chars, 0, new StringBuilder(), res);
        return res;
    }

    public void letterCasePermutation(char[] chars, int pos, StringBuilder sb, List<String> res){
        if(pos == chars.length){
            res.add(sb.toString());
            return;
        }

        sb.append(chars[pos]);
        letterCasePermutation(chars, pos + 1, sb, res);
        sb.deleteCharAt(sb.length() - 1);
        if(chars[pos] >= 'a' && chars[pos] <= 'z'){
            sb.append((char)(chars[pos] + 'A' - 'a'));
            letterCasePermutation(chars, pos + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }

        if(chars[pos] >= 'A' && chars[pos] <= 'Z'){
            sb.append((char)(chars[pos] + 'a' - 'A'));
            letterCasePermutation(chars, pos + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

//    解数独
//    编写一个程序，通过已填充的空格来解决数独问题。
//    空白格用 '.' 表示。
    public void solveSudoku(char[][] board) {
        solveSudoku(board, 0, 0);
    }

    public boolean solveSudoku(char[][] board, int row, int col){
        if(col == 9){
            return solveSudoku(board, row + 1, 0);
        }
        if(row == 9){
            return true;
        }

        if(board[row][col] != '.'){
            return solveSudoku(board, row, col + 1);
        }

        for(int i = 1; i <= 9; ++i){
            if(!sudokuIsValid(board, row, col, i)){
                continue;
            }
            board[row][col] = (char)(i + '0');
            if(solveSudoku(board, row, col + 1)){
                return true;
            }
            board[row][col] = '.';
        }
        return false;
    }

    public boolean sudokuIsValid(char[][] board, int row, int col, int num){
        for(int i = 0; i < 9; ++i){
            if(num == board[row][i] - '0'){
                return false;
            }
            if(num == board[i][col] - '0'){
                return false;
            }
            if(num == board[(row/3)*3 + i/3][(col/3)*3 + i%3] - '0'){
                return false;
            }
        }
        return true;
    }


//    括号生成
//    数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
//             
//
//    示例：
//
//    输入：n = 3
//    输出：[
//            "((()))",
//            "(()())",
//            "(())()",
//            "()(())",
//            "()()()"
//            ]
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/generate-parentheses
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesis(n, n, new StringBuilder(), res);
        return res;
    }

    public void generateParenthesis(int leftCount, int rightCount, StringBuilder sb, List<String> res){
        if(leftCount == 0 && rightCount == 0){
            res.add(sb.toString());
            return;
        }

        if(leftCount > 0){
            sb.append('(');
            generateParenthesis(leftCount - 1, rightCount, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }

        if(rightCount > leftCount){
            sb.append(')');
            generateParenthesis(leftCount, rightCount - 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

//    递减元素使数组呈锯齿状
//    给你一个整数数组 nums，每次 操作 会从中选择一个元素并 将该元素的值减少 1。
//
//    如果符合下列情况之一，则数组 A 就是 锯齿数组：
//
//    每个偶数索引对应的元素都大于相邻的元素，即 A[0] > A[1] < A[2] > A[3] < A[4] > ...
//    或者，每个奇数索引对应的元素都大于相邻的元素，即 A[0] < A[1] > A[2] < A[3] > A[4] < ...
//    返回将数组 nums 转换为锯齿数组所需的最小操作次数。
//
//             
//
//    示例 1：
//
//    输入：nums = [1,2,3]
//    输出：2
//    解释：我们可以把 2 递减到 0，或把 3 递减到 1。
//    示例 2：
//
//    输入：nums = [9,6,1,6,2]
//    输出：4
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/decrease-elements-to-make-array-zigzag
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int movesToMakeZigzag(int[] nums) {
        if(nums == null || nums.length < 2){
            return 0;
        }

        int countEven = nums[0] > nums[1] ? 0 : nums[1] - nums[0] + 1;
        int rightEven = countEven;
        int countOdd = 0;
        int rightOdd = 0;
        for(int i = 1; i < nums.length - 1; ++i){
            if((i & 1) == 1){
                if(nums[i] + rightOdd <= nums[i - 1]){
                    countOdd += nums[i - 1] - rightOdd - nums[i] + 1;
                }
                if(nums[i] <= nums[i + 1]){
                    countOdd += nums[i + 1] - nums[i] + 1;
                    rightOdd = nums[i + 1] - nums[i] + 1;
                }else{
                    rightOdd = 0;
                }
            }else{
                if(nums[i] + rightEven <= nums[i - 1]){
                    countEven += nums[i - 1] - rightEven - nums[i] + 1;
                }
                if(nums[i] <= nums[i + 1]){
                    countEven += nums[i + 1] - nums[i] + 1;
                    rightEven = nums[i + 1] - nums[i] + 1;
                }else{
                    rightEven = 0;
                }
            }
        }
        int last = nums.length - 1;
        if((last & 1) == 1){
            if(nums[last] + rightOdd <= nums[last - 1]){
                countOdd += nums[last - 1] - nums[last] - rightOdd + 1;
            }
        }else{
            if(nums[last] + rightEven <= nums[last - 1]){
                countEven += nums[last - 1] - nums[last] - rightEven + 1;
            }
        }

        return Math.min(countOdd, countEven);
    }


//    网络延迟时间
//    有 N 个网络节点，标记为 1 到 N。
//
//    给定一个列表 times，表示信号经过有向边的传递时间。 times[i] = (u, v, w)，其中 u 是源节点，v 是目标节点， w 是一个信号从源节点传递到目标节点的时间。
//
//    现在，我们从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1。
//
//    输入：times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
//    输出：2
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/network-delay-time
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    //Dijkstra方法
    public int networkDelayTime(int[][] times, int N, int K) {
        //邻接表
        Map<Integer, List<int[]>> distMap = new HashMap<>();
        for(int[] edge : times){
            if(!distMap.containsKey(edge[0])){
                distMap.put(edge[0], new ArrayList<>());
            }
            distMap.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        //K点到其他节点的距离
        Map<Integer, Integer> minDist = new HashMap<>();
        minDist.put(K, 0);
        if(distMap.get(K) == null){
            return -1;
        }
        for(int[] edge : distMap.get(K)){
            minDist.put(edge[0], edge[1]);
        }

        //标记，记录已经是路径最小的位置
        boolean[] minSet = new boolean[N + 1];
        for(int i = 0; i < N; ++i){

            //找出不在标记集合中的节点，K到此节点的距离是最小
            int pos = 0;
            int dist = Integer.MAX_VALUE;
            for(Map.Entry<Integer, Integer> entry : minDist.entrySet()){
                if(!minSet[entry.getKey()]){
                    if(entry.getValue() < dist){
                        dist = entry.getValue();
                        pos = entry.getKey();
                    }
                }
            }
            if(pos == 0){
                return -1;
            }
            //记录此节点
            minSet[pos] = true;
            if(distMap.get(pos) == null){
                continue;
            }

            //更新与此节点连通的节点距离
            for(int[] edge : distMap.get(pos)){
                if(minDist.getOrDefault(edge[0], Integer.MAX_VALUE) > minDist.get(pos) + edge[1]){
                    minDist.put(edge[0], minDist.get(pos) + edge[1]);
                }
            }
        }

        if(minDist.size() != N){
            return -1;
        }
        int maxDist = 0;
        for(int key : minDist.values()){
            maxDist = Math.max(maxDist, key);
        }
        return maxDist;
    }


//    大数相加/字符串相加
//    给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
    public String addStrings(String num1, String num2) {
        if(num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0){
            return null;
        }
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while(index1 >= 0 || index2 >= 0 || carry > 0){
            int n1 = index1 >= 0 ? num1.charAt(index1) - '0' : 0;
            int n2 = index2 >= 0 ? num2.charAt(index2) - '0' : 0;
            int res = n1 + n2 + carry;
            if(res > 9){
                res -= 10;
                carry = 1;
            }else{
                carry = 0;
            }
            sb.append(res);
            --index1;
            --index2;
        }
        return sb.reverse().toString();
    }


    //大数相减/字符串相减
    //输入中num1 肯定大于 num2
    public String minusString(String num1, String num2){
        if(num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0){
            return "";
        }
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;
        int borrow = 0;
        StringBuilder sb = new StringBuilder();
        while (index1 >= 0 || index2 >= 0){
            int n1 = index1 >= 0 ? num1.charAt(index1) - '0' : 0;
            int n2 = index2 >= 0 ? num2.charAt(index2) - '0' : 0;
            int res = n1 - n2 - borrow;
            if(res < 0){
                res += 10;
                borrow = 1;
            }else {
                borrow = 0;
            }
            sb.append(res);
            --index1;
            --index2;
        }
        while (sb.charAt(sb.length() - 1) == '0'){
            sb.deleteCharAt(sb.length() - 1);
        }
        return  sb.reverse().toString();
    }


//    大数相乘/字符串相乘
//    给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
//
//    示例 1:
//
//    输入: num1 = "2", num2 = "3"
//    输出: "6"
//    示例 2:
//
//    输入: num1 = "123", num2 = "456"
//    输出: "56088"
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/multiply-strings
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public String multiply(String num1, String num2) {
        if(num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0){
            return "";
        }
        if("0".equals(num1) || "0".equals(num2)){
            return "0";
        }
        int length1 = num1.length();
        int length2 = num2.length();
        int[] res = new int[length1 + length2];
        for(int i = length1 - 1; i >= 0; --i){
            int n1 = num1.charAt(i) - '0';
            for(int j = length2 - 1; j >= 0; --j){
                int n2 = num2.charAt(j) - '0';
                res[i + j + 1] += n1 * n2;
                if(res[i + j + 1] > 9){
                    res[i + j] += res[i + j + 1] / 10;
                    res[i + j + 1] %= 10;
                }
            }
        }
        int index = 0;
        while(res[index] == 0){
            ++index;
        }
        StringBuilder sb = new StringBuilder();
        for(; index < res.length; ++index){
            sb.append(res[index]);
        }
        return sb.toString();
    }


    //剑指 Offer 63. 股票的最大利润(只能买卖一次)
//    假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
//
//             
//
//    示例 1:
//
//    输入: [7,1,5,3,6,4]
//    输出: 5
//    解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//    注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
//    示例 2:
//
//    输入: [7,6,4,3,1]
//    输出: 0
//    解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }

        int minValue = prices[0];
        int profit = 0;
        for(int i = 1; i < prices.length; ++i){
            profit = Math.max(profit, prices[i] - minValue);
            if(minValue > prices[i]){
                minValue = prices[i];
            }
        }
        return profit;
    }

    //买卖股票的最佳时机 II
//    给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
//
//    设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
//
//    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//
//    示例 1:
//
//    输入: [7,1,5,3,6,4]
//    输出: 7
//    解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//                 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
//    示例 2:
//
//    输入: [1,2,3,4,5]
//    输出: 4
//    解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//                 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
//                 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
//    示例 3:
//
//    输入: [7,6,4,3,1]
//    输出: 0
//    解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int maxProfit2(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        int maxHold = -prices[0];
        int maxSold = 0;
        int tmpHold = 0;
        int tmpSold = 0;

        for(int i = 0; i < prices.length; ++i){
            tmpHold = Math.max(maxHold, maxSold - prices[i]);
            tmpSold = Math.max(tmpSold, maxHold + prices[i]);
            maxHold = tmpHold;
            maxSold = tmpSold;
        }

        return maxSold;
    }

    //买卖股票的最佳时机 III
//    给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
//    设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
//    注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//
//    示例 1:
//
//    输入: [3,3,5,0,0,3,1,4]
//    输出: 6
//    解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//                 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
//    示例 2:
//
//    输入: [1,2,3,4,5]
//    输出: 4
//    解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
//                 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
//                 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
//    示例 3:
//
//    输入: [7,6,4,3,1]
//    输出: 0
//    解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。



//    最佳买卖股票时机含冷冻期
//    给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
//
//    设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
//
//    你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//    卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
//
//    示例:
//
//    输入: [1,2,3,0,2]
//    输出: 3
//    解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int maxProfitWithCold(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        int cash = 0;
        int hold = 0;
        int cold = 0;
        int preCash = 0;
        int preHold = -prices[0];
        int preCold = 0;
        for(int i = 0; i < prices.length; ++i){
            cash = preHold + prices[i];
            hold = Math.max(preHold, preCold - prices[i]);
            cold = Math.max(preCold, preCash);
            preCash = cash;
            preHold = hold;
            preCold = cold;
        }
        return Math.max(cold, cash);
    }

//    买卖股票的最佳时机含手续费
//    给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
//
//    你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
//
//    返回获得利润的最大值。
//
//    注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
//
//    示例 1:
//
//    输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
//    输出: 8
//    解释: 能够达到的最大利润:
//    在此处买入 prices[0] = 1
//    在此处卖出 prices[3] = 8
//    在此处买入 prices[4] = 4
//    在此处卖出 prices[5] = 9
//    总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int maxProfitWithFee(int[] prices, int fee) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        int cash = 0;
        int hold = 0;
        int preCash = 0;
        int preHold = -prices[0];
        for(int i = 0; i < prices.length; ++i){
            cash = Math.max(preCash, preHold + prices[i] - fee);
            hold = Math.max(preHold, preCash - prices[i]);
            preCash = cash;
            preHold = hold;
        }
        return cash;
    }

    //关于交易次数有限制的股票交易 https://leetcode-cn.com/circle/article/qiAgHn/
    // dp[i][k][s] 其中 i 是第几天，k是第几次交易，s是状态（0 表示持现金，1表示持股票）
    // 初始状态 dp[0][0-k][1] = -prices[0]
    // 状态转移方程 dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i])
    //             dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i])

}
