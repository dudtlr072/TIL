# 2 x n 배열

N = int(input())

maxValueList = []
pre = 0
prePre = 0

for i in range(0, N):
    if i < 2:
        maxValueList.append(i + 1)
        continue

    pre = maxValueList[i - 1]
    prePre = maxValueList[i - 2]

    maxValueList.append(pre + prePre)

print(maxValueList[N - 1] % 10007)