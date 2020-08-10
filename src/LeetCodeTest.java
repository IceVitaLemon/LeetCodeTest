import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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

}
