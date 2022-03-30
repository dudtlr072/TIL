/*
A non-empty array A consisting of N integers is given. 
The array contains an odd number of elements, and each element of the array can be paired with another element 
that has the same value, except for one element that is left unpaired.

For example, in array A such that:

  A[0] = 9  A[1] = 3  A[2] = 9
  A[3] = 3  A[4] = 9  A[5] = 7
  A[6] = 9
the elements at indexes 0 and 2 have value 9,
the elements at indexes 1 and 3 have value 3,
the elements at indexes 4 and 6 have value 9,
the element at index 5 has value 7 and is unpaired.
Write a function:

function solution(A);

that, given an array A consisting of N integers fulfilling the above conditions, returns the value of the unpaired element.

For example, given array A such that:

  A[0] = 9  A[1] = 3  A[2] = 9
  A[3] = 3  A[4] = 9  A[5] = 7
  A[6] = 9
the function should return 7, as explained in the example above.

Write an efficient algorithm for the following assumptions:

N is an odd integer within the range [1..1,000,000];
each element of array A is an integer within the range [1..1,000,000,000];
all but one of the values in A occur an even number of times.
*/


// 66%
function solution(A) {
    let noMatchNums = [];

    if (A && A.length === 1) {
        noMatchNums.push(A[0]);
    } else {
        A.forEach((num) => {
            if (noMatchNums.length === 0) {
                noMatchNums.push(num);
            } else {
                let exist = false;
                noMatchNums.forEach((noMatchNum, i) => {
                    if (noMatchNum === num) {
                        // 매칭되는 항목은 삭제
                        noMatchNums.splice(i, 1);
                        exist = true;
                        return;
                    }
                });
                if (!exist) noMatchNums.push(num);
            }
            console.log(noMatchNums);
        });
    }

    return noMatchNums[0];
}

// 66%
// indexOf도 결국엔 array 길이만큼 돌아야함
function solution_2(A) {
    console.log("A: ", A);
    if (A.length === 1) {
        return A[0];
    } else {
        const firstItem = A[0];
        A.shift();
        console.log("firstItem: " + firstItem);
        console.log("A.shift(): ", A);
        const idx = A.indexOf(firstItem);
        console.log("idx: ", idx);
        if (idx > -1) {
            A.splice(idx, 1);
            console.log("4: ", A);
            console.log("------------------");
            return solution_2(A);
        } else {
            return firstItem;
        }
    }
}

// 100%
function solution_3(A) {
    let map = new Map();
    if (A.length === 1) {
        return A[0];
    } else {
        A.forEach((num) => {
            console.log("=============");
            console.log("num: " + num);
            if (!map.get(num)) {
                // map에 저장된게 없으면 새롭게 저장
                map.set(num, 1);
                console.log("no exist", map);
            } else {
                // map에 이미 저장된게 있으면 한쌍이 완성되므로 저장된것도 삭제
                map.delete(num);
                console.log("exist", map);
            }
        });
        const iterator = map.keys();
        return iterator.next().value;
    }
}

console.log(solution_3([9, 3, 9, 3, 9, 7, 9]));