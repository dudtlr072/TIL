
/**
 * 정렬이 되면서 마지막 정렬이면 연속된 숫자이기때문에 없는 숫자를 찾을 수 있다.
 */
let result = 0;
function checkContinue(arr, pivot) {
    if (arr.length === 1 && pivot) {
        console.log("[length == 1] arr[0]:" + arr[0] + " pivot:" + pivot);
        const subNum = Math.abs(arr[0] - pivot);
        if (subNum === 2) {
            result = parseInt((pivot + arr[0]) / 2);
            console.log("[length == 1] result: " + result);
        }
    } else if (arr.length === 2) {
        console.log("[length == 2] arr[0]:" + arr[0] + " arr[1]:" + arr[1]);
        const subNum = Math.abs(arr[0] - arr[1]);
        if (subNum === 2) {
            result = parseInt((arr[0] + arr[1]) / 2);
            console.log("[length == 2] result: " + result);
        }
    }
}

/*
퀵 정렬 (제일 빠름... 확실히 알아놓자)
*/
function sort(A) {
    if (A.length <= 1) {
        return A;
    }
    let left = []; // 기준값을 기준으로 왼쪽에 정렬될 애들
    let right = []; // 기준값을 기준으로 오른쪽에 정렬될 애들
    let pivot = A[0]; // 기준

    A.forEach((item, idx) => {
        if (idx !== 0) { // pivot값은 건너 띄어야지...
            if (item <= pivot) {
                left.push(item);
            } else {
                right.push(item);
            }
        }
    });
    
    console.log("left: ", left);
    console.log("right: ", right);
    console.log("---------------------");
    checkContinue(left, pivot);
    checkContinue(right, pivot);

    // 재귀함수로 왼쪽, 오른쪽 애들을 정렬시킨 후 pivot값이랑 한데 모아서 리턴
    const sortedLeft = sort(left);
    const sortedRight = sort(right);
    return [...sortedLeft, pivot, ...sortedRight];
}

function solution(A) {
    if (A.length === 0) {
        result = 1;
    } else if (A.length === 1) {
        result = A[0] === 1 ? A[0] + 1 : A[0] - 1;
    } else {
        sort(A);
        console.log("result", result);
        if (result === 0) {
            result = A.length === 0 ? 1 : A[A.length - 1] + 1;
        }
    }
    return result;
}

// console.log(solution([2, 3, 1, 5]));
console.log(solution([]));