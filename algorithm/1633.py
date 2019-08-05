TotalNum = 15
whiteList = []
blackList = []
whiteCount = 0
blackCount = 0
whiteTotalPower = 0
blackTotalPower = 0


# 87 84
# 66 78
# 86 94
# 93 87
# 72 100

while True:
    inputValue = input()
    if inputValue is "":
        break
    item = list(map(int, inputValue.split()))
    whiteList.append(item)
    blackList.append(item)

whiteList.sort(key=lambda white: white[0], reverse=True)
blackList.sort(key=lambda black: black[1], reverse=True)

for whiteIndex in range(0, len(whiteList)):
    print(whiteList[whiteIndex], " / ", blackList[whiteIndex])

    blackIndex = blackList.index(whiteList[whiteIndex])
    if whiteIndex < blackIndex and whiteCount < TotalNum:
        whiteTotalPower += whiteList[whiteIndex][0]
        whiteCount += 1
        print("white count:", whiteCount, " / value:", whiteList[whiteIndex][0], " / white total:", whiteTotalPower)
    elif whiteIndex > blackIndex and blackCount < TotalNum:
        blackTotalPower += blackList[blackIndex][1]
        blackCount += 1
        print("black count:", blackCount, " / value:", blackList[blackIndex][1], " / black total:", blackTotalPower)
    else:
        if whiteCount < blackCount and whiteCount < TotalNum:
            whiteTotalPower += whiteList[whiteIndex][0]
            whiteCount += 1
            print("white count:", whiteCount, " / value:", whiteList[whiteIndex][0], " / white total:", whiteTotalPower)
        elif whiteCount > blackCount and blackCount < TotalNum:
            blackTotalPower += blackList[whiteIndex][1]
            blackCount += 1
            print("black count:", blackCount, " / value:", blackList[blackIndex][1], " / black total:", blackTotalPower)

print(whiteTotalPower + blackTotalPower)