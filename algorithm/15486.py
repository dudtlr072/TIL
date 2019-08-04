# 퇴사2

scheduleList = []
tempList = []
maxCost = 0

N = int(input())
for i in range(0, N):
    scheduleList.append(list(map(int, input().split())))
    tempList.append(-1)

tempList.insert(scheduleList[0][0], scheduleList[0][1])
scheduleList[0][1] = 0

for i in range(1, N):
    nextIndex = i + scheduleList[i][0]
    print("i:", i, " / day: ", scheduleList[i][0])
    if nextIndex <= N:
        if tempList[nextIndex] is -1:
            tempList.insert(nextIndex, scheduleList[i][1])
        else:
            tempList[nextIndex] = max(tempList[nextIndex], scheduleList[i][1])

        if tempList[i] is -1:
            print("[1]", scheduleList[i - 1][1])
            scheduleList[i][1] = scheduleList[i - 1][1]
        else:
            print("[2]", scheduleList[i][1], tempList[i], scheduleList[i - 1][1])
            scheduleList[i][1] = scheduleList[i][1] + max(tempList[i], scheduleList[i - 1][1])
        maxCost = max(maxCost, scheduleList[i][1])

print(maxCost)