package com.example.myapplication.tree;

import com.example.myapplication.CollectionUtil;
import com.example.myapplication.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树相关题目
 */
public class TreeTest1 {
    /**
     * 柠檬水找零
     * https://leetcode-cn.com/problems/lemonade-change/
     *
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        boolean result = true;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                five += 5;
            } else if (bills[i] == 10) {
                five -= 5;
                if (five + ten < 0) {
                    result = false;
                    break;
                }
                ten += 10;
            } else if (bills[i] == 20) {
                //两种找零方案
                if (ten > 0) {
                    ten -= 10;
                    five -= 5;
                } else {
                    five -= 15;
                }
                if (ten + five < 0) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }


    /**
     * 返回二叉树的中序遍历
     *
     * @param root
     * @return
     */
    List<Integer> inorderList = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode<Integer> root) {
        _inorderTraversal(root);
        CollectionUtil.printList2(inorderList);
        return inorderList;
    }

    public void _inorderTraversal(TreeNode<Integer> node) {
        if (node == null) return;
        _inorderTraversal(node.left);
        inorderList.add(node.data);
        _inorderTraversal(node.right);
    }

    /**
     * 相同的树
     * 1.结构相同
     * 2.节点值相同
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return _isSameTree(p, q);

    }

    public boolean _isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q != null) return false;
        if (p != null && q == null) return false;
        if (p == null && q == null) return true;
        boolean result = true;
        if (!p.data.equals(q.data)) {
            return false;
        }
        result = isSameTree(p.left, q.left);
        if (!result) {
            return result;
        } else {
            result = isSameTree(p.right, q.right);
        }
        return result;
    }

    /**
     * 二叉树的最大深度
     * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
     * Math.max(left,right)+1
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        return _maxDepth(root);
    }

    //返回当前节点的最大深度
    public int _maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = _maxDepth(root.left);
        int rightHeight = _maxDepth(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
     * 将有序数组转换为二叉搜索树
     *
     * @param nums
     * @return
     */
    public TreeNode<Integer> sortedArrayToBST(int[] nums) {
        return _sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode<Integer> _sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) return null;
        if (start == end) return new TreeNode<>(nums[start]);
        int middle = start + (end - start + 1) / 2;
        TreeNode<Integer> root = new TreeNode<>(nums[middle]);
        root.left = _sortedArrayToBST(nums, start, middle - 1);
        root.right = _sortedArrayToBST(nums, middle + 1, end);
        return root;
    }

    /**
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
     * 二叉树的层序遍历
     *
     * @param root
     * @return 思路:
     * 1.使用一个辅助集合，保存每一层的元素
     */
    List<List<Integer>> levelOrderList = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode<Integer> root) {
        if (root == null) return levelOrderList;
        Queue<TreeNode> leverList = new ArrayDeque<>();
        leverList.add(root);
        while (!leverList.isEmpty()) {
            List<Integer> result = new ArrayList<>();
            int size = leverList.size();
            for (int i = 0; i < size; i++) {
                TreeNode<Integer> poll = leverList.remove();
                if (poll.left != null) {
                    leverList.add(poll.left);
                }
                if (poll.right != null) {
                    leverList.add(poll.right);
                }
                result.add(poll.data);
            }
            levelOrderList.add(0, result);
        }
        return levelOrderList;
    }

    /**
     * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
     * 最小深度
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        return _minDepth(root);
    }

    public int _minDepth(TreeNode node) {
        if (node == null) return 0;
        int left = _minDepth(node.left);
        int right = _minDepth(node.right);
        if (node.left == null) {
            return right + 1;
        } else if (node.right == null) {
            return left + 1;
        }
        return Math.min(left, right) + 1;
    }

    /**
     * https://leetcode-cn.com/problems/path-sum/
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     * 注意：是叶节点
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode<Integer> root, int sum) {
        return _hasPathSum(root, sum);
    }

    public boolean _hasPathSum(TreeNode<Integer> node, int sum) {
        if (node == null) return false;
        boolean result = false;
        if ((sum - node.data == 0) && node.left == null && node.right == null) {
            result = true;
        }
        if (result) {
            return result;
        } else {
            result = _hasPathSum(node.left, sum - node.data);
            if (result) {
                return result;
            } else {
                return _hasPathSum(node.right, sum - node.data);
            }
        }
    }

    /**
     * https://leetcode-cn.com/problems/path-sum-ii/
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     * 注意：是叶节点
     *
     * @param root
     * @param sum
     * @return 所有路径的集合
     */
    List<List<Integer>> pathList = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<Integer> list = new ArrayList<>();
        _hasPathSum2(root, sum, list);
        return pathList;
    }

    public void _hasPathSum2(TreeNode<Integer> node, int sum, List<Integer> list) {
        if (node == null) return;
        list.add(node.data);

        if (node.left == null && node.right == null) {
            //叶节点
            if ((sum - node.data == 0)) {
                //找到了这个点
                pathList.add(CollectionUtil.cloneIntegerList(list));
            }
        } else {
            _hasPathSum2(node.left, sum - node.data, list);

            _hasPathSum2(node.right, sum - node.data, list);
        }
        list.remove(node.data);
    }

    /**
     * https://leetcode-cn.com/problems/invert-binary-tree/
     * 翻转二叉树
     *
     * @param root
     * @return 思路：
     * 1.递归地将每个子节点翻转
     */
    public TreeNode invertTree(TreeNode root) {
        _invertTree(root);
        return root;
    }

    public void _invertTree(TreeNode node) {
        if (node == null) return;
        _invertTree(node.left);
        _invertTree(node.right);
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }

    /**
     * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
     * 二叉搜索树中第k小的元素
     *
     * @param root
     * @param k
     * @return 思路1：
     * BST的中序遍历为升序
     */
    public int kthSmallest(TreeNode<Integer> root, int k) {
        List<TreeNode<Integer>> list = new ArrayList<>();
        _inorderTraverse(root, list);
        return list.get(k - 1).data;
    }

    /**
     * @param root
     * @param k
     * @return 思路2：
     * 使用迭代，不需要遍历整颗树，在找到答案后停止
     * 1.一直向左走，可以保证是升序的
     */
    public int kthSmallest2(TreeNode<Integer> root, int k) {
        LinkedList<TreeNode<Integer>> list = new LinkedList<>();

        TreeNode<Integer> cur = root;
        while (cur != null) {
            list.addFirst(cur);
            cur = cur.left;
        }

        return list.get(k - 1).data;
    }

    private void _inorderTraverse(TreeNode node, List<TreeNode<Integer>> list) {
        if (node == null) return;
        _inorderTraverse(node.left, list);
        list.add(node);
        _inorderTraverse(node.right, list);
    }

    /**
     * https://leetcode-cn.com/problems/symmetric-tree/
     * 对称二叉树
     *
     * @param root
     * @return 思路:
     * 1.递归
     * 判断两棵树是否对称
     * a. 两颗树的根节点相同
     * b. A.left=B.right
     */
    public boolean isSymmetric(TreeNode root) {
        return _isSymmetric(root, root);
    }

    public boolean _isSymmetric(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 != null) return false;
        if (node1 != null && node2 == null) return false;
        if (node1 == null && node2 == null) return true;
        if (node1.data != node2.data) return false;
        return _isSameTree(node1.left, node2.right) && _isSameTree(node1.right, node2.left);
    }

    /**
     * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
     * 给定一个二叉树，原地将它展开为一个单链表
     *
     * @param root 思路一：
     *             <p>
     *             后序遍历(先访问右节点)，每访问到一个节点，将该节点的right设置为上一个节点
     */
    public void flatten(TreeNode root) {
        _postTraverseFlatten(root);
    }

    TreeNode pre;

    public void _postTraverseFlatten(TreeNode node) {
        if (node == null) return;
        _postTraverseFlatten(node.right);
        _postTraverseFlatten(node.left);
        node.right = pre;
        pre = node;
    }


    /**
     * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
     * 最近的公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return 思路:
     * 递归
     * 如果一个节点是p和q的父节点，那么要满足下面的条件
     * 1.他是p,他的左边或者右边包含q
     * 2.他是q,他的左边或者右边包含p
     * 3.他的左边或者右边包含p和q
     * <p>
     * 如何保证找到的父节点是最近的？
     * 因为是从下到上的递归，并且一旦找到该节点，上面的节点不用做判断直接返回该节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        _lowestCommonAncestor(root, p, q);
        return lowestCommonAncestor;
    }

    TreeNode lowestCommonAncestor;

    public boolean _lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) return false;
        boolean left = _lowestCommonAncestor(node.left, p, q);
        boolean right = _lowestCommonAncestor(node.right, p, q);
        if (left && right || (node.data == p && (left || right)) || (node.data == q && (left || right))) {
            if (lowestCommonAncestor == null) {
                lowestCommonAncestor = node;
            }
        }
        return left || right || node.data == p || node.data == q;
    }

    /**
     * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
     * 二叉搜索树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return 思路：
     * 1.
     */
    public TreeNode<Integer> lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        _lowestCommonAncestor2(root, p, q);
        return lowestCommonAncestor;
    }


    public void _lowestCommonAncestor2(TreeNode<Integer> node, TreeNode<Integer> p, TreeNode<Integer> q) {
        if (node == null) return;
        if (node.data > p.data && node.data > q.data) {
            _lowestCommonAncestor(node.left, p, q);
        } else if (node.data < p.data && node.data < q.data) {
            _lowestCommonAncestor(node.left, p, q);
        } else {
            lowestCommonAncestor = node;
        }
    }

    /**
     * 平衡二叉树
     *
     * @param root
     * @return 左右两颗子树的高度差不超过1
     * 思路:递归的判断每个节点的左右节点高度
     */
    boolean isBalanced = true;

    public boolean isBalanced(TreeNode root) {
        _nodeHeight(root);
        return isBalanced;
    }

    public int _nodeHeight(TreeNode node) {
        if (node == null) return 0;
        //在每一个节点，拿到左右节点的最大高度
        int leftHeight = _nodeHeight(node.left);
        int rightHeight = _nodeHeight(node.right);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            isBalanced = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }


    /**
     * https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
     * 求根到叶子节点数字之和
     *
     * @param root
     * @return
     */
    List<String> pathNums = new ArrayList<>();

    public int sumNumbers(TreeNode root) {
        _sumNumbers(root, new StringBuilder());
        int total = 0;
        for (int i = 0; i < pathNums.size(); i++) {
            total += Integer.parseInt(pathNums.get(i));
        }
        CollectionUtil.printList3(pathNums);
        return total;
    }

    public void _sumNumbers(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.data);
        if (node.left == null && node.right == null) {
            pathNums.add(new String(sb.toString()));
            return;
        }
        if (node.left != null) {
            _sumNumbers(node.left, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (node.right != null) {
            _sumNumbers(node.right, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * https://leetcode-cn.com/problems/count-complete-tree-nodes/
     * 完全二叉树的节点个数
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        return _countNodes(root);
    }

    public int _countNodes(TreeNode node) {
        if (node == null) return 0;
        int left = _countNodes(node.left);
        int right = _countNodes(node.right);
        return left + right + 1;
    }
}
