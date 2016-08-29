Coursera: Algorithms
===================

Programming Assignments (Algorithms, Part I and II by Kevin Wayne, Robert Sedgewick)

Part I

PA-1 Percolation (Union-find)
###
		Specification: 
			http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
		Code:
			Percolation.java;
			PercolationStats.java
	
PA-2 Randomized Queues and Deques (Queue and Stack)
###
		Specification:
			http://coursera.cs.princeton.edu/algs4/assignments/queues.html
		Code:
			Deque.java;
			RandomizedQueue.java;
			Subset.java

PA-3 Pattern Recognition (Sort)
###
		Specification:
			http://coursera.cs.princeton.edu/algs4/assignments/collinear.html
		Code:
			Point.java;
			Brute.java;
			Fast.java

PA-4 8 Puzzle (Priority Queues)
###
		Specification:
			http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html
		Code:
			Point.java;
			Board.java;
			Solver.java

Part II

PA-1 WordNet
###
		Specification: 
			http://coursera.cs.princeton.edu/algs4/assignments/wordnet.html
		Code:
			WordNet.java;
			SAP.java;
			Outcast.java

PA-2 Seam Carving
###
		Specification: 
			http://coursera.cs.princeton.edu/algs4/assignments/seamCarving.html
		Code:
			SeamCarver.java
	
	

---
dsa:(Data structure and algorithms)
===================

1. BucketSort // bucket sort<br>
2. counting sort //counting sort<br>
3. Poker // Pocker's Monte-Carlo simulation in java<br>
4. Range //binary search algorithm<br>
5. Train_Stack // using stack to simulate train dispatch<br>
6. Zuma //using list to simulate zuma game<br>

1.Range查询范围，用bin_search和qsort 题目描述： http://dsa.cs.tsinghua.edu.cn/oj/problem.shtml?id=936<br>

----

2.Zuma链表实现ZUMA消除 题目描述： http://dsa.cs.tsinghua.edu.cn/oj/problem.shtml?id=937<br> 
原理：
在插入位置检查前后相同元素数目，然后得到最左和最右相同元素的位置，删除它们，得到最左相同元素左边的再元素检查它重复直到相同元素数目小于3，这里有个坑就是当最左边元素==header时候，此时应该直接结束循环而不是再check_front <br>
<img src="http://dsa.cs.tsinghua.edu.cn/oj/attachment/39d0/39d0c6d89d99f25f06b6217f2bba86ea4747dd49.png"/>

----

3.用栈实现列车调度序列 题目描述 http://dsa.cs.tsinghua.edu.cn/oj/problem.shtml?id=939<br>
原理:
例如序列3 5 4 2 1 循环该序列若栈为空时，例如在第一个元素3时，栈为空，此时比较3与该下标的差值，从该下标j = i push（j+1）到 i + 差值，push进栈中，在此之前比较dif是否大于最大栈深<br>
若此时栈不为空，例如第二个元素5（此时还有两个元素在栈即2，1），将该值与前一个元素（3）做差值，该值前一个元素j = seq[i-1]到 seq[i-1]+差值，push进栈中，在此之前比较最大栈深与dif，还要判断dif是否<=-2，因为如果dif<=-2（如3 1 2）那么这样的序列是无法构成的。
直到一种情况:当车队为空（此时栈还没空），这时只需将栈中所有元素pop出来，即可组成需要的序列（3 5 4 2 1）--->push push push pop push push pop pop pop pop
<img src="http://dsa.cs.tsinghua.edu.cn/oj/attachment/03bc/03bc70595803464554b5f6b69a21962beb038264.png"/><br>

----
