# 퇴사

scheduleList = []
maxCostList = []

N = int(input())
for i in range(0, N):
    scheduleList.append(list(map(int, input().split())))
    maxCostList.append(-1)

maxCost = 0


def cal(num):
    if num + scheduleList[num][0] > N:
        return 0
    subMaxCost = 0
    for k in range(num + scheduleList[num][0], N):
        maxCostList[k] = cal(k)

        if maxCostList[k] == -1:
            subMaxCost = max(subMaxCost, cal(k))
        else:
            subMaxCost = max(subMaxCost, maxCostList[k])
    return scheduleList[num][1] + subMaxCost


for i in range(0, N):
    if maxCostList[i] == -1:
        maxCost = max(maxCost, cal(i))
    else:
        maxCost = max(maxCost, maxCostList[i])

print(maxCost)
