import java.util.*;

public class GraphAlgorithm {

    public static void main(String[] args) {
//        int[][] edges = {
//                {1, 2, 1},
//                {1, 3, 12},
//                {2, 3, 9},
//                {2, 4, 3},
//                {4, 3, 4},
//                {3, 5, 5},
//                {4, 5, 13},
//                {4, 6, 15},
//                {5, 6, 4}
//        };
        int[][] edges = {
                {1, 2, 6},
                {1, 3, 1},
                {1, 4, 5},
                {2, 3, 5},
                {2, 5, 3},
                {3, 4, 5},
                {3, 5, 6},
                {3, 6, 4},
                {4, 6, 2},
                {5, 6, 6}
        };

        GraphAlgorithm.Kruskal.minSpanningTree(edges, 6);
//        int[][] edges = {
//                {2, 3, 2},
//                {1, 2, -3},
//                {1, 5, 5},
//                {4, 5, 2},
//                {3, 4, 3}
//        };
//        GraphAlgorithm.BellmanFord.point2otherPoint(edges, 1, 5);

        int[][] edges2 = {
                {1, 2},
                {2, 4},
                {1, 3},
                {2, 3},
                {3, 4},
                {3, 5},
                {4, 5},
        };
        GraphAlgorithm.TopologicalSort.topologicalSort(edges2, 5);
    }

    public static class Dijkstra{
        /***
         * 求图中节点P到其他节点的最短距离(权值只能为正数)
         * @param edges 图边的信息 edges[i] = (u, v, w) 其中 u 是源节点，v 是目标节点， w 是权值
         * @param P 图中节点P
         * @param N 图中节点数目
         */
        public static Map<Integer, Integer> point2otherPoint(int[][] edges, int P, int N){
            if (edges == null || edges.length == 0 || edges[0] == null){
                return null;
            }

            //邻接表
            HashMap<Integer, List<int[]>> edgesMap = new HashMap<>();
            for (int[] edge : edges){
                if (!edgesMap.containsKey(edge[0])){
                    edgesMap.put(edge[0], new ArrayList<>());
                }
                edgesMap.get(edge[0]).add(new int[]{edge[1], edge[2]});
            }
            //节点P到其他点的距离
            HashMap<Integer, Integer> pDistMap = new HashMap<>();
            pDistMap.put(P, 0);
            if (edgesMap.get(P) == null){
                return null;
            }
            for (int[] targetAndWeight : edgesMap.get(P)){
                pDistMap.put(targetAndWeight[0], targetAndWeight[1]);
            }

            boolean[] minSet = new boolean[N + 1];
            for (int i = 0; i < N; i++) {
                int target = 0;
                int min = Integer.MAX_VALUE;
                for (Map.Entry<Integer, Integer> entry : pDistMap.entrySet()){
                    if (!minSet[entry.getKey()]){
                        if (entry.getValue() < min){
                            min = entry.getValue();
                            target = entry.getKey();
                        }
                    }
                }
                if (target == 0){
                    return null;
                }

                minSet[target] = true;
                //如果target没有边连接其他节点则继续
                if (edgesMap.get(target) == null){
                    continue;
                }

                for (int[] edge : edgesMap.get(target)){
                    if (pDistMap.getOrDefault(edge[0], Integer.MAX_VALUE) > pDistMap.get(target) + edge[1]){
                        pDistMap.put(edge[0], pDistMap.get(target) + edge[1]);
                    }
                }
            }
            return pDistMap;
        }
    }

    public static class BellmanFord{
        /***
         * 图中节点P到其他节点的最短距离(权值可以为负数)
         * @param edges 图边的信息 edges[i] = (u, v, w) 其中 u 是源节点，v 是目标节点， w 是权值
         * @param P 图中节点P
         * @param N 图中节点数目
         */
        public static Map<Integer, Integer> point2otherPoint(int[][] edges, int P, int N){
            if (edges == null || edges.length == 0 || edges[0] == null){
                return null;
            }
            int M = edges.length;
            int[] source = new int[M];
            int[] target = new int[M];
            int[] weight = new int[M];
            int index = 0;
            for (int[] edge : edges){
                source[index] = edge[0];
                target[index] = edge[1];
                weight[index] = edge[2];
                ++index;
            }

            HashMap<Integer, Integer> pDistMap = new HashMap<>();
            for (int i = 1; i < N + 1; i++) {
                pDistMap.put(i, Integer.MAX_VALUE);
            }
            pDistMap.put(P, 0);

            for (int i = 0; i < N; i++) {
                boolean check = false;
                for (int e = 0; e < M; e++) {
                    if (pDistMap.get(source[e]) == Integer.MAX_VALUE){
                        continue;
                    }
                    if (pDistMap.get(target[e]) == Integer.MAX_VALUE
                            || pDistMap.get(target[e]) > pDistMap.get(source[e]) + weight[e]){
                        pDistMap.put(target[e], pDistMap.get(source[e]) + weight[e]);
                        check = true;
                    }
                }
                if (!check){
                    break;
                }
            }
            return pDistMap;
        }
    }

