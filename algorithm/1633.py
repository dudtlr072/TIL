from functools import cmp_to_key

TotalNum = 15
whiteList = []
blackList = []
whiteCount = 0
blackCount = 0
whiteTotalPower = 0
blackTotalPower = 0

a = []
b = []

# 87 84
# 66 78
# 86 94
# 93 87
# 72 100

while True:
    inputValue = input()
    if inputValue is "":
        break
    temp = inputValue.split()
    temp.append("0")
    item = list(map(int, temp))
    whiteList.append(item)
    blackList.append(item)


def compareWhite(x, y):
    if x[0] > y[0]:
        return -1
    elif x[0] < y[0]:
        return 1
    else:
        if x[1] > y[1]:
            return -1
        elif x[1] < y[1]:
            return 1
        else:
            return 0


def compareBlack(x, y):
    if x[1] > y[1]:
        return -1
    elif x[1] < y[1]:
        return 1
    else:
        if x[0] > y[0]:
            return -1
        elif x[0] < y[0]:
            return 1
        else:
            return 0


whiteList.sort(key=cmp_to_key(compareWhite))
blackList.sort(key=cmp_to_key(compareBlack))

for i in range(0, len(whiteList)):
    blackIndex = blackList.index(whiteList[i])
    whiteIndex = whiteList.index(blackList[i])
    print(whiteList[i], " / ", blackList[i], " / ", blackIndex, ",", whiteIndex)

    if whiteList[i][2] is not 1 and (i < blackIndex or (blackCount is TotalNum)) and whiteCount < TotalNum:
        whiteTotalPower += whiteList[i][0]
        blackList[blackIndex][2] = 1
        whiteCount += 1
        print("white count:", whiteCount, " / value:", whiteList[i][0], " / white total:", whiteTotalPower)

    if blackList[i][2] is not 1 and (i < whiteIndex or (whiteCount is TotalNum)) and blackCount < TotalNum:
        blackTotalPower += blackList[i][1]
        whiteList[whiteIndex][2] = 1
        blackCount += 1
        print("black count:", blackCount, " / value:", blackList[i][1], " / black total:", blackTotalPower)

    if whiteList[i] == blackList[i]:
        if whiteList[i][0] > whiteList[i][1] and whiteCount < TotalNum:
            whiteTotalPower += whiteList[i][0]
            blackList[blackIndex][2] = 1
            whiteCount += 1
            print("white count:", whiteCount, " / value:", whiteList[i][0], " / white total:", whiteTotalPower)
        elif blackCount < TotalNum:
            blackTotalPower += blackList[i][1]
            whiteList[whiteIndex][2] = 1
            blackCount += 1
            print("black count:", blackCount, " / value:", blackList[i][1], " / black total:", blackTotalPower)

# for whiteIndex in range(0, TotalNum):
#     blackIndex = blackList.index(whiteList[whiteIndex])
#     print(whiteList[whiteIndex], " / ", blackList[whiteIndex], " / ", whiteIndex, ",", blackIndex)
#     if whiteIndex < blackIndex and whiteCount < TotalNum / 2:
#         whiteTotalPower += whiteList[whiteIndex][0]
#         whiteCount += 1
#         a.append(whiteList[whiteIndex][0])
#         print("white count:", whiteCount, " / value:", whiteList[whiteIndex][0], " / white total:", whiteTotalPower)
#     elif whiteIndex > blackIndex and blackCount < TotalNum / 2:
#         blackTotalPower += blackList[blackIndex][1]
#         blackCount += 1
#         b.append(blackList[blackIndex][1])
#         print("black count:", blackCount, " / value:", blackList[blackIndex][1], " / black total:", blackTotalPower)
#     else:  # 동일한 값이면 둘중 큰 쪽을 넣는다.
#         if whiteList[whiteIndex][0] > whiteList[whiteIndex][1] and whiteCount < TotalNum / 2:
#             whiteTotalPower += whiteList[whiteIndex][0]
#             whiteCount += 1
#             print("white count:", whiteCount, " / value:", whiteList[whiteIndex][0], " / white total:", whiteTotalPower)
#         elif blackCount < TotalNum / 2:
#             blackTotalPower += whiteList[whiteIndex][1]
#             blackCount += 1
#             print("black count:", blackCount, " / value:", blackList[blackIndex][1], " / black total:", blackTotalPower)

        # if whiteCount < blackCount and whiteCount < TotalNum:
        #     whiteTotalPower += whiteList[whiteIndex][0]
        #     whiteCount += 1
        #     a.append(whiteList[whiteIndex][0])
        #     print("white count:", whiteCount, " / value:", whiteList[whiteIndex][0], " / white total:", whiteTotalPower)
        # elif whiteCount > blackCount and blackCount < TotalNum:
        #     blackTotalPower += blackList[whiteIndex][1]
        #     blackCount += 1
        #     b.append(blackList[blackIndex][1])
        #     print("black count:", blackCount, " / value:", blackList[blackIndex][1], " / black total:", blackTotalPower)

print(whiteTotalPower + blackTotalPower)
