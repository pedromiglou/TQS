## Task 1.f

Issue: Bug
Problem Description: in Dip.java a new Random Object is created when a random value is needed. It is inefficient and may not produce random numbers.
How to Solve: create a Random instance and use that instance everytime we need a new random value.
----------------------------------------------
Issue: Vulnerability
0 found
----------------------------------------------
Issue: Code smell (major)
Problem description: in DemoMain.java there are messages being printed in the standard output which makes it so those messages are not recorded.
Solution: replace all "System.out" with a Logger

Issue: Code smell (major)
Problem Description: in Dip.java the variable used as a loop counter is being increased from within the loop body.
Solution: increase this variable outside the loop body.

## Task 2.a
Debt: 2h 17min
This value is the sum of time needed to fix each problem of the code. It represents the amount of time we will have to spend to fix the code because we chose an easy but worse solution instead of a better one even if it would cost more time.

## Task 2.d
48 Uncovered Lines
14 Uncovered Conditions