    public static class Kruskal{
        /***
         * 最小生成树
         * 1. 把图中的所有边按代价从小到大排序；
         * 2. 把图中的n个顶点看成独立的n棵树组成的森林；
         * 3. 按权值从小到大选择边，所选的边连接的两个顶点ui,vi,应属于两颗不同的树，则成为最小生成树的一条边，并将这两颗树合并作为一颗树。
         * 4. 重复(3),直到所有顶点都在一颗树内或者有n-1条边为止。
         * @param edges 图边的信息 edges[i] = (u, v, w) 其中 u 是源节点，v 是目标节点， w 是权值
         * @param N
         * @return
         */
        public static List<int[]> minSpanningTree(int[][] edges, int N){
            if (edges == null || edges.length == 0 || edges[0] == null){
                return null;
            }

            int[] roots = new int[N + 1];
            for (int i = 1; i < N + 1; i++) {
                roots[i] = i;
            }
            //对边的长度排序
            Arrays.sort(edges, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[2] - o2[2];
                }
            });

            List<int[]> spanTree = new ArrayList<>();
            for (int[] edge : edges){
                int rootA = findRoot(roots, edge[0]);
                int rootB = findRoot(roots, edge[1]);
                if (rootA != rootB){
                    if (rootA > rootB){
                        roots[rootA] = rootB;
                    }else {
                        roots[rootB] = rootA;
                    }
                    spanTree.add(edge);
                    --N;
                }
                if (N == 1){
                    break;
                }
            }
            return spanTree;
        }

        public static int findRoot(int[] roots, int index){
            int root = index;
            while (root != roots[root]){
                root = roots[root];
            }
            while (index != root){
                int tmp = roots[index];
                roots[index] = root;
                index = tmp;
            }
            return root;
        }
    }


    public static class TopologicalSort{
        /***
         * 拓扑排序
         * 1.初始化一个int[] inDegree保存每一个结点的入度。
         * 2.对于图中的每一个结点的子结点，将其子结点的入度加1。
         * 3.选取入度为0的结点开始遍历，并将该节点加入输出。
         * 4.对于遍历过的每个结点，更新其子结点的入度: 将子结点的入度减1。
         * 5.重复步骤3，直到遍历完所有的结点。
         * 6.如果无法遍历完所有的结点，则意味着当前的图不是有向无环图。不存在拓扑排序。
         * @param edges 图边的信息 edges[i] = (u, v, w) 其中 u 是源节点，v 是目标节点， w 是权值
         * @param N 节点数目
         * @return
         */
        public static List<Integer> topologicalSort(int[][] edges, int N){
            if (edges == null || edges.length == 0 || edges[0] == null){
                return null;
            }
            int[] inDegree = new int[N + 1];
            for (int[] edge : edges){
                inDegree[edge[1]] += 1;
            }
            HashMap<Integer, List<Integer>> edgesMap = new HashMap<>();
            for (int[] edge : edges){
                if (!edgesMap.containsKey(edge[0])){
                    edgesMap.put(edge[0], new ArrayList<>());
                }
                edgesMap.get(edge[0]).add(edge[1]);
            }

            List<Integer> sortedList = new ArrayList<>();
            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = 1; i < N + 1; i++) {
                if (inDegree[i] == 0){
                    deque.offer(i);
                }
            }

            while (!deque.isEmpty()){
                int curNode = deque.poll();
                sortedList.add(curNode);
                if (edgesMap.get(curNode) == null){
                    continue;
                }
                for (int index : edgesMap.get(curNode)){
                    inDegree[index] -= 1;
                    if (inDegree[index] == 0){
                        deque.offer(index);
                    }
                }
            }
            return sortedList.size() == N ? sortedList : new ArrayList<>();
        }
    }
}
