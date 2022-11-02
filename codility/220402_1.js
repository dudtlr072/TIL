/*
A small frog wants to get to the other side of the road. 
The frog is currently located at position X and wants to get to a position greater than or equal to Y. 
The small frog always jumps a fixed distance, D.

Count the minimal number of jumps that the small frog must perform to reach its target.

Write a function:

function solution(X, Y, D);

that, given three integers X, Y and D, returns the minimal number of jumps from position X to a position equal to or greater than Y.

For example, given:

  X = 10
  Y = 85
  D = 30
the function should return 3, because the frog will be positioned as follows:

after the first jump, at position 10 + 30 = 40
after the second jump, at position 10 + 30 + 30 = 70
after the third jump, at position 10 + 30 + 30 + 30 = 100
Write an efficient algorithm for the following assumptions:

X, Y and D are integers within the range [1..1,000,000,000];
X ≤ Y.
*/


/*
단순 수학 공식으로 풀어봄

X + (D * x) >= Y
(D * x) >= Y - X
x >= (Y - X) / D

(오른쪽 계산식 결과를 A라고 한다면,)
x는 최소 A보다 크거나 같아야 한다.
이떄 A계산의 나머지 값은 Y보다 크거나 같기 위한 남은 값이다.
그래서 D가 A계산의 나머지 값보다 크다면 마지막 count를 +1한다.
나머지값이 0이면 그대로 리턴.

나머지 12%는 어떤 case일까...
*/

// 88%
function solution(X, Y, D) {
    // write your code in JavaScript (Node.js 8.9.4)
    let answer = parseInt((Y - X) / D);
    let rest = (Y - X) % D;
    console.log("answer", answer);
    console.log("rest", rest);
    return (rest > 0 && D >= rest) ? answer + 1 : answer;
}

// 44%
// Performance 점수가 너무 낮게 나옴...
let result = 0;
function solution_2(X, Y, D) {
    if (X >= Y) {
      console.log("result", result);
      return result;
    } else {
      X = X + D;
      result++;
      console.log("X:"+X+" Y:"+Y+" D:"+D);
      return solution_2(X, Y, D);
    }
}

console.log(solution_2(1, 5 ,2));
console.log(solution_2(10, 85 ,30));