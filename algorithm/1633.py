from functools import cmp_to_key

TotalNum = 15
whiteList = []
blackList = []
whiteCount = 0
blackCount = 0
whiteTotalPower = 0
blackTotalPower = 0

index = 0


def compareWhite(x, y):
    if x[0] > y[0]:
        return -1
    elif x[0] < y[0]:
        return 1
    else:
        if x[1] > y[1]:
            return 1
        elif x[1] < y[1]:
            return -1
        else:
            return 0


def compareBlack(x, y):
    if x[1] > y[1]:
        return -1
    elif x[1] < y[1]:
        return 1
    else:
        if x[0] > y[0]:
            return 1
        elif x[0] < y[0]:
            return -1
        else:
            return 0


while True:
    # try:
    #     line = input()
    # except EOFError:
    #     break
    line = input()
    if line is "":
        break
    item = list(map(int, line.split()))
    item.append(0)
    item.append(index)
    whiteList.append(item)
    blackList.append(item)
    index += 1

whiteList.sort(key=cmp_to_key(compareWhite))
blackList.sort(key=cmp_to_key(compareBlack))


def valueCompare(listItem):
    if listItem[0] > listItem[1]:
        return 1
    elif listItem[0] < listItem[1]:
        return -1
    else:
        return 0


for i in range(0, len(whiteList)):
    blackIndex = blackList.index(whiteList[i])
    whiteIndex = whiteList.index(blackList[i])
    print("\n", whiteList[i], " / ", blackList[i], " / [", i, "],", blackIndex, ",", whiteIndex)
    print("whiteCount:", whiteCount)
    print("blackCount:", blackCount)

    if whiteList[i][2] is not 1:
        if valueCompare(whiteList[i]) is 1 and (i < blackIndex or (blackCount is TotalNum)) and whiteCount < TotalNum:
            whiteTotalPower += whiteList[i][0]
            whiteCount += 1
            blackList[blackIndex][2] = 1
            print("[1] white count:", whiteCount, " / value:", whiteList[i][0], " / white total:", whiteTotalPower)
        elif valueCompare(whiteList[i]) is -1:
            if blackCount < TotalNum:
                blackTotalPower += blackList[blackIndex][1]
                blackCount += 1
                blackList[blackIndex][2] = 1
                print("[1_1] black count:", blackCount, " / value:", blackList[blackIndex][1], " / black total:", blackTotalPower)
            elif whiteCount < TotalNum:
                whiteTotalPower += whiteList[i][0]
                whiteCount += 1
                blackList[i][2] = 1
                print("[1_2] white count:", whiteCount, " / value:", whiteList[i][0], " / white total:", whiteTotalPower)
        elif valueCompare(whiteList[i]) is 0:
            if blackCount <= whiteCount:
                blackTotalPower += blackList[blackIndex][1]
                blackCount += 1
                blackList[blackIndex][2] = 1
                print("[1_3] black count:", blackCount, " / value:", blackList[blackIndex][1], " / black total:", blackTotalPower)
            else:
                whiteTotalPower += whiteList[i][0]
                whiteCount += 1
                blackList[i][2] = 1
                print("[1_4] white count:", whiteCount, " / value:", whiteList[i][0], " / white total:", whiteTotalPower)

    if blackList[i][2] is not 1:
        if valueCompare(blackList[i]) is -1 and (i < whiteIndex or (whiteCount is TotalNum)) and blackCount < TotalNum:
            blackTotalPower += blackList[i][1]
            blackCount += 1
            whiteList[whiteIndex][2] = 1
            print("[2] black count:", blackCount, " / value:", blackList[i][1], " / black total:", blackTotalPower)
        elif valueCompare(blackList[i]) is 1:
            if whiteCount < TotalNum:
                whiteTotalPower += whiteList[whiteIndex][0]
                whiteCount += 1
                whiteList[whiteIndex][2] = 1
                print("[2_1] white count:", whiteCount, " / value:", whiteList[whiteIndex][0], " / white total:", whiteTotalPower)
            elif blackCount < TotalNum:
                blackTotalPower += blackList[i][1]
                blackCount += 1
                blackList[i][2] = 1
                print("[2_2] black count:", blackCount, " / value:", blackList[i][1], " / black total:", blackTotalPower)
        elif valueCompare(blackList[i]) is 0:
            if whiteCount <= blackCount:
                whiteTotalPower += whiteList[whiteIndex][0]
                whiteCount += 1
                whiteList[whiteIndex][2] = 1
                print("[2_3] white count:", whiteCount, " / value:", whiteList[whiteIndex][0], " / white total:", whiteTotalPower)
            else:
                blackTotalPower += blackList[i][1]
                blackCount += 1
                blackList[i][2] = 1
                print("[2_4] black count:", blackCount, " / value:", blackList[i][1], " / black total:", blackTotalPower)
print(whiteTotalPower + blackTotalPower)
